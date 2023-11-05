## [129. 求根节点到叶节点数字之和](https://leetcode.cn/problems/sum-root-to-leaf-numbers/description/)
## [剑指 Offer II 049. 从根节点到叶节点的路径数字之和](https://leetcode.cn/problems/3Etpl5/)

## 题目描述

给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：

例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。

## 题目考点

递归

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

    fun sumNumbers(root: TreeNode?): Int {
        return searchNumbersDFS(root, 0)
    }

    private fun searchNumbersDFS(root: TreeNode?, parent: Int) : Int {
        if (null == root) return 0
        val cur: Int = parent * 10 + root.`val`
        if (null == root.left && null == root.right) {
            return cur
        }
        return searchNumbersDFS(root.left, cur) + searchNumbersDFS(root.right, cur)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
