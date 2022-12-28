## [113. 路径总和 II](https://leetcode.cn/problems/path-sum-ii/description/)
## [剑指 Offer 34. 二叉树中和为某一值的路径](https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)

## 题目描述

给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。

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
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        return LinkedList<List<Int>>().also {
            pathSum(root, LinkedList<Int>(), targetSum, it)
        }
    }

    private fun pathSum(root: TreeNode?, path: MutableList<Int>, targetSum: Int, result: MutableList<List<Int>>) {
        if (null == root) return

        if (null == root.left && null == root.right) {
            if (root.`val` == targetSum) {
                path.add(root.`val`)
                result.add(LinkedList(path))
                path.removeAt(path.size - 1)
            }
            return
        }
        path.add(root.`val`)
        pathSum(root.left, path, targetSum - root.`val`, result)
        pathSum(root.right, path, targetSum - root.`val`, result)
        path.removeAt(path.size - 1)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n) 不考虑复制路径
- 空间复杂度：O(n) 递归栈
