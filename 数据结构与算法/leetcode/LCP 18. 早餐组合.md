## [LCP 18. 早餐组合](https://leetcode.cn/problems/2vYnGI/description/)

## 题目描述

小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。

注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1

## 题目考点

排序、双指针、二分

## 题解一（排序 + 二分）
 
```
class Solution {
    fun breakfastNumber(staple: IntArray, drinks: IntArray, x: Int): Int {
        staple.sort()
        drinks.sort()
        var result = 0
        for (element in staple) {
            val index = search(drinks, element, x)
            result += index
            result %= 1000000007
        }
        return result
    }

    // 寻找大于 drinks[index] + pivot > x 的第一个索引位置
    private fun search(drinks: IntArray, pivot: Int, x: Int): Int {
        var left = 0
        var right = drinks.size - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            if (drinks[mid] + pivot <= x) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        // 后处理
        return if (drinks[left] + pivot > x) left else left + 1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn + mlgn + mlgn)
- 空间复杂度：O(lgn + lgm) 

## 题解二（排序 + 双指针）

我们发现多次执行二分查找显得没有必要。

我们可以使用双指针：

- i：在 staple 数组上从左向右移动，每次移动会让窗口的价格变高
- j：在 drink 数组上从右向左移动，每次移动会让窗口的价格变低

在每次操作中，我们先固定指针 i：

- 当 staple[i] + drink[j] <= x 时，i 与所有 [..., j] 都可以组成目标方案，随后右移 i 指针让价格增加
- 当 staple[i] + drink[j] > 时，此时左移 j 指针让价格遍地

```
class Solution {
    fun breakfastNumber(staple: IntArray, drinks: IntArray, x: Int): Int {
        staple.sort()
        drinks.sort()
        var result = 0
        // 双指针
        var i = 0
        var j = drinks.size - 1
        while (i < staple.size && j >= 0) {
            if (staple[i] + drinks[j] <= x) {
                result += (j + 1)
                result %= 1000000007
                i++
            } else {
                j--
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn + mlgn + m + m)
- 空间复杂度：O(lgn + lgm) 
