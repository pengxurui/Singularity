## [剑指 Offer 57. 和为s的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/description/?favorite=xb9nqhhg)

## 题目描述

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

## 题目考点

双指针

## 题解
 
```
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return intArrayOf(nums[left], nums[right])
            }
            if (nums[left] + nums[right] < target) {
                left++
            }

            if (nums[left] + nums[right] > target) {
                right--
            }
        }
        return IntArray(0)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
