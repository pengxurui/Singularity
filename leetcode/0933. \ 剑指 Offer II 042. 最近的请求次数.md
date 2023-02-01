## [933. 最近的请求次数](https://leetcode.cn/problems/number-of-recent-calls/)
## [剑指 Offer II 042. 最近请求次数](https://leetcode.cn/problems/H8086Q/?favorite=e8X3pBZi)

## 题目描述

写一个 RecentCounter 类来计算特定时间范围内最近的请求。

请你实现 RecentCounter 类：

RecentCounter() 初始化计数器，请求数为 0 。
int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
保证 每次对 ping 的调用都使用比之前更大的 t 值。

## 题目考点

快慢指针

## 题解
 
```
class RecentCounter() {

    private val queue = LinkedList<Int>()

    fun ping(t: Int): Int {
        while (!queue.isEmpty() && queue.getFirst() < t - 3000) queue.pollFirst()
        queue.offer(t)
        return queue.size
    }

}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * var obj = RecentCounter()
 * var param_1 = obj.ping(t)
 */
```

**复杂度分析：**

- 时间复杂度：O(1)
- 空间复杂度：O(1) 
