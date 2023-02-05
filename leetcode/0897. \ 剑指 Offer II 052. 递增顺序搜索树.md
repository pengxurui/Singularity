## [897. 递增顺序搜索树](https://leetcode.cn/problems/increasing-order-search-tree/)
## [剑指 Offer II 052. 展平二叉搜索树](https://leetcode.cn/problems/NYBBNL/description/)

## 题目描述

给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。

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

    private var last : TreeNode? = null

    fun increasingBST(root: TreeNode?): TreeNode? {
        if(null == root) return null
        val left = increasingBST(root.left)
        root.left = null
        // 衔接左子树
        last?.right = root
        // 标记前驱节点
        last = root
        // 衔接右子树
        root.right = increasingBST(root.right)
        return if(null != left) left else root
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
