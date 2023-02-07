## [137. 只出现一次的数字 II](https://leetcode.cn/problems/single-number-ii/)
## [剑指 Offer II 004. 只出现一次的数字 ](https://leetcode.cn/problems/WGki4K/description/?favorite=e8X3pBZi)

## 题目描述

给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。

## 题目考点

位与运算

## 题解一（取余）

由于除了答案外的数都出现了 3 次，那么在二进制位中，除了答案外每个位中 1 的个数正好就是 3 的倍数。

反过来，我们将每一位中 1 出现的次数对 3 取余，就是答案在该位中的值
 
```
class Solution {
    fun singleNumber(nums: IntArray): Int {
        // 方法 1 - 散列表：略
        // 方法 2 - 取余
        var result = 0
        for (index in 0 .. 31) {
            var count = 0
            for (element in nums) {
                count += element shr index and 1
            }
            if (count % 3 != 0) {
                result = result or (1 shl index)
            }
        }
        return result
    }
}
```

```
class Solution {
    fun singleNumber(nums: IntArray): Int {
        // 按位取余
        val cnts = IntArray(32)
        for (element in nums) {
            for (index in 0..31) {
                cnts[index] += (element shr index) and 1
            }
        }
        for (index in cnts.indices) {
            cnts[index] %= 3
        }
        var result = 0
        for (index in 0..31) {
            if (0 == cnts[index]) continue
            result = result or (1 shl index)
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlog⁡C)，其中 n 是数组的长度，C 是元素的数据范围，也就是 32 位二进制

- 空间复杂度：O(1)

## 题解二（状态机）

题解一是依次处理每个位，题解二是考虑使用位运算同时处理所有位。

当一个二进制位中 1 的个数超过 3 时，就会循环会 0，相当于是以 3 为模。那么，我们可以设计一个 00 -> 01 -> 10 -> 00(循环) 的状态机，但是一个二进制位只能存储两个状态，所以我们定义两个等长的整数变量，
两个变量相同位置上的状态组成一个复合状态，即：

- a 的第 i 位为 0 且 b 的第 i 位为 0，表示状态 0；
- a 的第 i 位为 0 且 b 的第 i 位为 1，表示状态 1；
- a 的第 i 位为 1 且 b 的第 i 位为 0，表示状态 2。

我们要思考的是：我们将两个变量的初始状态置为 0，然后依次处理每个数字 n， **如何将数字 n 的特征叠加到状态机中。**

最后，我们要找到答案只出现 1 次，所以复合状态为 01 的位就是答案二进制中出现 1 的位， **这正好就是变量 b 的值。**

计算复合状态的方法：

![](https://user-images.githubusercontent.com/25008934/206743932-2697f214-0791-4933-ab90-0ad5204706c0.png)

我们发现 a 的计算较负责，如果先计算 b，则有：

![](https://user-images.githubusercontent.com/25008934/206745616-ebd4abb7-f29e-4faa-a75e-d22d98805b83.png)

```
if ai == 0:
  if xi == 0:
    bi = bi
  if xi == 1:
    bi = ~bi
if ai == 1:
    bi = 0
引入异或运算：
if ai == 0:
    bi = bi ^ xi
if ai == 1:
    bi = 0
引入与运算：
bi = bi ^ xi & ~ai
```
将新的 b 套入到真值表中：

![](https://user-images.githubusercontent.com/25008934/206745462-42358295-19ad-433e-b926-23ce119cd671.png)

此时，a 的真值表与 b 的真值表类似，有 ai = ai ^ xi & ~bi

[参考](https://leetcode.cn/problems/single-number-ii/solutions/8944/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891)
[参考](https://leetcode.cn/problems/single-number-ii/solutions/746993/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/)

```
class Solution {
    fun singleNumber(nums: IntArray): Int {
        var a = 0
        var b = 0
        for (element in nums) {
            b = b xor element and a.inv()
            a = a xor element and b.inv()
        }
        return b
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)，其中 n 是数组的长度。
- 空间复杂度：O(1)。
