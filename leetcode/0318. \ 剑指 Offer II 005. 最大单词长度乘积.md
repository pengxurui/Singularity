## [318. 最大单词长度乘积](https://leetcode.cn/problems/maximum-product-of-word-lengths/description/)
## [剑指 Offer II 005. 单词长度的最大乘积](https://leetcode.cn/problems/aseY1I/?favorite=e8X3pBZi)

## 题目描述

乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。

## 题目考点

位运算

## 题解一（暴力）
 
```
class Solution {
    fun maxProduct(words: Array<String>): Int {
        // 暴力
        val cnts = Array(words.size) {
            IntArray(26).apply {
                for (element in words[it]) {
                    this[element - 'a'] = 1
                }
            }
        }
        var result = 0
        for (i in 0 until words.size) {
            for (j in i until words.size) {
                if (compare(cnts[i], cnts[j])) continue
                result = Math.max(result, words[i].length * words[j].length)
            }
        }
        return result
    }

    private fun compare(cnt1: IntArray, cnt2: IntArray): Boolean {
        for (index in cnt1.indices) {
            if (cnt1[index] == 1 && cnt2[index] == 1) return true
        }
        return false
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n^2·m + L) L 是所有单词的长度之和，m 是 26
- 空间复杂度：O(n·m) 

## 题解二（位运算）

我们可以用二进制掩码代替 cnt 数组，从而将判断两个数组是否相等的时间复杂度降低到 O(1)

```
class Solution {
    fun maxProduct(words: Array<String>): Int {
        val masks = IntArray(words.size)
        for (index in words.indices) {
            for (element in words[index]) {
                masks[index] = masks[index] or (1 shl element - 'a')
            }
        }
        var result = 0
        for (i in 0 until words.size) {
            for (j in i until words.size) {
                if (masks[i] and masks[j] == 0) result = Math.max(result, words[i].length * words[j].length)
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n^2 + L) L 是所有单词的长度之和
- 空间复杂度：O(n) 

## 题解三（位运算 + 优化）

我们发现掩码相同的单词，最终结果取决于最长的单词。例如 meet 和 met 的掩码相同，而最终结果应该取决于 meet，而不可能取决于 met，所以 met 这个元素在 n^2 的循环里没有必要存在。

因此，我们使用散列表记录每种不同掩码的最长单词长度，在遍历每种掩码组合

```
class Solution {
    fun maxProduct(words: Array<String>): Int {
        // mask to maxLength
        val masks = HashMap<Int, Int>()
        for (index in words.indices) {
            var mask = 0
            for (element in words[index]) {
                mask = mask or (1 shl element - 'a')
            }
            // 记录最大长度
            masks[mask] = Math.max(masks.getOrDefault(mask, 0), words[index].length)
        }
        var result = 0
        val maskKeys = masks.keys
        for (mask1 in maskKeys) {
            for (mask2 in maskKeys) {
                if (mask1 == mask2) continue
                if (mask1 and mask2 == 0) result = Math.max(result, masks[mask1]!! * masks[mask2]!!)
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(M^2 + L) L 是所有单词的长度之和，M 是掩码种类，最坏情况下每个单词的掩码都不同（M=n)
- 空间复杂度：O(M) 
