## [LCP 07. 传递信息](https://leetcode.cn/problems/chuan-di-xin-xi/description/)

## 题目描述

小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：

有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。

## 题目考点

动态规划

## 题解

- 定义 dp[k][n] 表示第 k 轮传递到 n 的方案总数，则有：dp[k][n] = 存在 [m to n] 关系的所有 dp[k-1][m] 的和
- 边界条件：dp[0][0] = 1, d[0][m] = 0
- 目标：dp[k-1][n-1]

正向思路：遍历能够传递到 index 的关系

```
class Solution {
    fun numWays(n: Int, relation: Array<IntArray>, k: Int): Int {
        // 邻接表
        val graph = Array(n) { LinkedList<Int>() }.apply {
            for (element in relation) {
                // dst to src
                this[element[1]].add(element[0])
            }
        }

        val dp = Array(k + 1) { IntArray(n) { 0 } }.apply {
            this[0][0] = 1
        }
        for (kIndex in 1..k) {
            for (index in 0..n - 1) {
                // 遍历能够传递到 index 的关系
                for (path in graph[index]) {
                    dp[kIndex][index] += dp[kIndex - 1][path]
                }
            }
        }
        return dp[k][n - 1]
    }
}
```

or

```
class Solution {
    fun numWays(n: Int, relation: Array<IntArray>, k: Int): Int {
        val dp = Array(k + 1) { IntArray(n) { 0 } }.apply {
            this[0][0] = 1
        }
        for (kIndex in 1..k) {
            for (index in 0..n - 1) {
                // 遍历能够传递到 index 的关系
                for (element in relation) {
                    if (element[1] == index) dp[kIndex][index] += dp[kIndex - 1][element[0]]
                }
            }
        }
        return dp[k][n - 1]
    }
}
```

逆向思路：计算每个关系的贡献

```
class Solution {
    fun numWays(n: Int, relation: Array<IntArray>, k: Int): Int {
        val dp = Array(k + 1) { IntArray(n) { 0 } }.apply {
            this[0][0] = 1
        }
        for (kIndex in 1 .. k) {
            // 计算每个关系的贡献
            for (element in relation) {
                dp[kIndex][element[1]] += dp[kIndex - 1][element[0]]
            }
        }
        return dp[k][n - 1]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(k·m) m 是关系数
- 空间复杂度：O(k·n) n 是人数

## 题解二（动态规划 + 空间优化）

```
class Solution {
    fun numWays(n: Int, relation: Array<IntArray>, k: Int): Int {
        var dp = IntArray(n) { 0 }.apply {
            this[0] = 1
        }
        for (kIndex in 1..k) {
            val cur = IntArray(n) { 0 }
            // 计算每个关系的贡献
            for (element in relation) {
                cur[element[1]] += dp[element[0]]
            }
            dp = cur
        }
        return dp[n - 1]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(k·m) m 是关系数
- 空间复杂度：O(n) n 是人数
