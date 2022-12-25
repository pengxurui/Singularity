## [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/)
## [剑指 Offer 53 - I. 在排序数组中查找数字 I](https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/description/?favorite=xb9nqhhg)

## 题目描述

给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

## 题目考点

二分查找

## 题解
 
```
class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        val firstIndex = nums.searchFirst(target)
        return if (-1 == firstIndex)
            intArrayOf(-1, -1)
        else
            intArrayOf(firstIndex, nums.searchLast(target, firstIndex))
    }

    // 寻找第一个大于等于目标数的节点
    private fun IntArray.searchFirst(target: Int): Int {
        var left = 0
        var right = size - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            if (this[mid] >= target) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return if (this[left] == target) left else -1
    }

    // 寻找最后一个小于等于目标数的节点
    private fun IntArray.searchLast(target: Int, first: Int): Int {
        var left = first
        var right = size - 1
        while (left < right) {
            val mid = (left + right + 1) ushr 1
            if (this[mid] <= target) {
                left = mid
            } else {
                right = mid - 1
            }
        }
        return if (this[left] == target) left else -1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(1) 
