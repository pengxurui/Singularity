## [剑指 Offer 62. 圆圈中最后剩下的数字](https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/description/)

## 题目描述

0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

## 题目考点

动态规划、数学

- f[n,m] 约瑟夫环问题，表示最后留下的数字所处下标

如果我们知道对于一个长度 n - 1 的序列，留下的是第几个元素，那么我们就可以由此计算出长度为 n 的序列的答案

已知，f(n-1, m) 剩下 [x]，f(n, m) 删除 [(m-1) % n] 得到从 [m] 开始的新序列，

那么，f(n, m) 剩下从 m%n 开始的第 x 个元素 f(n, m) = (m % n + x) % n = (m + x) % n

## 题解一（递归）
 
```
class Solution {
    fun lastRemaining(n: Int, m: Int): Int {
        // dp[n,m] 约瑟夫环问题，表示最后留下的数字所处下标
        // 如果 n < m，那么删除的数是 (m - 1) % n，删除后得到一个 [n-1,m] 问题
        // 当我们知道 [n-1,m] 问题的解 x 后，就可以知道 [n,m] 的解是从 m%n 开始数的第 x 个数
        // [0,1,2,3,4,5] [5,2]
        return find(n, m)
    }


    private fun find(n: Int, m: Int): Int {
        if (n == 1) return 0
        val x = find(n - 1, m)
        return (m + x) % n
        // return ((m % n) + x) % n
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 

## 题解二（迭代）

```
class Solution {
    fun lastRemaining(n: Int, m: Int): Int {
        var f = 0 // f[1] = 0
        for (size in 1..n) {
            f = (f + m) % size
        }
        return f
    }
}
```

```
class Solution {
    fun lastRemaining(n: Int, m: Int): Int {
        // dp[n,m] 约瑟夫环问题，表示最后留下的数字所处下标
        // 如果 n < m，那么删除的数是 (m - 1) % n，删除后得到一个 [n-1,m] 问题
        // 当我们知道 [n-1,m] 问题的解 x 后，就可以知道 [n,m] 的解是从 m%n 开始数的第 x 个数
        // [0,1,2,3,4,5] [5,2]
        var pre = 0
        for (size in 1..n) {
            val cur = (pre + m) % size
            pre = cur
        }
        return pre
    }
}
```
**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
