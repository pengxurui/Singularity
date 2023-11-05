## [1857A - Array Coloring](https://codeforces.com/contest/1857/problem/A)

## 题解（数学）

增加偶数对子集总和的奇偶性没有影响，问题仅取决于奇数的个数是否为偶数

```
fun main(args: Array<String>) {
    val tests = Array(nextInt()) {
        nextArrayI(nextInt())
    }
    // ------------------------------------
    for (test in tests) {
        var cntOdd = 0 // 奇数
        for (e in test) {
            if (e and 1 != 0) cntOdd++
        }
        println(if (cntOdd and 1 == 0) "YES" else "NO")
    }
    done()
}
```

- O(n)
- O(1)
