## [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/description/)
## [剑指 Offer 29. 顺时针打印矩阵](https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/?favorite=xb9nqhhg)

## 题目描述

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

## 题目考点

遍历

## 题解一（由外层向内层输出）
 
```
import java.util.*

class Solution {
    fun spiralOrder(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return IntArray(0)

        val resultList = LinkedList<Int>()

        var startRow = 0
        var startColumn = 0
        var endRow = matrix.size - 1
        var endColumn = matrix[0].size - 1

        while (startRow <= endRow && startColumn <= endColumn) {
            // 向右
            for (column in startColumn..endColumn) {
                resultList.add(matrix[startRow][column])
            }
            // 向下
            for (row in startRow + 1..endRow) {
                resultList.add(matrix[row][endColumn])
            }
            // 过滤重复的向左和向上
            if (startRow < endRow && startColumn < endColumn) {
                // 向左
                for (column in endColumn - 1 downTo startColumn) {
                    resultList.add(matrix[endRow][column])
                }
                // 向上
                for (row in endRow - 1 downTo startRow + 1) {
                    resultList.add(matrix[row][startColumn])
                }
            }
            startRow++
            startColumn++
            endRow--
            endColumn--
        }
        return resultList.toIntArray()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(m·n)
- 空间复杂度：O(1) 
