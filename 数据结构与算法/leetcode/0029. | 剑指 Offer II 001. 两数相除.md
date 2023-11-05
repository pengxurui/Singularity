## [29. 两数相除](https://leetcode.cn/problems/divide-two-integers/)
## [剑指 Offer II 001. 整数除法](https://leetcode.cn/problems/xoh6Oh/)

## 题目描述

给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。

## 题目考点

位运算、二分查找、快速乘

## 题解一（不限制 long）

- 1、先将参数转为正数
- 2、在除数不为 [0,1] 的小数的前提下，除法的结果一定位于 [0,被除数] 区间
- 3、在结果区间内，我们可以用二分查找：
  - 如果 mid * 除数 > 被除数，那么 mid 及其右区间不是解
  - 反之，mid 和右区间是解
- 4、由于题目禁止使用乘法，所以需要找到替代乘法的方法：

- 以 a * 11 为例，11 = 2^0 + 2^1 + 2^3（1011_2），因此 a * 11 = a·2^0 + a·2^1 + + 0·a· 2^2 + a·2^3

我们可以使用从低位向高位遍历，每次移动指针时执行 a+=a 将权重倍增，在遇到位 1 的位置将权重累加到答案中。

```
class Solution {
    fun divide(dividend: Int, divisor: Int): Int {
        // 负数标记
        val isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)
        // 将参数转为正数(如果参数是 -2147483648，直接转正数会超过最大的整数)
        val a: Long = if (dividend > 0) dividend.toLong() else -(dividend.toLong())
        val b: Long = if (divisor > 0) divisor.toLong() else -(divisor.toLong())
        // 二分查找
        var left = 0L
        var right = a.toLong()
        while (left < right) {
            val mid = (left + right + 1) ushr 1
            // mid * divisor
            if (mul(mid, b.toLong()) > a.toLong()) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        if (isNegative) left = -left
        if (left > Integer.MAX_VALUE) return Integer.MAX_VALUE
        if (left < Integer.MIN_VALUE) return Integer.MIN_VALUE
        return left.toInt()
    }

    // 快速乘
    private fun mul(a: Long, b: Long): Long {
        var x = a
        var y = b
        var result = 0L
        while (0L != y) {
            if (y and 1 == 1L) result += x
            x += x
            y = y ushr 1
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn * lgn) 每一步需要 lgn 时间计算快速乘
- 空间复杂度：O(1) 

## 题解二（限制 long）TODO

由于负数范围比正数范围大一个数，所以我们选择事先将参数都转换为负数的方式

```
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(1) 
