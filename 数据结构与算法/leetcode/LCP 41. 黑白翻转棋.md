## [LCP 41. 黑白翻转棋](https://leetcode.cn/problems/fHi6rV/description/)

```
class Solution {

    private val directions = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(1, 1), intArrayOf(1, -1), intArrayOf(-1, 1), intArrayOf(-1, -1))

    fun flipChess(chessboard: Array<String>): Int {
        var ret = 0
        for (i in 0 until chessboard.size) {
            for (j in 0 until chessboard[0].length) {
                ret = Math.max(ret, bfs(chessboard, i, j))
            }
        }
        return ret
    }

    private fun bfs(chessboard: Array<String>, i: Int, j: Int) : Int {
        // 构造
        val n = chessboard.size
        val m = chessboard[0].length
        val board = Array(n) { CharArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                board[i][j] = chessboard[i][j]
            }
        }
        // BFS
        var cnt = 0
        val queue = LinkedList<IntArray>()
        queue.offer(intArrayOf(i, j))
        board[i][j] = 'X'
        while (!queue.isEmpty()) {
            val node = queue.poll()
            val x = node[0]
            val y = node[1]
            for (direction in directions) {
                if (check(board, x, y, direction)) {
                    // 入队
                    var newX = x
                    var newY = y
                    while (true) {
                        newX += direction[0]
                        newY += direction[1]
                        if (newX < 0 || newX >= n || newY < 0 || newY >= m || board[newX][newY] == 'X') break
                        board[newX][newY] = 'X'
                        queue.offer(intArrayOf(newX, newY))
                        cnt++
                    }
                }
            }
        }
        return cnt
    }

    private fun check(board: Array<CharArray>, x: Int, y: Int, direction: IntArray) : Boolean {
        val n = board.size
        val m = board[0].size
        var newX = x
        var newY = y
        while (true) {
            newX += direction[0]
            newY += direction[1]
            if (newX < 0 || newX >= n || newY < 0 || newY >= m) return false
            if (board[newX][newY] == 'X') return true
            if (board[newX][newY] == '.') return false
        }
    }
}
```

- O(nm * nm * max{n, m})
- O(nm)
