## [346](https://leetcode.cn/problems/moving-average-from-data-stream/)
## [剑指 Offer II 041. 滑动窗口的平均值](https://leetcode.cn/problems/qIsx9U/?favorite=e8X3pBZi)

## 题目描述

给你一个链表的头节点 head ，判断链表中是否有环。

## 题目考点

给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。

## 题解
 
```
class MovingAverage(val size: Int) {

    /** Initialize your data structure here. */
    private val queue = LinkedList<Int>()
    private var sum = 0.0

    fun next(`val`: Int): Double {
        if (queue.size >= size) {
            sum -= queue.removeFirst()
        }
        queue.offer(`val`)
        sum += `val`
        return sum / queue.size
    }

}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = MovingAverage(size)
 * var param_1 = obj.next(`val`)
 */
```

**复杂度分析：**

- 时间复杂度：O(1)
- 空间复杂度：O(1) 
