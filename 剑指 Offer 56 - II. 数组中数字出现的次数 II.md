## [剑指 Offer 56 - II. 数组中数字出现的次数 II](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/description/?favorite=xb9nqhhg)

## 题目描述

在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

## 题目考点

位运算

## 题解

参考：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solutions/215895/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/

```
class Solution {
    fun singleNumber(nums: IntArray): Int {
        val cnts = IntArray(32)
        // 统计每一位上 1 的计数
        for (element in nums) {
            for (index in 0..31) {
                if (element and (1 shl index) != 0) {
                    cnts[index]++
                }
            }
        }
        // 取余
        var result = 0
        for (index in 31 downTo 0) {
            result = result shl 1
            result += cnts[index] % 3
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
