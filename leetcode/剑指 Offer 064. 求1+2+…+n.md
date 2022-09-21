## [剑指 Offer 64. 求1+2+…+n](https://leetcode.cn/problems/maximum-width-ramp/)

## 题目描述

给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。

找出 A 中的坡的最大宽度，如果不存在，返回 0 。

## 题目考点

单调栈

## 题解
 
```
class Solution {
    fun sumNums(n: Int): Int {

        var sum = 0

        fun recursiveAdd(n: Int): Boolean {
            sum += n
            return (n - 1) > 0 && recursiveAdd(n - 1)
        }

        recursiveAdd(n)

        return sum
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)、函数调用栈
