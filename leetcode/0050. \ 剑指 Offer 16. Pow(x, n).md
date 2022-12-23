## [50. Pow(x, n)](https://leetcode.cn/problems/powx-n)
## [剑指 Offer 16. 数值的整数次方](https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/description/?favorite=xb9nqhhg)

## 题目描述

实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。

## 题目考点

快速幂

## 题解一（分治 + 散列表）
 
```
class Solution {

    private val map = HashMap<Int,Double>()

    fun myPow(x: Double, n: Int): Double {
        if(map.containsKey(n)){
            return map.get(n)!!
        }
        if(0 == n){
            return 1.0
        }
        if(1 == n){
            return x
        }
        if(-1 == n){
            return 1 / x
        }
        val left = n / 2
        val right = n - left
        val result = myPow(x,left) * myPow(x,right)
        map.put(n,result)
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(lgn) 散列表

## 题解二（快速幂 · 递归）

当指数 n 为负数时，可以先计算 -n 的指数幂再取倒数。

快速幂也是分治思想，在每一次递归中，将问题规模为一半的子问题的解取平方：

n 为奇数：(Fn/2 ^ 2) * x

n 为偶数：Fn/2 ^ 2

```
class Solution {
    fun myPow(x: Double, n: Int): Double {
        return if (n > 0) {
            pow(x, n)
        } else {
            1 / pow(x, -n)
        }
    }

    private fun pow(x: Double, n: Int): Double {
        if (n == 0) return 1.0
        if (n == 1) return x

        return pow(x, n / 2).let { it * it } *
            if ((n and 1) == 1) x else 1.0
    }
}
```

**复杂度分析：**

- 时间复杂度：O(lgn)
- 空间复杂度：O(lgn) 递归栈

## 题解三（位运算）

任何一个数字转换为二进制后，都等价于一个以 2 为底的多项式，例如 77 = (1001101)\_2 = 2^0 + 2^2 + 2^3 + 2^6 = 1 + 4 + 8 + 64

则有 x^n = x^1 * x^4 * x^8 * x^64。

此时，发现只要检查二进制位中为 1 的位，将该位对应的幂加入到最终结果中即可

```
class Solution {
    fun myPow(x: Double, n: Int): Double {
        return if (n > 0) {
            pow(x, n)
        } else {
            1 / pow(x, -n)
        }
    }

    private fun pow(x: Double, n: Int): Double {
        if (n == 0) return 1.0
        if (n == 1) return x

        var k = 1
        var powK = x
        var result = 1.0
        var num = n
        while(num != 0) {
            if((num and (1 shl k - 1)) != 0){
                result *= powK
                num = num and (num - 1) 
            }
            powK *= powK
            k++
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(1)
- 空间复杂度：O(1)
