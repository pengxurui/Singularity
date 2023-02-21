## [剑指 Offer 03. 数组中重复的数字](https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof)

## 题目描述

找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

## 题目考点

散列表、桶

## 题解
 
```
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        // 方法 1：散列表
        // 方法 2：桶：数字的范围正好在 0~n-1，可以直接在数组上建立索引

        // 将数字交换到索引位置，如果索引位置存在相同数字，说明重复
        var index = 0
        while (index < nums.size) {
            if (nums[index] == index) {
                index++
                continue
            }
            if (nums[nums[index]] == nums[index]) {
                return nums[index]
            }
            val temp = nums[index]
            nums[index] = nums[temp]
            nums[temp] = temp
        }
        return -1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
