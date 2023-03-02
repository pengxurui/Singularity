## [120. 三角形最小路径和](https://leetcode.cn/problems/triangle/description/)
## [剑指 Offer II 100. 三角形中最小路径之和](https://leetcode.cn/problems/IlPe0q/description/)

## 题目描述

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

## 题目考点

动态规划

## 题解一（动态规划 + 滚动数组）
 
```
class Solution {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if (triangle.isEmpty() || triangle[0].isEmpty()) return 0
        // 动态规划
        val size = triangle.size
        val dp = IntArray(size) {
            // 初始化取最后一行
            triangle[size - 1][it]
        }
        for (row in size - 2 downTo 0) {
            for (column in 0..row) {
                dp[column] = triangle[row][column] + Math.min(dp[column], dp[column + 1])
            }
        }
        return dp[0]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(size^2)
- 空间复杂度：O(size) 

## 题解一（动态规划 + 原地数组）

```
class Solution {
    // 形参修改为 List<MutableList<Int>>
    fun minimumTotal(triangle: List<MutableList<Int>>): Int {
        if (triangle.isEmpty() || triangle[0].isEmpty()) return 0
        // 动态规划
        val size = triangle.size
        for (row in size - 2 downTo 0) {
            for (column in 0..row) {
                triangle[row][column] += Math.min(triangle[row + 1][column], triangle[row + 1][column + 1])
            }
        }
        return triangle[0][0]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(size^2)
- 空间复杂度：O(1) 
