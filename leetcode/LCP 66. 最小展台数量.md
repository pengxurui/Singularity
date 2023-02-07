## [LCP 66. 最小展台数量](https://leetcode.cn/problems/600YaG/description/)

## 题目描述

力扣嘉年华将举办一系列展览活动，后勤部将负责为每场展览提供所需要的展台。 已知后勤部得到了一份需求清单，记录了近期展览所需要的展台类型， demand[i][j] 表示第 i 天展览时第 j 个展台的类型。 在满足每一天展台需求的基础上，请返回后勤部需要准备的 最小 展台数量。

注意：

同一展台在不同天中可以重复使用。

## 题目考点

计数

## 题解
 
```
class Solution {
    fun minNumBooths(demand: Array<String>): Int {
        val maxCnts = IntArray(26)
        val cnts = IntArray(26)
        for (day in demand) {
            Arrays.fill(cnts, 0)
            for (table in day) {
                cnts[table - 'a']++
            }
            for (index in cnts.indices) {
                maxCnts[index] = Math.max(maxCnts[index], cnts[index])
            }
        }
        return maxCnts.sum()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·m)
- 空间复杂度：O(26) 
