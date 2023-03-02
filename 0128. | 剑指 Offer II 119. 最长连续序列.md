## [128. 最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence/)
## [剑指 Offer II 119. 最长连续序列](https://leetcode.cn/problems/WhsWhI/)

## 题目描述

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

## 题目考点

散列表

## 题解
 
我们考虑枚举数组中的每个数 x，考虑以其为起点，不断尝试匹配 x+1,x+2,⋯ 是否存在，假设最长匹配到了 x+y，那么以 x 为起点的最长连续序列即为 [x,x+y]，其长度为 y + 1，我们不断枚举并更新答案即可。

为了优化内层循环，我们发现如果之前尝试过 x，那么将来尝试 x + 1 开始一定不会取得更优解。

所以每次在尝试之前，我们先检查 x - 1 是否存在，如果存在则不需要尝试。

即：只会从连续序列的最小数开始尝试

```
class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toHashSet()
        var result = 0
        for (element in nums) {
            if (set.contains(element - 1)) continue
            var curCount = 1
            while (set.contains(element + curCount)) {
                curCount++
            }
            result = Math.max(result, curCount)
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
