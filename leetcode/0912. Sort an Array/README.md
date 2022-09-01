## [990. 等式方程的可满足性](https://leetcode.cn/problems/satisfiability-of-equality-equations/)

## 题目描述

给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

## 题目考点

并查集

## 题解一

```
class Solution {
    fun equationsPossible(equations: Array<String>): Boolean {
        // 26 个小写字母的并查集
        val unionFind = UnionFind()

        // 合并所有等式
        for (equation in equations.filter { it[1] == '=' }) {
            unionFind.union(equation.first(), equation.second())
        }
        // 检查不等式是否与连通性冲突
        for (equation in equations.filter { it[1] == '!' }) {
            if (unionFind.isConnected(equation.first(), equation.second())) {
                return false
            }
        }
        return true
    }

    private fun String.first(): Int {
        return this[0].toInt() - 97
    }

    private fun String.second(): Int {
        return this[3].toInt() - 97
    }

    private class UnionFind() {

        val parent = IntArray(26) { it }

        fun find(x: Int): Int {
            var key = x
            while (key != parent[key]) {
                key = parent[key]
            }
            return key
        }

        fun union(key1: Int, key2: Int) {
            val root1 = find(key1)
            val root2 = find(key2)
            parent[root1] = root2
        }

        fun isConnected(key1: Int, key2: Int): Boolean {
            return find(key1) == find(key2)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：假设有 C 个字母，N 个等式，则单次合并操作或查询操作的时间复杂度是 O(lgC)，总体时间复杂度是 O(NlgC)
- 空间复杂度：O(C) 使用固定长度的数组

## 题解二（使用路径压缩与按秩合并优化）

```
class Solution {
    fun equationsPossible(equations: Array<String>): Boolean {
        // 26 个小写字母的并查集
        val unionFind = UnionFind(26)

        // 合并所有等式
        for (equation in equations.filter { it[1] == '=' }) {
            unionFind.union(equation.first(), equation.second())
        }
        // 检查不等式是否与连通性冲突
        for (equation in equations.filter { it[1] == '!' }) {
            if (unionFind.isConnected(equation.first(), equation.second())) {
                return false
            }
        }
        return true
    }

    private fun String.first(): Int {
        return this[0].toInt() - 97
    }

    private fun String.second(): Int {
        return this[3].toInt() - 97
    }

    private class UnionFind(n : Int) {

        // 父节点关系
        private val parent = IntArray(n) { it }

        // 树的高度
        private val rank = IntArray(n) { 1 }

        // 查询（隔代压缩）
        fun find(x: Int): Int {
            var key = x
            while (key != parent[key]) {
                parent[key] = parent[parent[key]] 
                key = parent[key]
            }
            return key
        }

        // 合并（按秩合并）
        fun union(key1: Int, key2: Int) {
            val root1 = find(key1)
            val root2 = find(key2)

            if(rank[root1] > rank[root2]){
                // root1 的高度更大，让 root2 成为子树
                parent[root2] = root1
            }else if(rank[root2] > rank[root1]){
                // root2 的高度更大，让 root1 成为子树
                parent[root1] = root2
            }else{
                // 高度相同，谁当子树都一样
                parent[root1] = root2
                rank[root1] ++
                //  或
                //  parent[root2] = root1
                //  rank[root2] ++
            }
        }

        fun isConnected(key1: Int, key2: Int): Boolean {
            return find(key1) == find(key2)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：在同时使用路径压缩和按秩合并两种优化策略时，算法在最坏情况下的时间复杂度几乎是线性的。以对 C 个字母进行 N 个等式为例，单次操作的时间复杂度是 O(a(C))，而整体的时间复杂度是 O(N·a(C))。其中 a(x) 是阿克曼函数，是一个增长非常非常慢的函数，只有使用那些非常大的 “天文数字” 作为变量 x，否则 a(x) 的取值都不会超过 4，基本上可以当作常数。然而，阿克曼函数毕竟不是常数，因此我们不能说并查集的时间复杂度是线性的，但也几乎是线性的。
- 空间复杂度：在同时使用路径压缩和按秩合并两种优化策略时，单次合并操作或查询操作的时间复杂度几乎是常量，整体的时间复杂度几乎是线性的。
