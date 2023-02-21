## [剑指 Offer 54. 二叉搜索树的第k大节点](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/description/?favorite=xb9nqhhg)

## 题目描述

给定一棵二叉搜索树，请找出其中第 k 大的节点的值。

## 题目考点

二叉搜索树

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

    var count = 0

    fun kthLargest(root: TreeNode?, k: Int): Int {
        // 逆中序遍历第 k 个出现的数
        return _inorder(root, k)
    }

    private fun _inorder(root: TreeNode?, k: Int): Int {
        if (null == root) return -1
        val leftResult = _inorder(root.right, k)
        if (-1 != leftResult) {
            return leftResult
        }
        if (++count == k) {
            return root.`val`
        }
        return _inorder(root.left, k)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
