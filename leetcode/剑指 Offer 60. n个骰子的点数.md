## [剑指 Offer 60. n个骰子的点数](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

## 题目考点

动态规划

## 题解

当添加骰子的点数为 1 时，前 n−1 个骰子的点数和应为 x−1 ，方可组成点数和 x ；同理，当此骰子为 2 时，前 n−1 个骰子应为 x−2 ；以此类推，直至此骰子点数为 6 。将这 6 种情况的概率相加，即可得到概率 f(n,x) 。递推公式如下所示：

 
参考资料：https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/solutions/637778/jian-zhi-offer-60-n-ge-tou-zi-de-dian-sh-z36d/

```
class Solution {
    fun dicesProbability(n: Int): DoubleArray {
        var dp = DoubleArray(6) { 1.0 / 6.0 }
        for (i in 2..n) {
            val newDp = DoubleArray(5 * i + 1)
            // 子问题对原问题的贡献
            for (index in dp.indices) {
                for (offset in 0 until 6) {
                    newDp[index + offset] += dp[index] / 6
                }
            }
            dp = newDp
        }

        return dp
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n^2)
- 空间复杂度：O(1) 不考虑结果数组
