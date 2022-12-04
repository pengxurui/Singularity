## [105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)
## [剑指 Offer 07. 重建二叉树](https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

## 题目考点

二叉树 + 分治

对于任意一颗树而言，前序遍历的形式总是

[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是

[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]

以题目示例为例：

前序遍历划分 [ 3 | 9 | 20 15 7 ]
中序遍历划分 [ 9 | 3 | 15 20 7 ]

此时：
- 1、前序遍历的首元素 为 树的根节点 node 的值。

- 2、在中序遍历中搜索根节点 node 的索引 ，可将 中序遍历 划分为 [ 左子树 | 根节点 | 右子树 ] 。

- 3、根据中序遍历中的左（右）子树的节点数量，可将 前序遍历 划分为 [ 根节点 | 左子树 | 右子树 ] 。

通过以上三步，可确定 三个节点 ：1.树的根节点、2.左子树根节点、3.右子树根节点。

根据「分治算法」思想，对于树的左、右子树，仍可复用以上方法划分子树的左右子树。

[参考](https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solutions/100091/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/)

## 题解一（递归）
 
```
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {

    private val inOrderIndexMap = HashMap<Int, Int>()

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        for ((index, element) in inorder.withIndex()) {
            inOrderIndexMap[element] = index
        }
        return buildRoot(preorder, inorder, 0, preorder.size - 1, 0, inorder.size - 1)
    }

    private fun buildRoot(preorder: IntArray, inorder: IntArray, preFromIndex: Int, preToIndex: Int, inFromIndex: Int, inToIndex: Int): TreeNode? {
        if(preFromIndex > preToIndex){
            return null
        }
        val rootVal = preorder[preFromIndex]
        val rootIndex = inOrderIndexMap[rootVal]!!
        return TreeNode(rootVal).apply {
            val leftTreeCount = rootIndex - inFromIndex
            left = buildRoot(preorder, inorder, preFromIndex + 1, preFromIndex + leftTreeCount, inFromIndex, rootIndex - 1)
            right = buildRoot(preorder, inorder, preFromIndex + leftTreeCount + 1, preToIndex, rootIndex + 1, inToIndex)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
