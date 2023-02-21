## [LCS 02. 完成一半题目](https://leetcode.cn/problems/WqXACV/description/)

## 题目描述

有 N 位扣友参加了微软与力扣举办了「以扣会友」线下活动。主办方提供了 2*N 道题目，整型数组 questions 中每个数字对应了每道题目所涉及的知识点类型。 若每位扣友选择不同的一题，请返回被选的 N 道题目至少包含多少种知识点类型。

## 题目考点

排序、计数

## 题解
 
```
class Solution {
    fun halfQuestions(questions: IntArray): Int {
        // [1,1000]
        val n = questions.size / 2
        val cnts = IntArray(1001)
        for (type in questions) {
            cnts[type]++
        }
        cnts.sort()
        var typeCount = 0
        var questionCount = 0
        for (index in cnts.size - 1 downTo 0) {
            typeCount++
            questionCount += cnts[index]
            if (questionCount >= n) break
        }
        return typeCount
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn + m) m 是类型数
- 空间复杂度：O(lgn + m) 
