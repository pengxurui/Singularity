## [剑指 Offer 46. 把数字翻译成字符串](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg)

## 题目描述

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

## 题目考点

动态规划

## 题解（动态规划）
 
```
class Solution {
    fun translateNum(num: Int): Int {
        // 动态规划
        // 可以拼接：dp[index] = dp[index - 1] + dp[index - 2]
        // 不可拼接：dp[index] = dp[index - 1]
        val numStr = "$num"
        val n = numStr.length
        if (n <= 1) return 1
        val dp = IntArray(n + 1).apply {
            this[0] = 1
            this[1] = 1
        }
        for (index in 2..n) {
            val elementIndex = index - 1
            dp[index] = dp[index - 1]
            if (numStr[elementIndex - 1] == '1' || (numStr[elementIndex - 1] == '2' && numStr[elementIndex] < '6')) dp[index] += dp[index - 2]
        }
        return dp[n]
    }
}
```

滚动数组：

```
class Solution {
    fun translateNum(num: Int): Int {
        // 动态规划
        // 可以拼接：dp[index] = dp[index - 1] + dp[index - 2]
        // 不可拼接：dp[index] = dp[index - 1]
        val numStr = "$num"
        val n = numStr.length
        if (n <= 1) return 1
        var pre1 = 1
        var pre2 = 1
        for (elementIndex in 1 until n) {
            var cur = pre2
            if (numStr[elementIndex - 1] == '1' || (numStr[elementIndex - 1] == '2' && numStr[elementIndex] < '6')) cur += pre1
            pre1 = pre2
            pre2 = cur
        }
        return pre2
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
