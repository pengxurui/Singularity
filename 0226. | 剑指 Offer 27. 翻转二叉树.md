## [226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/description/)
## [剑指 Offer 27. 二叉树的镜像](https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/description/?favorite=xb9nqhhg)

## 题目描述

给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

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
    fun mirrorTree(root: TreeNode?): TreeNode? {
        if (null == root) return null
        val right = root.right
        root.right = root.left
        root.left = right
        // 递归
        mirrorTree(root.left)
        mirrorTree(root.right)
        return root
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
