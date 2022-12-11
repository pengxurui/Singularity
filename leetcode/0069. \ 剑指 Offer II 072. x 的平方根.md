## [69. x 的平方根 ](https://leetcode.cn/problems/sqrtx/description/)
## [剑指 Offer II 072. 求平方根](https://leetcode.cn/problems/jJ0w9p/description/)

## 题目描述

给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。

## 题目考点

二分查找

## 题解
 
```
class Solution {
    fun mySqrt(x: Int): Int {
        if (x < 2) return x
        var left  = 1L
        var right = x.toLong()
        while (left < right) {
            val mid = (left + right + 1) ushr 1
            val mul : Long = mid * mid
            // 溢出
            if (mul > x.toLong()) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return left.toInt()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(1) 
