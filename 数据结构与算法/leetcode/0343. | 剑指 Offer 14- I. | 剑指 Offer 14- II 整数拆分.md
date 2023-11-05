## [343. 整数拆分](https://leetcode.cn/problems/integer-break/description/)
## [剑指 Offer 14- I. 剪绳子](https://leetcode.cn/problems/jian-sheng-zi-lcof/?favorite=xb9nqhhg)
## [剑指 Offer 14- II. 剪绳子 II](https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/?favorite=xb9nqhhg)

## 题目描述

给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。

返回 你可以获得的最大乘积 。

## 题目考点

动态规划、数学

## 题解一（动态规划）
 
```
class Solution {
    fun integerBreak(n: Int): Int {
        // 由于每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积，因此可以使用动态规划求解
        // 最优子结构：设 dp[n] 为 n 的最大乘积，若 n = i + j，则有 dp[n] = dp[i] * dp[j]。
        // 在 i 固定时，j 有拆分和不拆分两种选择，因此有状态转移方程 dp[n] = max{i*(n-i),i*dp[n-i]}

        val dp = IntArray(n + 1) { -1 }
        for (element in 2..n) {
            var elementMax = 0
            // 当前状态的最优解
            for (i in 1 until element) {
                // 根据子问题的最优解推导当前问题的最优解
                elementMax = Math.max(elementMax, Math.max(i * (element - i), i * dp[element - i]))
            }
            dp[element] = elementMax
        }
        return dp[n]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n\*n)，计算一个整数需要的时间是 O(n)
- 空间复杂度：O(n)

## 题解二（数学推导）

通过数学可证明以下两点：

数字拆分为更多的 3，其次为 2，最差为 1，可让乘积最大化。
但是，若最终拆出来，剩下一个 1，我们将其中一个 3+1 组成 4，可让乘积更大化。

```
class Solution {
    fun integerBreak(n: Int): Int {
        if (n <= 3) return n - 1
        val a = n / 3
        val b = n % 3
        return when (b) {
            0 -> {
                Math.pow(3.toDouble(), a.toDouble()).toInt()
            }
            1 -> {
                (Math.pow(3.toDouble(), (a - 1).toDouble()) * 4).toInt()
            }
            else -> {
                (Math.pow(3.toDouble(), a.toDouble()) * 2).toInt()
            }
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(1)
- 空间复杂度：O(1)

## 题解三（增加大数取余条件 · 动态规划）

尝试在动态规划的基础上取余，就算把数据类型都换成long也是无解的，对每次的 dp[i] 取余确实可以避免溢出的问题，但是由于过程中修改了值，会导致最终结果和预期不同。比如这一步： dp[i] = Math.max(dp[i] ，x * y ); x * y = 1000000005 ，若dp[i] 本该等于 1000000008 ，但是经过上次取余后变成了1，本来的结果应该是1000000008 ，现在却变成了1000000005，所以在动态规划过程中是不能取余的，那么就只能使用BigInter存储中间结果了。

```
class Solution {
    fun cuttingRope(n: Int): Int {
        val dp = Array(n + 1) { BigInteger.valueOf(-1) }
        for (element in 2..n) {
            var elementMax = BigInteger.valueOf(0)
            // 当前状态的最优解
            for (i in 1 until element) {
                // 根据子问题的最优解推导当前问题的最优解
                // elementMax = Math.max(elementMax, Math.max(i * (element - i), i * dp[element - i]))
                elementMax = elementMax.max(BigInteger.valueOf(i * (element - i).toLong())).max(dp[element - i].multiply(BigInteger.valueOf(i.toLong())))
            }
            dp[element] = elementMax
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).toInt()
    }
}
```

性能非常差

## 题解四（增加大数取余条件 · 数学推导）

```
class Solution {
    fun cuttingRope(n: Int): Int {
        if (n <= 3) return n - 1
        val a = n / 3
        val b = n % 3
        val MOD = 1000000007
        return ((when (b) {
            0 -> {
                powWithMod(3.toDouble(), a.toDouble(), MOD)
            }
            1 -> {
                powWithMod(3.toDouble(), (a - 1).toDouble(), MOD) * 4
            }
            else -> {
                powWithMod(3.toDouble(), a.toDouble(), MOD) * 2
            }
        }) % MOD).toInt()
    }

    private fun powWithMod(a: Double, b: Double, mod: Int): Double {
        var result: Double = 1.toDouble()
        var bCount = 0
        while (bCount < b) {
            result = result * a % mod
            bCount++
        }
        return result
    }
}
```
