## [剑指 Offer 53 - II. 0～n-1中缺失的数字](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/description/?favorite=xb9nqhhg)

## 题目描述

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

## 题目考点

二分查找

## 题解
 
```
class Solution {
    fun missingNumber(nums: IntArray): Int {
        // 等价于找出第一个 value > index 的 value
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            if (nums[mid] == mid) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return if(nums[left] == left) nums.size else left
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(1) 
