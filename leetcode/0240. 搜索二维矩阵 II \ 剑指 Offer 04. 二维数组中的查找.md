## [240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii)
## [剑指 Offer 04. 二维数组中的查找](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof)

## 题目描述

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。

## 题目考点

二分搜索、二维数组

## 题解一（二分搜索）
 
```
class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if(matrix.isEmpty() || matrix[0].isEmpty()) return false

        // 方法 1：二维搜索
        // 方法 2：二分搜索，对每一行执行二分搜索
        // 方法 3：逻辑 BST 搜索，从右上角为根节点，整个二维数组形如 BST

        for (array in matrix) {
            if (array.find(target)) {
                return true
            }
        }
        return false
    }

    private fun IntArray.find(target: Int): Boolean {
        var left = 0
        var right = size - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            if (this[mid] < target) {
                // 左边严格不是解
                left = mid + 1
            } else {
                right = mid
            }
        }
        return this[left] == target
    }
}
```

**复杂度分析：**

- 时间复杂度：O(row\*lg(column))
- 空间复杂度：O(1)

## 题解二（逻辑 BST）

[参考](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solutions/95306/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-zuo/)
 
```
class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false

        // 方法 1：二维搜索
        // 方法 2：二分搜索，对每一行执行二分搜索
        // 方法 3：逻辑 BST 搜索，从右上角为根节点，整个二维数组形如 BST

        var row = 0
        var column = matrix[0].size - 1

        while (row <= matrix.size - 1 && column >= 0) {
            if (matrix[row][column] == target) return true

            if (matrix[row][column] < target) {
                row++
            } else {
                column--
            }
        }
        return false
    }
}
```

**复杂度分析：**

- 时间复杂度：O(row + column)
- 空间复杂度：O(1)
