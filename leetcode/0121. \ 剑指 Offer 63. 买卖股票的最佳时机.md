## [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)
## [剑指 Offer 63. 股票的最大利润](https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/?favorite=xb9nqhhg)

## 题目描述

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

## 题目考点

- 思考 1：单调栈
- 思考 2：小顶堆 OK：逆序处理数据，维护最小值
- 思考 3：动态规划 X：问题定义 f(n) 为在第 n 天买入后的最大收益，发现 f(n) 与 f(n-1) 不存在最优子结构

## 题解一（单调栈）

第一次尝试时：以为单调栈的解法是错误的：问题寻找的是下一个最大元素，而不是下一个更大元素

- 输入: [7,1,5,3,6,4]
- 输出: 5
- 错误原因：丢失了 6 - 1 的方案

如果我们在入栈的时候计算差值，确实会丢失方案。但如果我们在出栈的时候计算差值，就不会丢失方案：

每当处理一个元素时，由于还不清楚应该买入还是卖出，所以先将其加入到数据容器。

后来，每增加一个较小的元素，那么这个 [较小] 元素一定是比容器内所有比 [较大] 元素更好的买入时机，这些较大元素可以不再考虑。因此我们发现这个容器符合栈的逻辑，且应该是大顶的逻辑。

而栈底的元素是迄今为止发现的最好的买入时机，每次元素出栈（发现更好的买入时机）时，会与栈底做差值。

参考：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/solutions/139451/c-li-yong-shao-bing-wei-hu-yi-ge-dan-diao-zhan-tu-/

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 大顶
        val stack = LinkedList<Int>()
        var result = 0
        
        for (price in prices) {
            while (!stack.isEmpty() && stack.getLast() >= price) {
                result = Math.max(result, stack.getLast() - stack.getFirst())
                stack.pollLast()
            }
            stack.addLast(price)
        }
        // 最后有一部分元素没有出栈机会
        if (!stack.isEmpty()) result = Math.max(result, stack.getLast() - stack.getFirst())
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 

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

## 题解四（动态规划）

当前是否持股和昨天是否持股有关系

定义问题 dp[i][j]：[i] 表示某一天结束，[j] 表示持有或不持有的状态（j==0 不持股），返回值表示该状态的最大的利润。

那么有：

- dp[i][0] = max{dp[i-1][0], dp[i - 1][1] + prices[i]}
- dp[i][1] = max{dp[i - 1[1], 0 - prices[i]}

- 今天不持股：昨天不持股 or 昨天持股 + 今天卖出
- 今日持股：昨天持股 or 昨天不持股 + 今天买入（由于题目只允许一次买卖，所以买入之前的盈亏一定是 0）

- 初始状态：dp[0][0] = 0、dp[0][1] = -prices[0]
- 终止状态：dp[n - 1][0] 最后不持有的盈利一定更高

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 动态规划
        val dp = Array(prices.size) { IntArray(2) }.apply {
            this[0][0] = 0
            this[0][1] = -prices[0]
        }
        for (index in 1 until prices.size) {
            dp[index][0] = Math.max(dp[index - 1][0], dp[index - 1][1] + prices[index])
            dp[index][1] = Math.max(dp[index - 1][1], -prices[index])
        }
        return dp[prices.size - 1][0]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 

## 题解五（动态规划 · 滚动数组）

我们发现第 [i] 天只和第 [i-1] 天有关，所以我们没有必要存储整个 dp 数组，可以去掉 [i] 这个维度

```
class Solution {
    fun maxProfit(prices: IntArray): Int {
        // 动态规划 + 滚动数组
        val dp = IntArray(2).apply {
            this[0] = 0
            this[1] = -prices[0]
        }
        for (index in 1 until prices.size) {
            dp[0] = Math.max(dp[0], dp[1] + prices[index])
            dp[1] = Math.max(dp[1], -prices[index])
        }
        return dp[0]
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
