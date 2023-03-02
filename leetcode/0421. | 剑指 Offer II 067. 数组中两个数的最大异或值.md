## [421. 数组中两个数的最大异或值](https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/description/)
## [剑指 Offer II 067. 最大的异或](https://leetcode.cn/problems/ms70jA/description/?favorite=e8X3pBZi)

## 题目描述

给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

## 题目考点

位运算、前缀和、线段树、字典树

## 题解一（前缀和 + 散列表）

输入数据的最高有效位是 31 位，我们可以从第 31 位开始向第 1 位逼近，为了获得最大的异或值，我们期待每一位都能取 1，即最大值为 1111111

在从高位向低位逼近的过程中，假设已经确定的高位部分是 preK，对于第 index 位，如果存在 [pre,1:] 的数字，那么说明第 index 位可以取 1

为了快速判断，我们将所有数字的前 k 位写入散列表，并使用 O(1) 判断 [pre,1:] 是否存在

```
class Solution {
    fun findMaximumXOR(nums: IntArray): Int {
        var result = 0
        for (index in 30 downTo 0) {
            val maskSet = HashSet<Int>()
            // 二进制前缀 [30,index]
            for (element in nums) {
                maskSet.add(element shr index)
            }
            // 检查目标二进制前缀是否存在
            val candidate = (result shl 1) + 1
            var isFound = false
            // x xor y = candidate => x xor candidate = y
            for (mask in maskSet) {
                if (maskSet.contains(mask xor candidate)) {
                    isFound = true
                    break
                }
            }
            result = if (isFound) candidate else candidate - 1
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n · 31)
- 空间复杂度：O(n) 

## 题解二（字典树）

1、我们将数字看成 01 字符串，如果我们将数字转为字典树，那么在字典树上查询某个字符串的过程，正好对应于从高位向低位的遍历过程。

2、完成建树后，我们遍历每一个数，从字典树上查询另一个使得两个数字异或值最大的结果

```
class Solution {

    fun findMaximumXOR(nums: IntArray): Int {
        // 建立字典树
        val root = TireNode()
        for (element in nums) {
            var curNode = root
            // 从高位向低位遍历
            for (index in 30 downTo 0) {
                val bit = (element shr index) and 1
                // 建立节点
                if (null == curNode.children[bit]) {
                    curNode.children[bit] = TireNode()
                }
                // 指向下一层
                curNode = curNode.children[bit]!!
            }
        }
        // 查询
        var result = 0
        for (element in nums) {
            var curXorSum = 0
            var curNode = root
            // 从高位向低位遍历
            for (index in 30 downTo 0) {
                val bit = (element shr index) and 1
                val targetBit = bit xor 1
                if (null != curNode.children[targetBit]) {
                    // 存在可以使得异或值为 1 的节点
                    curNode = curNode.children[targetBit]!!
                    curXorSum += (1 shl index)
                } else {
                    // 存在可以使得异或值为 1 的节点
                    curNode = curNode.children[bit]!!
                }
            }
            result = Math.max(result, curXorSum)
        }
        return result
    }

    private class TireNode() {
        // [0] 表示 0
        // [1] 表示 1
        val children = arrayOfNulls<TireNode>(2)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n · 31 + n · 31)
- 空间复杂度：O(n · 31) 
