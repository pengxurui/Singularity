## [242. 有效的字母异位词](https://leetcode.cn/problems/valid-anagram/description/)
## [剑指 Offer II 032. 有效的变位词](https://leetcode.cn/problems/dKk3P7/?favorite=e8X3pBZi)

## 题目描述

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。

## 题目考点

滑动窗口

## 题解一（排序）
 
```
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        val arrayS = s.toCharArray()
        val arrayT = t.toCharArray()
        Arrays.sort(arrayS)
        Arrays.sort(arrayT)
        return Arrays.equals(arrayS, arrayT)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn)
- 空间复杂度：O(lgn) 

## 题解二（滑动窗口）

```
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val diffs = IntArray(26) { 0 }
        for (index in s.indices) {
            diffs[s[index] - 'a']++
            diffs[t[index] - 'a']--
        }

        var diffCount = 0
        for (diff in diffs) {
            if (0 != diff) diffCount++
        }
        return 0 == diffCount
    }
}
```

叠加 “字符顺序不完全相同” 条件：

```
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val diffs = IntArray(26) { 0 }
        for (index in s.indices) {
            diffs[s[index] - 'a']++
            diffs[t[index] - 'a']--
        }

        var diffCount = 0
        for (diff in diffs) {
            if (0 != diff) diffCount++
        }
        return 0 == diffCount && s != t
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(26) 
