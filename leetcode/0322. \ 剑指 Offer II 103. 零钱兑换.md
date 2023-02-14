## [322. 零钱兑换](https://leetcode.cn/problems/coin-change/description/)
## [剑指 Offer II 103. 最少的硬币数目](https://leetcode.cn/problems/gaM7Ch/?favorite=e8X3pBZi)

## 题目描述

给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。

## 题目考点

回溯、动态规划

参考：https://leetcode.cn/problems/coin-change/solutions/6568/dong-tai-gui-hua-tao-lu-xiang-jie-by-wei-lai-bu-ke/

## 题解一（回溯思想 + 备忘录）

使用回溯可以解决决策问题，由于这道题不对硬币数量做限制，所以我们没有必要使用排列的 used 数组或组合的 startIndex 变量，也可以理解为 startIndex 始终为 0

终止条件：amount = 0

```
class Solution {

    // amount to count
    private val memo = HashMap<Int, Int>()

    fun coinChange(coins: IntArray, amount: Int): Int {
        return coinChangeBackTrack(coins, amount)
    }

    // return:获得 leftAmount 的最小数量,-1 表示无解
    private fun coinChangeBackTrack(coins: IntArray, leftAmount: Int): Int {
        if (leftAmount == 0) return 0
        if (leftAmount < 0) return -1
        if (memo.containsKey(leftAmount)) return memo[leftAmount]!!
        var result = Integer.MAX_VALUE
        for (coin in coins) {
            // 不需要选择
            val nextChoice = coinChangeBackTrack(coins, leftAmount - coin)
            if (-1 != nextChoice) result = Math.min(result, nextChoice + 1)
            // 亦不需要回溯
        }
        if (Integer.MAX_VALUE == result) result = -1
        memo[leftAmount] = result
        return result
    }
}
```
**复杂度分析：**

- 时间复杂度：O(Sn) 一共有 S 种状态，每个状态需要遍历 n 种面值。由于使用了备忘录所以每种状态只会计算一次，整体时间复杂度是 O(Sn)
- 空间复杂度：O(S) 

## 题解二（动态规划）

最优子结构：

dp[i] 表示以 [i] 为总金额的最少金币数，则有 dp[i] = min{dp[i - coin]} + 1

即尝试每种减去 coin，如果 [i-coin] 子问题有解，则记录方案取最小值，最后 + 1

```
class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { Integer.MAX_VALUE }.apply {
            this[0] = 0
        }
        for (i in 1..amount) {
            for (coin in coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], dp[i - coin] + 1)
            }
        }
        return if (dp[amount] == Integer.MAX_VALUE) -1 else dp[amount]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(Sn)
- 空间复杂度：O(S) 

## 题解三（完全背包）

这道题的动态规划也可以用完全背包的实现，定义 dp[i][j] 表示以 [i] 为结尾且和为 [j] 的最少硬币数，那么：

dp[i][j] = min{dp[i - 1][j], dp[i - 1][j - k*coin] + k}

```
class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val n = coins.size
        val dp = Array(n + 1) { IntArray(amount + 1) { Integer.MAX_VALUE } }.apply {
            this[0][0] = 0
        }
        for (i in 1..n) {
            for (j in 0..amount) {
                dp[i][j] = dp[i - 1][j]
                for (k in 0..j / coins[i - 1]) {
                    if (j >= k * coins[i - 1] && dp[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coins[i - 1]] + k)
                }
            }
        }
        return if (dp[n][amount] != Integer.MAX_VALUE) dp[n][amount] else -1
    }
}
```

一维化后：

dp[j] = min{dp[j], dp[j - k*coin] + k}

```
class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val n = coins.size
        val dp = IntArray(amount + 1) { Integer.MAX_VALUE }.apply {
            this[0] = 0
        }
        for (i in 1..n) {
            // 从高到低
            for (j in amount downTo 0) {
                for (k in 0..j / coins[i - 1]) {
                    if (j >= k * coins[i - 1] && dp[j - k * coins[i - 1]] != Integer.MAX_VALUE) dp[j] = Math.min(dp[j], dp[j - k * coins[i - 1]] + k)
                }
            }
        }
        return if (dp[amount] != Integer.MAX_VALUE) dp[amount] else -1
    }
}
```

换元后（将面值视为成本，数量视为价值）：

另外，使用小于 Integer.MAX_VALUE 的较小数可以简化代码

dp[j] = min{dp[j], dp[j - coin] + k}

```
class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val INF = 0x3F3F3F3F
        val n = coins.size
        val dp = IntArray(amount + 1) { INF }.apply {
            this[0] = 0
        }
        for (i in 1..n) {
            val coin = coins[i - 1]
            // 从低到高
            for (j in coin .. amount) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1)
            }
        }
        return if (dp[amount] != INF) dp[amount] else -1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(Sn)
- 空间复杂度：O(S) 
