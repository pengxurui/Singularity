## [873. 最长的斐波那契子序列的长度](https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/description/)
## [剑指 Offer II 093. 最长斐波那契数列](https://leetcode.cn/problems/Q91FMA/description/)

## 题目描述

如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

（回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

## 题目考点

动态规划

## 题解一（正向思维）

最优子结构：

设 dp[i][j] 是以 [i,j] 为开头的目标子序列的最大长度，

如果存在 k 满足 arr[i] + arr[j] = arr[k]，那么 [i,j,k] 组成一个目标子序列，且 dp[i][j] = dp[j][k] + 1，基准条件：以 [x,7] 为开头的子序列 [1,7]、[2,7]、[3,7]、[4,7]、[5,7]、[6,7] 
 
```
class Solution {
    fun lenLongestFibSubseq(arr: IntArray): Int {
        // 索引
        val indices = HashMap<Int, Int>().apply {
            for ((index, element) in arr.withIndex()) {
                this[element] = index
            }
        }

        var result = 0

        val n = arr.size
        val dp = Array<IntArray>(n) { IntArray(n) { -1 } }

        for (j in n - 1 downTo 0) {
            for (i in j - 1 downTo 0) {
                val targetK = arr[i] + arr[j]
                val indexK = indices.getOrDefault(targetK, -1)
                if (-1 != indexK) {
                    // 存在
                    dp[i][j] = Math.max(dp[j][indexK] + 1, 3)
                }
                result = Math.max(result, dp[i][j])
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n^2)
- 空间复杂度：O(n^2)

## 题解二（逆向思维）

最优子结构：

设 dp[i][j] 是以 [i,j] 为结尾目标子序列的最大长度，

如果存在 k 满足 arr[k] + arr[i] = arr[j]，那么 [k,i,j] 组成一个目标子序列，且 dp[i][j] = dp[k][i] + 1，基准条件：以 [2,x] 为结尾的子序列 [2,3]、[2,4]、[2,5]、[2,6]、[2,7]、[2,8]

```
class Solution {
    fun lenLongestFibSubseq(arr: IntArray): Int {
        // 索引
        val indices = HashMap<Int, Int>().apply {
            for ((index, element) in arr.withIndex()) {
                this[element] = index
            }
        }

        var result = 0

        val n = arr.size
        val dp = Array<IntArray>(n) { IntArray(n) { -1 } }

        for (j in 0 until n) {
            var i = j - 1
            // 由于数组的单调性，只有 arr[i] * 2 > arr[j] 才有必要继续
            while (i >= 0 && arr[i] * 2 > arr[j]) {
                val targetK = arr[j] - arr[i]
                val indexK = indices.getOrDefault(targetK, -1)
                if (-1 != indexK) {
                    // 存在
                    dp[i][j] = Math.max(dp[indexK][i] + 1, 3)
                }
                result = Math.max(result, dp[i][j])
                i--
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n^2)
- 空间复杂度：O(n^2)

