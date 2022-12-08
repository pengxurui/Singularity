## [191. 位1的个数](https://leetcode.cn/problems/number-of-1-bits/description/)
## [剑指 Offer 15. 二进制中1的个数](https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/description/?favorite=xb9nqhhg)

## 题目描述

编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。

## 题目考点

位运算

## 题解一（无符号位移）
 
```
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n)
    {
        // 不断无符号右移
        int result = 0;
        while (0 != n) {
            // 检查最低位是否为 1
            if ((n & 1) == 1)  result +=1;
            n = n >>> 1;
        }
        return result;
    }
}
```

**复杂度分析：**

- 时间复杂度：O(k)，k = 32
- 空间复杂度：O(1)

## 题解二（与)

n & (n−1)，其预算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。

```
public class Solution {
    public int hammingWeight(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }
}
```

- 时间复杂度：O(lgn)，最坏情况下 n 的二进制位全部为 1，需要循环 log n 次。
- 空间复杂度：O(1)
