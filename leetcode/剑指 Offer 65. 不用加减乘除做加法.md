## [剑指 Offer 65. 不用加减乘除做加法](https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/description/?favorite=xb9nqhhg)

## 题目描述

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

## 题目考点

位运算

## 题解
 
```
class Solution {
    fun add(a: Int, b: Int): Int {
        // 用两个变量：a 存储答案，b 存储缺失的进位信息
        // 在每一轮运算中，使用 a ^ b 求得无进位的加法，再使用 (a & b) << 1 获得进位信息
        var numA = a
        var numB = b
        while (0 != numB) {
            val answer = numA xor numB
            val append = (numA and numB) shl 1
            numA = answer
            numB = append
        }
        return numA
    }
}
```

**复杂度分析：**

- 时间复杂度：O(1)
- 空间复杂度：O(1) 
