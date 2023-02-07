## [LCP 39. 无人机方阵](https://leetcode.cn/problems/0jQkd0/description/)

## 题目描述

在 「力扣挑战赛」 开幕式的压轴节目 「无人机方阵」中，每一架无人机展示一种灯光颜色。 无人机方阵通过两种操作进行颜色图案变换：

调整无人机的位置布局
切换无人机展示的灯光颜色
给定两个大小均为 N*M 的二维数组 source 和 target 表示无人机方阵表演的两种颜色图案，由于无人机切换灯光颜色的耗能很大，请返回从 source 到 target 最少需要多少架无人机切换灯光颜色。

注意： 调整无人机的位置布局时无人机的位置可以随意变动。

## 题目考点

计数

## 题解
 
```
class Solution {
    fun minimumSwitchingTimes(source: Array<IntArray>, target: Array<IntArray>): Int {
        // [1,10000]
        val sourceCnts = IntArray(10001)
        for (row in source) {
            for (element in row) {
                sourceCnts[element]++
            }
        }
        var sameCount = 0
        for (row in target) {
            for (element in row) {
                if (sourceCnts[element] > 0) {
                    sourceCnts[element]--
                    sameCount++
                }
            }
        }
        return source.size * source[0].size - sameCount
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·m)
- 空间复杂度：O(10000) 
