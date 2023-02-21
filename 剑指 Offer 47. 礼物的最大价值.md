## [剑指 Offer 47. 礼物的最大价值](https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/description/?favorite=xb9nqhhg)

## 题目描述

在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

## 题目考点

DFS

## 题解一（DFS）
 
```
class Solution {

    private val memo = HashMap<Int, Int>()

    fun maxValue(grid: Array<IntArray>): Int {
        // DFS + 记忆化递归
        return maxValueDFS(grid, 0, 0)
    }

    // return：最大价值
    private fun maxValueDFS(grid: Array<IntArray>, row: Int, column: Int): Int {
        if (row >= grid.size || column >= grid[0].size) return 0
        if (memo.containsKey(row * 200 + column)) {
            return memo[row * 200 + column]!!
        }
        val result = grid[row][column] + Math.max(maxValueDFS(grid, row + 1, column), maxValueDFS(grid, row, column + 1))
        memo[row * 200 + column] = result
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(2·m·n) 一共有 mn 个子问题，每个问题有两种选择
- 空间复杂度：O(max{m,n}) 递归栈

## 题解二（动态规划）

设 f(i,j) 为从棋盘左上角走至单元格 (i,j) 的礼物最大累计价值，易得到以下递推关系：f(i,j) 等于 f(i,j−1) 和 f(i−1,j) 中的较大值加上当前单元格礼物价值 grid(i,j)。

```
class Solution {
    fun maxValue(grid: Array<IntArray>): Int {
        // 动态规划
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) { 0 } }
        for (row in 0 until m) {
            for (column in 0 until n) {
                // 自左向右的路径
                val leftPath = if (column == 0) 0 else dp[row][column - 1]
                // 自上向下的路径
                val upPath = if (row == 0) 0 else dp[row - 1][column]
                dp[row][column] = grid[row][column] + Math.max(leftPath, upPath)
            }
        }
        return dp[m - 1][n - 1]
    }
}
```

滚动数组：

```
class Solution {
    fun maxValue(grid: Array<IntArray>): Int {
        // 动态规划
        val m = grid.size
        val n = grid[0].size
        val dp = IntArray(n) { 0 }
        for (row in 0 until m) {
            for (column in 0 until n) {
                // 自左向右的路径
                val leftPath = if (column == 0) 0 else dp[column - 1]
                // 自上向下的路径
                val upPath = if (row == 0) 0 else dp[column]
                dp[column] = grid[row][column] + Math.max(leftPath, upPath)
            }
        }
        return dp[n - 1]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(m·n)
- 空间复杂度：O(min{m,n})
