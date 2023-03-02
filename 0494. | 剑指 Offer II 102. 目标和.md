## [494. 目标和](https://leetcode.cn/problems/target-sum/description/)
## [剑指 Offer II 102. 加减的目标值](https://leetcode.cn/problems/YaVDxD/)

## 题目描述

给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

## 题目考点

回溯、动态规划、背包问题

参考：https://leetcode.cn/problems/target-sum/solutions/816711/gong-shui-san-xie-yi-ti-si-jie-dfs-ji-yi-et5b/

## 题解一（回溯）

由于元素个数在 20 以内，所以用回溯可以满足。
 
回溯有使用 ”全局变量“ 和 ”子问题返回值“ 两种写法，这里使用后者

```
class Solution {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        return findTargetSumWaysBackTrack(nums, 0, 0, target)
    }

    // return：方案数
    private fun findTargetSumWaysBackTrack(nums: IntArray, startIndex: Int, sum: Int, target: Int): Int {
        if (startIndex == nums.size) {
            return if (sum == target) 1 else 0
        }
        // 当前状态采用 + 和 - 两个选择的方案数
        val left = findTargetSumWaysBackTrack(nums, startIndex + 1, sum + nums[startIndex], target)
        val right = findTargetSumWaysBackTrack(nums, startIndex + 1, sum - nums[startIndex], target)
        return left + right
    }
}
```

我们发现问题的状态取决于当前下标 startIndex 和当前和 sum，所以在求解的过程中存在重叠子问题，我们可以增加备忘录：

```
class Solution {

    // startIndex_sum to 方案数
    private val memo = HashMap<String,Int>()

    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        return findTargetSumWaysBackTrack(nums, 0, 0, target)
    }

    // return：方案数
    private fun findTargetSumWaysBackTrack(nums: IntArray, startIndex: Int, sum: Int, target: Int): Int {
        if (startIndex == nums.size) {
            return if (sum == target) 1 else 0
        }
        val key = "${startIndex}_$sum"
        if(memo.containsKey(key)) return memo[key]!!
        // 当前状态采用 + 和 - 两个选择的方案数
        val left = findTargetSumWaysBackTrack(nums, startIndex + 1, sum + nums[startIndex], target)
        val right = findTargetSumWaysBackTrack(nums, startIndex + 1, sum - nums[startIndex], target)
        val result = left + right
        memo[key] = result
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(2^n)
- 空间复杂度：O(n) 递归栈 

## 题解二（动态规划 · 背包问题）

所有位置选择负和正可以得到目标和的覆盖范围是 [-fullSum, fullSum]，那么问题就变成在 nums 中选择若干个数
，使得最终的和为 target（背包价值为 target）的方案数

此时，我们定义 dp[i][j + fullSum] 表示以 [i] 为结果且和为 j 的方案数，那么对于每一位 [i] 有两种选择：

- 正号：那么 dp[i][j] = dp[i - 1][j - nums[i]]，加上 [i] 后的和就是 j
- 负号：那么 dp[i][j] = dp[i - 1][j + nums[i]]，减去 [i] 后的和就是 j

- 终止条件：dp[n][target]
- 由于和的范围存在负数，所以在第二维的处理中我们需要统一加上 fullSum 偏移

- 注意：在背包问题中，如果是求最值一般是对子状态求 max，而求方案数则是对所有子状态的解求和。

```
class Solution {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val n = nums.size
        val fullSum = nums.sum()
        if (Math.abs(target) > fullSum) return 0
        val dp = Array(n + 1) { IntArray(fullSum * 2 + 1) }.apply {
            this[0][fullSum] = 1
        }
        for (i in 0 .. n - 1) {
            for (j in -fullSum..fullSum) {
                if (j - nums[i] + fullSum >= 0) dp[i + 1][j + fullSum] += dp[i][j - nums[i] + fullSum]
                if (j + nums[i] + fullSum <= 2 * fullSum) dp[i + 1][j + fullSum] += dp[i][j + nums[i] + fullSum]
            }
        }
        return dp[n][target + fullSum]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·S) S 是和的覆盖范围
- 空间复杂度：O(n·S) 递归栈 

## 题解三（动态规划 · 背包问题 · 优化第二维）

将数组分为 2 部分：设置为负数的元素之和记为 neg，和正数部分之和 sum - neg。根据题意有：(sum - neg) - neg = target，即：neg = (sum - target) / 2

- 注意：由于 sum 和 neg 是非负数，那么 sum - target 也应该是非负数，且是可以被 2 整除（偶数）
- 此时，问题转换为对 nums 数组的分割方案：我们期望以 01 背包模型选择出若干个元素之和为 neg

定义 dp[i][j] 表示以 [i] 为终点且元素之和为 [j] 的方案数，题解就是求 dp[n][neg]，此时第二维度降低

从子状态转移：dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]]

- 其中 dp[i - 1][j] 表示不选择 [i] 时已经满足的方案
- 其中 dp[i - 1][j - nums[i]] 表示选择 [i] 后已经满足的方案，前提示 j - nums[i] >= 0（即子状态存在）

```
class Solution {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val n = nums.size
        val fullSum = nums.sum()
        val diff = fullSum - target
        if (diff < 0 || diff % 2 != 0) return 0
        val neg = diff / 2
        val dp = Array(n + 1) { IntArray(neg + 1) { 0 } }.apply {
            // 和为 0 的方案数为 1
            this[0][0] = 1
        }
        for (i in 1..n) {
            for (j in 0..neg) {
                dp[i][j] += dp[i - 1][j]
                if (j >= nums[i - 1]) dp[i][j] += dp[i - 1][j - nums[i - 1]]
            }
        }
        return dp[n][neg]
    }
}
```

空间优化：

二维从大到小遍历避免覆盖

```
class Solution {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val n = nums.size
        val fullSum = nums.sum()
        val diff = fullSum - target
        if (diff < 0 || diff % 2 != 0) return 0
        val neg = diff / 2
        val dp = IntArray(neg + 1) { 0 }.apply {
            // 和为 0 的方案数为 1
            this[0] = 1
        }
        for (i in 1..n) {
            for (j in neg downTo 0) {
                // 使用滚动数组等于已经加上这一项
                // dp[j] += dp[j]
                if (j >= nums[i - 1]) dp[j] += dp[j - nums[i - 1]]
            }
        }
        return dp[neg]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·(sum - target))
- 空间复杂度：O(sum - target) 递归栈 

