## [剑指 Offer 50. 第一个只出现一次的字符](https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

## 题目考点

散列表

## 题解
 
```
class Solution {
    fun firstUniqChar(s: String): Char {
        if (s.isEmpty()) return ' '

        val map = HashMap<Char, Int>()
        for (element in s) {
            map[element] = map.getOrDefault(element, 0) + 1
        }
        for (element in s) {
            if (map[element] == 1) {
                return element
            }
        }
        return ' '
    }
}
```

**复杂度分析：**

- 时间复杂度：O(N) 字符串长度
- 空间复杂度：O(M) 不同的字符个数
