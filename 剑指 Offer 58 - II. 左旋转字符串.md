## [剑指 Offer 58 - II. 左旋转字符串](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg)

## 题目描述

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

## 题目考点

字符串

## 题解
 
```
class Solution {
    fun reverseLeftWords(s: String, n: Int): String {
        val k = n % s.length
        val builder = StringBuilder(s.length)
        for (index in k until s.length) {
            builder.append(s[index])
        }
        for (index in 0 until k) {
            builder.append(s[index])
        }
        return builder.toString()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
