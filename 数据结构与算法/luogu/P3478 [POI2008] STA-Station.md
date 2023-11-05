## [P3478 [POI2008] STA-Station](https://www.luogu.com.cn/problem/P3478)

## 题解（换根 DP）

DFS：

```
fun main(args: Array<String>) {
    Reader.takeFile()
    val n = nextInt()
    val graph = Array(n + 1) { LinkedList<Int>() }
    repeat(n - 1) {
        val (from, to) = nextArrayI(2)
        graph[from].add(to)
        graph[to].add(from)
    }
    val son = IntArray(n + 1)
    val dp = IntArray(n + 1)
    // 以 1 为根节点
    fun dfs(i: Int, fa: Int, depth: Int) {
        dp[1] += depth
        son[i] += 1
        for (to in graph[i]) {
            if (to == fa) continue
            dfs(to, i, depth + 1)
            son[i] += son[to]
        }
    }

    fun dp(i: Int, fa: Int) {
        for (to in graph[i]) {
            if (to == fa) continue
            // 状态转移
            dp[to] = dp[i] + n - 2 * son[to]
            dp(to, i)
        }
    }

    dfs(1, -1, 0)
    dp(1, -1)

    var max = 1
    for (i in 1 .. n) {
        if (dp[i] > dp[max]) max = i
    }
    println(max)
}
```

- O(n)
- O(n)
