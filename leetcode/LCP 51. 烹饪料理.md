## [LCP 51. 烹饪料理](https://leetcode.cn/problems/UEcfPD/description/)

## 题目描述

欢迎各位勇者来到力扣城，城内设有烹饪锅供勇者制作料理，为自己恢复状态。

勇者背包内共有编号为 0 ~ 4 的五种食材，其中 materials[j] 表示第 j 种食材的数量。通过这些食材可以制作若干料理，cookbooks[i][j] 表示制作第 i 种料理需要第 j 种食材的数量，而 attribute[i] = [x,y] 表示第 i 道料理的美味度 x 和饱腹感 y。

在饱腹感不小于 limit 的情况下，请返回勇者可获得的最大美味度。如果无法满足饱腹感要求，则返回 -1。

## 题目考点

回溯

## 题解
 
```
class Solution {

    private var result = -1

    fun perfectMenu(materials: IntArray, cookbooks: Array<IntArray>, attribute: Array<IntArray>, limit: Int): Int {
        // 方法 1：回溯 + 组合
        perfectMenu(cookbooks, attribute, materials, 0, 0, limit)
        return result
    }

    // cookbooks：菜谱
    // attribute：属性
    // materials：食材限制
    // start：组合起始索引
    // return：美味度
    private fun perfectMenu(cookbooks: Array<IntArray>, attribute: Array<IntArray>, materials: IntArray, start: Int, niceSum: Int, limit: Int) {
        // 记录结果
        if (limit <= 0) {
            result = Math.max(result, niceSum)
        }
        outer@ for (choice in start until cookbooks.size) {
            // 检查食材限制
            for (index in materials.indices) {
                if (materials[index] < cookbooks[choice][index]) continue@outer
            }
            // 选择
            for (index in materials.indices) {
                materials[index] -= cookbooks[choice][index]
            }
            // 递归
            perfectMenu(cookbooks, attribute, materials, choice + 1, niceSum + attribute[choice][0], limit - attribute[choice][1])
            // 回溯
            for (index in materials.indices) {
                materials[index] += cookbooks[choice][index]
            }
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(m·C(n,n)) m 是食材数量，n 是菜品数量
- 空间复杂度：O(n) 递归栈
