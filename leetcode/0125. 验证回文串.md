## [125. 验证回文串](https://leetcode.cn/problems/valid-palindrome/)
## [剑指 Offer II 018. 有效的回文](https://leetcode.cn/problems/XltzEq/?favorite=e8X3pBZi)

## 题目描述

如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。

字母和数字都属于字母数字字符。

给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。

## 题目考点

双指针

## 题解
 
```
class Solution {
    fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1
        while (left < right) {
            if (!s[left].isLetterOrDigit()) {
                left++
                continue
            }
            if (!s[right].isLetterOrDigit()) {
                right--
                continue
            }
            if (s[left].toLowerCase() != s[right].toLowerCase()) {
                return false
            } else {
                left++
                right--
            }
        }
        return true
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
