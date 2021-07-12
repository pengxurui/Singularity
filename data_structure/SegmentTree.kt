class SegmentTree<E>(
    private val data: Array<E>, private val merge: (e1: E?, e2: E?) -> E
) {

    private val tree: Array<E?>

    init {
        tree = Array<Any?>(4 * data.size) { null } as Array<E?>
        buildSegmentTree(0, 0, data.size - 1)
    }

    /**
     * 左子节点的索引
     */
    fun leftTreeIndex(treeIndex: Int) = 2 * treeIndex + 1

    /**
     * 右子节点的索引
     */
    fun rightTreeIndex(treeIndex: Int) = 2 * treeIndex + 2

    /**
     * 建树
     *
     * @param treeIndex 当前节点的线段树索引
     * @param dataLeft 当前节点对应的区间左端点
     * @param dataRight 当前节点对应的区间右端点
     */
    private fun buildSegmentTree(treeIndex: Int, dataLeft: Int, dataRight: Int) {
        if (dataLeft == dataRight) {
            // 叶子节点
            tree[treeIndex] = merge(data[dataLeft], null)
            return
        }
        val mid = (dataLeft + dataRight) ushr 1
        val leftChild = leftTreeIndex(treeIndex)
        val rightChild = rightTreeIndex(treeIndex)
        // 构建左子树
        buildSegmentTree(leftChild, dataLeft, mid)
        // 构建右子树
        buildSegmentTree(rightChild, mid + 1, dataRight)
        tree[treeIndex] = merge(tree[leftChild], tree[rightChild])
    }

    /**
     * 取原始数据第 index 位元素
     */
    fun get(index: Int): E {
        if (index < 0 || index > data.size) {
            throw IllegalArgumentException("Index is illegal.")
        }
        return data[index]
    }

    /**
     * 区间查询
     *
     * @param left 区间左端点
     * @param right 区间右端点
     */
    fun query(left: Int, right: Int): E {
        if (left < 0 || left >= data.size || right < 0 || right >= data.size || left > right) {
            throw IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.size - 1, left, right) // 注意：取数据长度
    }

    /**
     * 区间查询
     *
     * @param treeIndex 当前节点的线段树索引
     * @param dataLeft 当前节点对应的区间左端点
     * @param dataRight 当前节点对应的区间右端点
     * @param left 查询区间的左端点
     * @param right 查询区间的右端点
     */
    private fun query(treeIndex: Int, dataLeft: Int, dataRight: Int, left: Int, right: Int): E {
        if (dataLeft == left && dataRight == right) {
            // 查询范围正好是线段树节点区间范围
            return tree[treeIndex]!!
        }
        val mid = (dataLeft + dataRight) ushr 1
        val leftChild = leftTreeIndex(treeIndex)
        val rightChild = rightTreeIndex(treeIndex)
        // 查询区间都在左子树
        if (right <= mid) {
            return query(leftChild, dataLeft, mid, left, right)
        }
        // 查询区间都在右子树
        if (left >= mid + 1) {
            return query(rightChild, mid + 1, dataRight, left, right)
        }
        // 查询区间横跨两棵子树
        val leftResult = query(leftChild, dataLeft, mid, left, mid)
        val rightResult = query(rightChild, mid + 1, dataRight, mid + 1, right)
        return merge(leftResult, rightResult)
    }

    /**
     * 单点更新
     *
     * @param index 数据索引
     * @param value 新值
     */
    fun set(index: Int, value: E) {
        if (index < 0 || index >= data.size) {
            throw IllegalArgumentException("Index is illegal.");
        }
        data[index] = value
        set(0, 0, data.size - 1, index, value) // 注意：取数据长度
    }

    /**
     * 单点更新
     *
     * @param treeIndex 当前节点的线段树索引
     * @param dataLeft 当前节点对应的区间左端点
     * @param dataRight 当前节点对应的区间右端点
     * @param index 数据索引
     * @param value 新值
     */
    private fun set(treeIndex: Int, dataLeft: Int, dataRight: Int, index: Int, value: E) {
        if (dataLeft == dataRight) {
            // 叶子节点
            tree[treeIndex] = value
            return
        }
        // 先更新左右子树，再更新当前节点
        val mid = (dataLeft + dataRight) ushr 1
        val leftChild = leftTreeIndex(treeIndex)
        val rightChild = rightTreeIndex(treeIndex)
        if (index <= mid) {
            set(leftChild, dataLeft, mid, index, value)
        } else if (index >= mid + 1) {
            set(rightChild, mid + 1, dataRight, index, value)
        }
        tree[treeIndex] = merge(tree[leftChild], tree[rightChild])
    }
}
