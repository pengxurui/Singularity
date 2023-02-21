## [剑指 Offer 56 - I. 数组中数字出现的次数](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

## 题目考点

位运算

## 题解
 
```
class Solution {
    fun singleNumbers(nums: IntArray): IntArray {
        var xorSum = 0
        for (element in nums) {
            xorSum = xorSum xor element
        }
        // 最后一个 1 的位置
        val last1Bit = xorSum and (-xorSum)
        var num1 = 0
        var num2 = 0
        for (element in nums) {
            if (element and last1Bit != 0) {
                num1 = num1 xor element
            } else {
                num2 = num2 xor element
            }
        }
        return intArrayOf(num1, num2)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
