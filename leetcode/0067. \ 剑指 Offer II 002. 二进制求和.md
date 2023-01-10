## [67. 二进制求和](https://leetcode.cn/problems/add-binary/description/)
## [剑指 Offer II 002. 二进制加法](https://leetcode.cn/problems/JFETK5/description/)

## 题目描述

给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。

## 题目考点

位运算

## 题解一（模拟加法）
 
```
class Solution {

    fun addBinary(a: String, b: String): String {
        val result = StringBuilder()
        var append = 0
        var pointA = a.length - 1
        var pointB = b.length - 1
        while (pointA >= 0 || pointB >= 0) {
            var cur = append
            if (pointA >= 0) {
                cur += a[pointA--] - '0'
            }
            if (pointB >= 0) {
                cur += b[pointB--] - '0'
            }
            result.append("${cur % 2}")
            append = cur / 2
        }
        if(append != 0) {
            result.append("1")
        }
        return result.reversed().toString()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(max{|a|,|b|})，加法的时间是 max{|a|,|b|}
- 空间复杂度：O(1)

## 题解二（附加不允许使用四则运算）

如果不允许四则运算，则可以使用位运算模拟出加法：

- 1、先将 a 和 b 转换为整型数字 x 和 y
- 2、在每一轮循环中，用 x 存储数据，用 y 存储进位信息，并循环：
 - 2.1 将 x^y，得到丢失进位的加法结果，并将结果放在 x
 - 2.2 将 x&y 并左移一位，恢复进位信息，并将进位信息放在 y

```
class Solution {

    fun addBinary(a: String, b: String): String {
        return if (a.length <= 63 && b.length <= 63) {
            addBinary1(a, b)
        } else {
            addBinary2(a, b)
        }
    }

    fun addBinary1(a: String, b: String): String {
        // 按二进制字符串解析为整型
        var x = a.toLong(2)
        var y = b.toLong(2)
        while (y != 0L) {
            // 丢失进位信息的加法结果
            val result = x xor y
            // 恢复进位信息
            val carry = (x and y) shl 1
            x = result
            y = carry
        }
        // 解析为二进制字符串
        return x.toString(2)
    }

    fun addBinary2(a: String, b: String): String {
        val result = StringBuilder()
        var append = 0
        var pointA = a.length - 1
        var pointB = b.length - 1
        while (pointA >= 0 || pointB >= 0) {
            var cur = append
            if (pointA >= 0) {
                cur += a[pointA--] - '0'
            }
            if (pointB >= 0) {
                cur += b[pointB--] - '0'
            }
            result.append("${cur % 2}")
            append = cur / 2
        }
        if (append != 0) {
            result.append("1")
        }
        return result.reversed().toString()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(|a| + |b| + max{|a|,|b|})，字符串转整型的时间是 |a| + |b|，加法的时间是 max{|a|,|b|}
- 空间复杂度：O(1)

