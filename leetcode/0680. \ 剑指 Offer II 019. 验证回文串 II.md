## [680. 验证回文串 II](https://leetcode.cn/problems/valid-palindrome-ii/)
## [剑指 Offer II 019. 最多删除一个字符得到回文](https://leetcode.cn/problems/RQku0D/description/)

## 题目描述

给你一个字符串 s，最多 可以从中删除一个字符。

请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。

## 题目考点

双指针

## 题解
 
选择试错：双指针向字符串中间逼近，如果出现不同，则尝试删除移除这两个字符，在检查中间的子串是否为回文

```
class Solution {
    fun validPalindrome(s: String): Boolean {
        // aba
        // abca
        // cbca
        var left = 0
        var right = s.length - 1
        while (left < right) {
            if (s[left] == s[right]) {
                left++
                right--
                continue
            }
            return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)
        }
        return true
    }

    // return：是否回文串
    private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var left = start
        var right = end
        while (left < right) {
            if (s[left++] != s[right--]) return false
        }
        return true
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
