## [338. 比特位计数](https://leetcode.cn/problems/counting-bits/)
## [剑指 Offer II 003. 前 n 个数字二进制中 1 的个数](https://leetcode.cn/problems/w3tCBm/description/)

## 题目描述

给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。

## 题目考点

- 思路 1 - 暴力：直接计算每个数中 1 的个数，可以使用 Brian Kernighan 算法计算 1 的个数（让 x & x - 1 把最后一位 1 置为 0，计算操作次数）
- 思路 2 - 动态规划：问题定义：f(n) 表示 n 中 1 的个数，则有 f(n) = f(n - highBit) + 1，例如 数111 = 数11 + 1（多一个最高位 1）
- 思路 3 - 奇偶数规律：奇数一定比上一个偶数多一个1(2:10, 3:11)，偶数一定比右移一位的数的个数相同(3:11, 6:110)

## 题解一（暴力）
 
```
class Solution {
    fun countBits(n: Int): IntArray {
        fun oneCount(n: Int): Int {
            var x = n
            var count = 0
            while (0 != x) {
                x = x and (x - 1)
                count++
            }
            return count
        }

        return IntArray(n + 1) {
            oneCount(it)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn)
- 空间复杂度：O(1) 

## 解法二（动态规划·递推）

```
class Solution {
    fun countBits(n: Int): IntArray {
        // 维护最高有效位
        var highBit = 0
        return IntArray(n + 1) { 0 }.apply {
            for (num in 1..n) {
                if (0 == num and num - 1) {
                    highBit = num
                }
                this[num] = this[num - highBit] + 1
            }
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 

## 题解三（奇偶数规律）

```
class Solution {
    fun countBits(n: Int): IntArray {
        return IntArray(n + 1) { 0 }.apply {
            for (index in 0..n) {
                if (0 == index % 2) {
                    // 偶数
                    this[index] = this[index ushr 1]
                } else {
                    // 奇数
                    this[index] = this[index - 1] + 1
                }
            }
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
