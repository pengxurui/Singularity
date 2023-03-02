## [416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum/description/)
## [剑指 Offer II 101. 分割等和子集](https://leetcode.cn/problems/NUPfPr/?favorite=e8X3pBZi)

## 题目描述

给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。

## 题目考点



## 题解一（排序 · 错误）
 
```
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val fullSum = nums.sum()
        val target = fullSum / 2
        // 不能整除
        if (target * 2 != fullSum) return false
        // 排序
        nums.sort()
        // 游标
        var index = 0
        var leftSum = 0
        var rightSum = fullSum
        //  && index < nums.size
        while (leftSum < rightSum) {
            leftSum += nums[index]
            rightSum -= nums[index]
            index++
            if (leftSum == rightSum) return true
        }
        return false
    }
}
```

case ：[2,2,1,1]

## 题解二（枚举子集 · 错误）

错误：子序列可以是非连续的

```
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val fullSum = nums.sum()
        println(fullSum)
        // 不能整除
        if (fullSum / 2 * 2 != fullSum) return false
        // 枚举子集
        for (start in 0 until nums.size) {
            var curSum = 0
            for (index in start until nums.size) {
                curSum += nums[index]
                if (curSum * 2 == fullSum) return true
            }
        }
        return false
    }
}
```

## 题解三（回溯 + 备忘录 · 超出时间限制）

```
class Solution {

    private val memo = HashMap<String, Boolean>()

    fun canPartition(nums: IntArray): Boolean {
        // 回溯
        val n = nums.size
        val fullSum = nums.sum()
        if (fullSum % 2 != 0) return false
        val target = fullSum / 2
        return canPartitionBackTrack(nums, 0, 0, target)
    }

    private fun canPartitionBackTrack(nums: IntArray, index: Int, sum: Int, target: Int): Boolean {
        if (index == nums.size) return sum == target
        val key = "${index}_$sum"
        if (memo.containsKey(key)) return memo[key]!!
        // 选择与不选择
        val choice1 = canPartitionBackTrack(nums, index + 1, sum, target)
        val choice2 = canPartitionBackTrack(nums, index + 1, sum + nums[index], target)
        val result = choice1 || choice2
        memo[key] = result
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(C(n,n))
- 空间复杂度：O(n) 

## 题解四（动态规划 · 0-1 背包问题）

从 n 个物品中选择 x 个后得到目标或者价值最大

- 0-1 背包问题：每个物品最多只能选择 1 次
- 完全背包问题：每个物品可以选择无限次

错误版本：忽略的转移方程第一项不受限制

```
class Solution {

    fun canPartition(nums: IntArray): Boolean {
        // 动态规划
        val n = nums.size
        val fullSum = nums.sum()
        if (fullSum % 2 != 0) return false
        val target = fullSum / 2
        // dp[i][j] 表示以 [i] 为结尾且和为 [j] 的方案是否存在
        // dp[i][j] = d[i - 1][j] || dp[i - 1][j - nums[i]]
        val dp = Array(n + 1) { BooleanArray(fullSum + 1) }.apply {
            this[0][0] = true
        }
        for (i in 1..n) {
            for (j in nums[i - 1]..fullSum) {
                if (dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]) {
                    dp[i][j] = true
                    break
                }
            }
        }
        return dp[n][target]
    }
}
```

正确版本：

```
class Solution {

    fun canPartition(nums: IntArray): Boolean {
        // 动态规划
        val n = nums.size
        val fullSum = nums.sum()
        if (fullSum % 2 != 0) return false
        val target = fullSum / 2
        // dp[i][j] 表示以 [i] 为结尾且和为 [j] 的方案是否存在
        // dp[i][j] = d[i - 1][j] || dp[i - 1][j - nums[i]]
        val dp = Array(n + 1) { BooleanArray(fullSum + 1) }.apply {
            this[0][0] = true
        }
        for (i in 1..n) {
            for (j in 0..fullSum) {
                dp[i][j] = if (j >= nums[i - 1]) {
                    dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]
                } else {
                    dp[i - 1][j]
                }
            }
        }
        return dp[n][target]
    }
}
```

空间优化：

```
class Solution {

    fun canPartition(nums: IntArray): Boolean {
        // 动态规划
        val n = nums.size
        val fullSum = nums.sum()
        if (fullSum % 2 != 0) return false
        val target = fullSum / 2
        // dp[i][j] 表示以 [i] 为结尾且和为 [j] 的方案是否存在
        // dp[i][j] = d[i - 1][j] || dp[i - 1][j - nums[i]]
        val dp = BooleanArray(fullSum + 1).apply {
            this[0] = true
        }
        for (i in 1..n) {
            // 从大到小遍历避免覆盖上一层
            for (j in fullSum downTo 0) {
                dp[j] = if (j >= nums[i - 1]) {
                    dp[j] || dp[j - nums[i - 1]]
                } else {
                    dp[j]
                }
            }
        }
        return dp[target]
    }
}
```

继续优化：我们发现第二维不需要遍历 [0,fullSum]，而是只需要关心 [0,target] 范围的数据

```
class Solution {

    fun canPartition(nums: IntArray): Boolean {
        // 动态规划
        val n = nums.size
        val fullSum = nums.sum()
        if (fullSum % 2 != 0) return false
        val target = fullSum / 2
        // dp[i][j] 表示以 [i] 为结尾且和为 [j] 的方案是否存在
        // dp[i][j] = d[i - 1][j] || dp[i - 1][j - nums[i]]
        val dp = BooleanArray(target + 1).apply {
            this[0] = true
        }
        for (i in 1..n) {
            // 从大到小遍历避免覆盖上一层
            for (j in target downTo 0) {
                if (j >= nums[i - 1]) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]]
                }
            }
        }
        return dp[target]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·target)
- 空间复杂度：O(target) 
