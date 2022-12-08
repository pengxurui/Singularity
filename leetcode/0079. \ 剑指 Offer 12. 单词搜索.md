## [79. 单词搜索](https://leetcode.cn/problems/word-search/description/)
## [剑指 Offer 12.](https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/?favorite=xb9nqhhg)

## 题目描述

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

## 题目考点

搜索

## 题解
 
```
class Solution {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (board.isEmpty() || board[0].isEmpty() || word.length < 1) return false
        // 选择列表
        val flagBoard = Array<BooleanArray>(board.size) {
            BooleanArray(board[0].size) { false }
        }
        // 从每个起点开始遍历
        for (row in 0 until board.size) {
            for (column in 0 until board[0].size) {
                if (board.search(word, flagBoard, 0/* 单词游标 */, row, column/* 起点 */)) {
                    return true
                }
            }
        }
        return false
    }

    // return：是否满足目标条件
    fun Array<CharArray>.search(word: String, flagBoard: Array<BooleanArray>, pivot: Int, row: Int, column: Int): Boolean {
        // 终止条件
        if (pivot == word.length) return true
        // 越界
        if (row < 0 || row >= this.size || column < 0 || column >= this[0].size) return false
        // 重复索引
        if (flagBoard[row][column]) return false
        // 不匹配
        if (this[row][column] != word[pivot]) return false
        // 选择
        flagBoard[row][column] = true
        // 递归
        val directions = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(-1, 0))
        for (direction in directions) {
            if (this.search(word, flagBoard, pivot + 1, row + direction[0], column + direction[1])) {
                //满足目标条件
                return true
            }
        }
        // 回溯
        flagBoard[row][column] = false
        return false
    }
}
```

**复杂度分析：**

设字符串长度为 L：
在每一趟遍历中，除了首个节点外有 3 个方向可以选择，所以搜索时间复杂度是 O(3^L)。而我们又 M\*N 个起始方案，所以整体的时间复杂度是 O(M\*N\*3^L)

- 时间复杂度：O(M\*N\*3^L)
- 空间复杂度：O(L)
