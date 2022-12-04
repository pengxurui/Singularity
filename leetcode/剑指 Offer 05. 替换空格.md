## [剑指 Offer 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/?favorite=xb9nqhhg)

## 题目描述

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

## 题目考点

字符串

## 题解
 
```
class Solution {
    fun replaceSpace(s: String): String {
        return StringBuilder().apply {
            for (element in s) {
                if (element == ' ') {
                    append("%20")
                } else {
                    append(element)
                }
            }
        }.toString()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
