## [53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/description/)
## [剑指 Offer 42. 连续子数组的最大和](https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)

## 题目描述

给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分。

## 题目考点

动态规划、分治

## 题解一（动态规划）

令 dp[index] 是以 index 为结尾的子数组的最大和，

那么在考虑 nums[index + 1] 时，要么将 nums[index] 拼接到前一个子数组，要么将 nums[index] 单独作为新的子数组

则有：dp[index + 1] = max{(dp[index] + num[index + 1]), (num[index + 1])}

```
class Solution {
    fun maxSubArray(nums: IntArray): Int {
        // 动态规划
        val dp = IntArray(nums.size) { -1 }.apply {
            this[0] = nums[0]
        }
        var result = dp[0]
        for (index in 1..nums.size - 1) {
            // 将 nums[index] 拼接到前一个子数组，或者将 nums[index] 单独作为新的子数组
            dp[index] = Math.max(dp[index - 1] + nums[index], nums[index])
            result = Math.max(result, dp[index])
        }
        return result
    }
}
```

```
class Solution {
    fun maxSubArray(nums: IntArray): Int {
        // 动态规划
        var pre = nums[0]
        var result = nums[0]
        for (index in 1..nums.size - 1) {
            // 将 nums[index] 拼接到前一个子数组，或者将 nums[index] 单独作为新的子数组
            val cur = Math.max(pre + nums[index], nums[index])
            result = Math.max(result, cur)
            pre = cur
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 

## 题解二（分治）

使用分治思想，区间 [left,right] 的最大子数组和可以分解为左右两个子区间 [left,mid] 和 [mid + 1,right] 的解的最大值，

另外在加上一种横跨两个子区间的特殊情况（即同时包含 nums[mid] 和 nums[mid + 1] 两个元素

```
class Solution {
    fun maxSubArray(nums: IntArray): Int {
        // 分治
        return nums.maxSubArray(0, nums.size - 1)
    }

    // return [left,right] 的解
    private fun IntArray.maxSubArray(left: Int, right: Int): Int {
        // 终止条件
        if (left == right) return this[left]
        // 左中位数
        val mid = (left + right) ushr 1
        // 横跨左右子区间的解
        val midSum = this.maxSubArrayCross(left, mid, right)
        // 左子区间的解
        val leftSum = this.maxSubArray(left, mid)
        // 右子区间的解
        val rightSum = this.maxSubArray(mid + 1, right)
        return Math.max(midSum, Math.max(leftSum, rightSum))
    }

    // 横跨左右子区间的解（一定包含 nums[mid] 和 nums[mid + 1]
    private fun IntArray.maxSubArrayCross(left: Int, mid: Int, right: Int): Int {
        var leftSum = Integer.MIN_VALUE
        var sum = 0
        for (index in mid downTo left) {
            sum += this[index]
            leftSum = Math.max(leftSum, sum)
        }
        var rightSum = Integer.MIN_VALUE
        sum = 0
        for (index in mid + 1..right) {
            sum += this[index]
            rightSum = Math.max(rightSum, sum)
        }
        return leftSum + rightSum
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn)，树的高度是 lgn，每一层需要遍历数组
- 空间复杂度：O(lgn)，递归栈深度

在计算 leftSum 和 rightSum 时，存在重复加法

## 题解三（线段树）

分治法其实就是使用线段树求解 “区间最大子段和” 问题

我们定义线段树的节点 Node，并维持 4 个变量：
- 1、iSum：区间和
- 2、leftSum：左子区间的解
- 3、rightSum：右子区间的解
- 4、result：区间的解

现在我们从线段树的根节点开始，将原问题的解分解为线段树子节点的解

```
class Solution {

    fun maxSubArray(nums: IntArray): Int {
        return nums.getSum(0, nums.size - 1).result
    }

    private class Node(val iSum: Int, val leftSum: Int, val rightSum: Int, val result: Int)

    private fun IntArray.getSum(left: Int, right: Int): Node {
        // 终止条件
        if (left == right) return Node(this[left], this[left], this[left], this[left])
        // 左中位数
        val mid = (left + right) ushr 1
        // 分治
        val leftNode = this.getSum(left, mid)
        val rightNode = this.getSum(mid + 1, right)
        // 合并
        val iSum = leftNode.iSum + rightNode.iSum
        val leftSum = Math.max(leftNode.leftSum, leftNode.iSum + rightNode.leftSum)
        val rightSum = Math.max(rightNode.rightSum, rightNode.iSum + leftNode.rightSum)
        val result = Math.max(leftNode.rightSum + rightNode.leftSum, Math.max(leftNode.result, rightNode.result))
        return Node(iSum, leftSum, rightSum, result)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)，问题相当于遍历线段树的所有节点，每个节点的时间复杂度是 O(1)
- 空间复杂度：O(lgn)，递归栈深度
