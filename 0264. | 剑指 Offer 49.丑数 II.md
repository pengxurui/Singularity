## [264. 丑数 II](https://leetcode.cn/problems/ugly-number-ii/description/)
## [剑指 Offer 49. 丑数](https://leetcode.cn/problems/chou-shu-lcof/)

## 题目描述

给你一个整数 n ，请你找出并返回第 n 个 丑数 。

丑数 就是只包含质因数 2、3 和/或 5 的正整数。

## 题目考点

位运算

## 题解一（小顶堆）
 
先将最小的丑数 1 入堆，再依次取堆顶，将 2x、3x、5x 入堆，最终第 n 次出堆的数就是答案（可以理解为基于堆的动态规划）

为了避免重复入堆，可以用散列表统计

```
class Solution {
    fun nthUglyNumber(n: Int): Int {
        // 小顶堆
        val set = HashSet<Long>()
        val heap = PriorityQueue<Long>().apply {
            offer(1)
        }
        val factors = longArrayOf(2L, 3L, 5L)
        var count = n
        while (--count > 0) {
            val min = heap.poll()
            for (factor in factors) {
                val next : Long = factor * min
                if (set.add(next)) {
                    heap.offer(next)
                }
            }
        }
        return heap.poll().toInt()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn)
- 空间复杂度：O(n) 

## 题解二（动态规划）
 
丑数举例：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12]

小顶堆的解法是在堆顶的基础上推导后续丑数，我们可以脱离堆直接在数组上递推。

我们发现所有丑数的序列一定被 3 个子序列的一个或多个覆盖，子序列之间有重复：

- 丑数乘以 2 序列：1x2、2x2、3x2、4x2...
- 丑数乘以 3 序列：1x3、2x3、3x3、4x3...
- 丑数乘以 5 序列：1x5、2x5、3x5、4x5...

因此，我们可以定义在 3 个子序列上的指针 p2、p3、p5，每次取尝试取三个指针中最小的数放到目标数组中，并将指针推到下一位。

最终输出第 n 个丑数

```
class Solution {
    fun nthUglyNumber(n: Int): Int {
        // 动态规划：dp[index]=min{dp[p2]*2,dp[p3]*3,dp[p5]*5}
        // dp 表示第 index 个丑数，第 0 位无效
        val dp = IntArray(n + 1) { 1 }
        var p2 = 1
        var p3 = 1
        var p5 = 1
        for (index in 2..n) {
            val num2 = dp[p2] * 2
            val num3 = dp[p3] * 3
            val num5 = dp[p5] * 5
            val cur = Math.min(num2, Math.min(num3, num5)).also {
                dp[index] = it
            }

            if (cur == num2) p2++
            if (cur == num3) p3++
            if (cur == num5) p5++
        }
        return dp[n]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
