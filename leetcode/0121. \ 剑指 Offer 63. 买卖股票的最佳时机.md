## [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)
## [剑指 Offer 63. 股票的最大利润](https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/?favorite=xb9nqhhg)

## 题目描述

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

## 题目考点

- 思考 1：单调栈 X：问题寻找的是下一个最大元素，而不是下一个更大元素
- 思考 2：小顶堆 OK：逆序处理数据，维护最小值
- 思考 3：动态规划 X：问题定义 f(n) 为在第 n 天买入后的最大收益，发现 f(n) 与 f(n-1) 不存在最优子结构

## 题解一（单调栈 · 错误）

- 输入: [7,1,5,3,6,4]
- 输出: 5
- 错误原因：丢失了 6 - 1 的方案

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 关键逻辑：增加一个更小的值，则后续值与其做减法的利润更大
        var result = 0
        val stack = ArrayDeque<Int>()
        for (price in prices) {
            // 增加一个较小值，则栈顶的较大值后续不会再考虑
            while (!stack.isEmpty() && stack.peek() >= price) {
                stack.pop()
            }
            if (!stack.isEmpty()) {
                result = Math.max(result, price - stack.peek())
            }
            stack.push(price)
        }
        return result
    }
}
```

## 题解二（小顶堆 / 大顶堆）

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 小顶堆
        val heap = PriorityQueue<Int> { s1, s2 ->
            s1 - s2
        }
        var result = 0
        for (price in prices) {
            if (heap.isEmpty()) heap.add(price)
            result = Math.max(result, price - heap.peek())
            heap.add(price)
        }
        return result
    }
}
```

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 大顶堆
        val heap = PriorityQueue<Int> { s1, s2 ->
            s2 - s1
        }
        var result = 0
        for (index in prices.size - 1 downTo 0) {
            if (heap.isEmpty()) heap.add(prices[index])
            result = Math.max(result, heap.peek() - prices[index])
            heap.add(prices[index])
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 

## 题解三（单个变量维护小顶 / 大顶）

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 最小买入价
        var minPrice = Integer.MAX_VALUE
        var result = 0
        for (price in prices) {
            result = Math.max(result, price - minPrice)
            minPrice = Math.min(minPrice, price)
        }
        return result
    }
}
```

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        var result = 0
        // 最大卖出价
        var maxValue = 0

        for (index in prices.size - 1 downTo 0) {
            result = Math.max(result, maxValue - prices[index])
            maxValue = Math.max(maxValue, prices[index])
        }

        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 

