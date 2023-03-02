## [151. 反转字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string/description/)
## [剑指 Offer 58 - I. 翻转单词顺序](https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/?favorite=xb9nqhhg)

## 题目描述

给你一个字符串 s ，请你反转字符串中 单词 的顺序。

## 题目考点

字符串

## 题解一（库函数）
 
```
class Solution {
    fun reverseWords(s: String): String {
        // 分割、逆序、链接
        return s.trim().split("\\s+".toRegex()).reversed().joinToString(" ")
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 

## 题解二（栈）

```
class Solution {
    fun reverseWords(s: String): String {
        // 将单词入栈，再将单词出栈
        val stack = ArrayDeque<String>()

        var left = 0
        var right = s.length - 1

        while (left <= right && ' ' == s[left]) {
            left++
        }
        while (left <= right && ' ' == s[right]) {
            right--
        }

        val wordBuilder = StringBuilder()
        for (index in left..right) {
            if (' ' == s[index]) {
                if (!wordBuilder.isEmpty()) {
                    // 新单词
                    stack.push(wordBuilder.toString())
                    wordBuilder.setLength(0)
                }
            } else {
                wordBuilder.append(s[index])
            }
        }
        if (!wordBuilder.isEmpty()) {
            stack.push(wordBuilder.toString())
        }
        return stack.joinToString(" ")
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
