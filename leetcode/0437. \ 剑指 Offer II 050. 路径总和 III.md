## [437. 路径总和 III](https://leetcode.cn/problems/path-sum-iii/description/)
## [剑指 Offer II 050. 向下的路径节点之和](https://leetcode.cn/problems/6eUYwP/)

## 题目描述

给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

## 题目考点

二叉树、前缀和

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

    private var result = 0

    fun pathSum(root: TreeNode?, targetSum: Int): Int {

        val preSumMap = HashMap<Long, Int>().apply {
            this[0] = 1
        }
        pathSumDFS(root, 0L, targetSum, preSumMap)
        return result
    }

    fun pathSumDFS(root: TreeNode?, parentSum: Long, targetSum: Int, preSumMap: MutableMap<Long, Int>) {
        if (null == root) return

        val curSum = (parentSum + root.`val`).toLong()
        val k = (curSum - targetSum).toLong()
        result += preSumMap.getOrDefault(k, 0)

        preSumMap[curSum] = preSumMap.getOrDefault(curSum, 0) + 1

        pathSumDFS(root.left, curSum, targetSum, preSumMap)
        pathSumDFS(root.right, curSum, targetSum, preSumMap)
        preSumMap[curSum] = preSumMap.getOrDefault(curSum, 0) - 1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
