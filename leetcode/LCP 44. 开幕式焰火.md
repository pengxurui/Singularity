## [LCP 44. 开幕式焰火](https://leetcode.cn/problems/sZ59z6/description/)

## 题目描述

「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。

## 题目考点

DFS、散列表

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

    private val colorSet = HashSet<Int>()

    fun numColor(root: TreeNode?): Int {
        numColorDFS(root)
        return colorSet.size
    }

    private fun numColorDFS(root: TreeNode?) {
        if (null == root) return
        colorSet.add(root.`val`)
        numColorDFS(root.left)
        numColorDFS(root.right)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
