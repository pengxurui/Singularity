## [剑指 Offer 21. 调整数组顺序使奇数位于偶数前面](https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/description/)

## 题目描述

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。

## 题目考点

双指针

## 题解一
 
```
class Solution {
    fun exchange(nums: IntArray): IntArray {
        // 插入法：存在大量数据搬运，不考虑
        // 双指针：找到乱序的奇偶组合，并对换位置
        if(nums.size <= 1){
            return nums
        }
        
        var left = 0
        var right = nums.size - 1
        while (true) {
            while (1 == nums[left] % 2 && left < right) {
                // 奇数
                left++
            }
            while (0 == nums[right] % 2 && left < right) {
                // 偶数
                right--
            }
            if (left >= right) {
                break;
            }
            nums.swap(left, right)
        }
        return nums
    }

    fun IntArray.swap(first: Int, second: Int) {
        val temp = this[first]
        this[first] = this[second]
        this[second] = temp
    }
}
```

eg：[1,2,3,4,5,6] => [1,5,3,4,2,6]

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
