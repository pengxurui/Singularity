## [852. 山脉数组的峰顶索引](https://leetcode.cn/problems/peak-index-in-a-mountain-array/)
## [剑指 Offer II 069. 山峰数组的顶部](https://leetcode.cn/problems/B1IidL/)

## 题目描述

符合下列属性的数组 arr 称为 山脉数组 ：

arr.length >= 3

存在 i（0 < i < arr.length - 1）使得：

arr[0] < arr[1] < ... arr[i-1] < arr[i] 

arr[i] > arr[i+1] > ... > arr[arr.length - 1]

给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。

## 题目考点

二分查找

## 题解
 
```
class Solution {
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var left = 0
        var right = arr.size - 1
        while (left < right) {
            val mid = (left + right + 1) ushr 1
            // 如果 [mid] 严格小于左边，那么 mid 一定不是解
            if (mid > 0 && arr[mid] < arr[mid - 1]) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return left
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(1) 
