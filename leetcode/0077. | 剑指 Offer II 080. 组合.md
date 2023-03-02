## [77. 组合](https://leetcode.cn/problems/combinations/description/)
## [剑指 Offer II 080. 含有 k 个元素的组合](https://leetcode.cn/problems/uUsW3B/description/)

## 题目描述

给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。

## 题目考点

排列组合

## 题解一（组合）
 
```
class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        // k 个数的组合
        return LinkedList<List<Int>>().apply {
            combine(1, n, k, LinkedList<Int>(), this)
        }
    }

    private fun combine(start: Int, end: Int, k: Int, path: MutableList<Int>, result: MutableList<List<Int>>) {
        if (k == 0) {
            result.add(ArrayList(path))
            return
        }
        for (choice in start..end) {
            // 选择
            path.add(choice)
            // 递归
            combine(choice + 1, end, k - 1, path, result)
            // 回溯
            path.removeAt(path.size - 1)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(C(n,k)) 不考虑构造每个组合结果字符串的 O(k) 时间
- 空间复杂度：O(k) 最多 k 层递归栈，不考虑路径数组 O(k)

## 题解二（剪枝）

为了选出 k 个数，如果前一个数选择过大，那么有可能导致后序区间无法选择出 k 个数。

例如 n = 100, k = 10，如果第 1 层选择 99，后面绝对无法选出 9 个数。

```
class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        // k 个数的组合
        return LinkedList<List<Int>>().apply {
            combine(1, n, k, LinkedList<Int>(), this)
        }
    }

    private fun combine(start: Int, n: Int, k: Int, path: MutableList<Int>, result: MutableList<List<Int>>) {
        if (k == 0) {
            result.add(ArrayList(path))
            return
        }
        // 下界：from = start（去重）
        // 上界：to = k + start（剪枝）,n - to >= k - 1 下一层才能满足
        // 例如在 [7,4] 组合问题中，如果第一个数选择 5，那么在下一层递归中只能从 [6,7] 中选择出 3 个数，这显然是无法完成的，所以 5 这个分支可以减去
        // 所以，在 [7,4] 组合问题中，上界应该是 4，下一层才可能在 [5,6,7] 中选择出 3 个数
        for (choice in start..n - k + 1) {
            // 选择
            path.add(choice)
            // 递归
            combine(choice + 1, n, k - 1, path, result)
            // 回溯
            path.removeAt(path.size - 1)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(C(n,k)) 不考虑构造每个组合结果字符串的 O(k) 时间
- 空间复杂度：O(k) 最多 k 层递归栈，不考虑路径数组 O(k)

## 题解三（枚举二级制数）

枚举二进制数，如果二进制位中 1 的个数为 k，则将数字转换为列表。

如果我们将选中的位置标记位 1，未选中的位置标记为 0，则最终的这对应的二进制数正好是递增的字典序

<img width="434" alt="image" src="https://user-images.githubusercontent.com/25008934/210206222-665c16eb-dc5e-4c28-b28c-c10127d0b525.png">

可以看出，二进制数正好是包含 k 个 1 和 n - k 个 0 的二进制数的字典序排列

```
class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        // 枚举二进制数
        val result = LinkedList<List<Int>>()
        val mask = (1 shl n) - 1
        for (num in 0..mask) {
            if (Integer.bitCount(num) == k) {
                val list = LinkedList<Int>()
                for (index in 0 until n) {
                    if (num and (1 shl index) != 0) {
                        list.add(index + 1)
                    }
                }
                result.add(list)
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(2^n·lgn) 一共有 2^n 个状态，每个数字花费 lgn 时间检查二进制位，不考虑构造每个组合结果字符串的 O(n) 时间
- 空间复杂度：O(1)

## 题解四（下一个排列 · TODO）

事实上，我们不需要枚举所有二进制数，而是不断求下一个字典序排列，直接从前一个方案变换得到下一个方案

我们可以用一个长度为 n 的 0/1 数组来表示选择方案对应的二进制数，初始状态下最低的 k 位全部为 1，其余位置全部为 0，然后不断通过上述方案求 next，就可以构造出所有的方案。


```
```

**复杂度分析：**

- 时间复杂度：
- 空间复杂度：
