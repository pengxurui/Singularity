## [剑指 Offer 38. 字符串的排列](https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/description/?favorite=xb9nqhhg)

## 题目描述

输入一个字符串，打印出该字符串中字符的所有排列。

## 题目考点

排列组合

## 题解一（回溯）
 
```
class Solution {
    fun permutation(s: String): Array<String> {
        return LinkedList<String>().apply {
            permutation(s.toList().sorted(), BooleanArray(s.length) { false }, StringBuilder(), this)
        }.toTypedArray()
    }

    private fun permutation(s: List<Char>, seens: BooleanArray, path: StringBuilder, result: MutableList<String>) {
        if (path.length == s.size) {
            result.add(path.toString())
            return
        }
        // 选择列表
        for (index in 0 until seens.size) {
            if (index > 0 && s[index] == s[index - 1] && !seens[index - 1]) {
                continue
            }
            if (!seens[index]) {
                // 选择
                seens[index] = true
                path.append(s[index])
                // 递归
                permutation(s, seens, path, result)
                // 回溯
                path.deleteCharAt(path.length - 1)
                seens[index] = false
            }
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n!) 不考虑处理每个排列的 O(n) 时间
- 空间复杂度：O(n) 递归栈

## 题解二（下一个排列）

我们先求得最小的排列，然后循环计算下一个排列，直到结束

```
class Solution {
    fun permutation(s: String): Array<String> {
        if (s.length <= 1) return arrayOf(s)
        // 循环计算下一个排列
        val sArray = s.toCharArray()
        sArray.sort()
        return LinkedList<String>().apply {
            add(String(sArray))
            while (nextPermutation(sArray)) {
                add(String(sArray))
            }
        }.toTypedArray()
    }

    // return false：没有下一个排列
    private fun nextPermutation(sArray: CharArray): Boolean {
        var i = sArray.size - 2
        while (i >= 0 && sArray[i] >= sArray[i + 1]) {
            i--
        }
        if (i < 0) return false
        var j = sArray.size - 1
        while (sArray[j] <= sArray[i]) {
            j--
        }
        sArray.swap(i, j)
        sArray.reversed(i + 1, sArray.size - 1)
        return true
    }

    private fun CharArray.swap(first: Int, second: Int) {
        val temp = this[first]
        this[first] = this[second]
        this[second] = temp
    }

    private fun CharArray.reversed(start: Int, end: Int) {
        var left = start
        var right = end
        while (left < right) {
            swap(left++, right--)
        }
    }
}
```


**复杂度分析：**

- 时间复杂度：O(n! + nlgn) 不考虑处理每个排列的 O(n) 时间
- 空间复杂度：O(n) 递归栈
