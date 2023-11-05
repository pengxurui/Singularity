## [剑指 Offer 32 - I. 从上到下打印二叉树](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

## 题目考点

二叉树

## 题解
 
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
    fun levelOrder(root: TreeNode?): IntArray {
        if (null == root) return IntArray(0)

        val resultList = LinkedList<Int>()
        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        while (!queue.isEmpty()) {
            for (count in 0 until queue.size) {
                val node = queue.poll()
                resultList.add(node.`val`)

                if (null != node.left) {
                    queue.offer(node.left)
                }
                if (null != node.right) {
                    queue.offer(node.right)
                }
            }
        }

        return resultList.toIntArray()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
