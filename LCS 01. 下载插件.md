## [LCS 01. 下载插件](https://leetcode.cn/problems/Ju9Xwi/)

## 题目描述

小扣打算给自己的 VS code 安装使用插件，初始状态下带宽每分钟可以完成 1 个插件的下载。假定每分钟选择以下两种策略之一:

使用当前带宽下载插件
将带宽加倍（下载插件数量随之加倍）
请返回小扣完成下载 n 个插件最少需要多少分钟。

注意：实际的下载的插件数量可以超过 n 个

## 题目考点

回溯、动态规划

## 题解一（回溯 · 超出时间限制）

```
class Solution {
    fun leastMinutes(n: Int): Int {
        // 回溯
        return leastMinutesBackTrack(1, 0, 0, n)
    }

    private fun leastMinutesBackTrack(speed: Int, minute: Int, plugins: Int, target: Int): Int {
        // println("speed=$speed, minute=$minute, plugins=$plugins, target=$target")
        if (plugins + speed >= target) return minute + 1
        // 下载
        val choice1 = leastMinutesBackTrack(speed, minute + 1, plugins + speed, target)
        // 加速
        val choice2 = leastMinutesBackTrack(speed * 2, minute + 1, plugins, target)
        return Math.min(choice1, choice2)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(2^n)
- 空间复杂度：O(根号 n) 最多翻倍 根号 n 次  

## 题解二（回溯 + 记忆化 · 超出时间限制）

```
class Solution {

    private val memo = HashMap<Int, Int>()

    fun leastMinutes(n: Int): Int {
        // 回溯
        return leastMinutesBackTrack(1, 0, n)
    }

    private fun leastMinutesBackTrack(speed: Int, minute: Int, left: Int): Int {
        // println("speed=$speed, minute=$minute, left=$left")
        if (memo.containsKey(speed * 100000 + left)) return memo[speed * 100000 + left]!!
        return if (speed >= left) {
            minute + 1
        } else {
            // 下载
            val choice1 = leastMinutesBackTrack(speed, minute + 1, left - speed)
            // 加速
            val choice2 = leastMinutesBackTrack(speed * 2, minute + 1, left)
            Math.min(choice1, choice2)
        }.also {
            memo[speed * 100000 + left] = it
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(2^n)
- 空间复杂度：O(根号 n) 最多翻倍 根号 n 次  

## 题解三（动态规划）

贪心思路：[下载，下载] 与 [加倍,下载] 的效果是相同的，我们可以优先加倍带宽，如果第 t 分钟的带宽覆盖 n，那么 t 就是结果

定义 dp[n] 表示下载 n 个插件的最少时间，则有 dp[n] = dp[n/2] + 1，考虑到奇数则有 dp[n] = dp[(n+1)/2] + 1

```
class Solution {
    fun leastMinutes(n: Int): Int {
        val dp = IntArray(n + 1).apply {
            this[0] = 0
        }
        for (index in 1..n) {
            dp[index] = dp[(index + 1) / 2] + 1
        }
        return dp[n]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)

## 题解四（数学）

直接用贪心思路求解

```
class Solution {
    fun leastMinutes(n: Int): Int {
        var speed = 1
        var minute = 1
        while (speed < n) {
            speed *= 2
            minute += 1
        }
        return minute
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(1)
