## [剑指 Offer 17. 打印从1到最大的n位数](https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

## 题目考点

位运算

## 题解
 
```
class Solution {
    fun printNumbers(n: Int): IntArray {
        var sum = 0
        var num = n
        while (num != 0) {
            sum = sum * 10 + 9
            num--
        }
        return IntArray(sum) { it + 1 }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(C^n)
- 空间复杂度：O(1)
