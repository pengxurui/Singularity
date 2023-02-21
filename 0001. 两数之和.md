## [1. Two Sum](https://leetcode-cn.com/problems/two-sum/)

## [LeetCode 题解](https://leetcode-cn.com/problems/two-sum/solution/1-two-sum-liang-shu-zhi-he-by-pengxurui/)

## 题目描述

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

## 题目大意

两数之和

## 解法一（哈希表）
```
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // value - index
        val map = HashMap<Int, Int>()
        for ((index, num) in nums.withIndex()) {
            val offset = target - num
            if (map.containsKey(offset)) {
                return intArrayOf(map[offset]!!, index)
            } else {
                map[num] = index
            }
        }
        return IntArray(0)
    }
}
```
**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
