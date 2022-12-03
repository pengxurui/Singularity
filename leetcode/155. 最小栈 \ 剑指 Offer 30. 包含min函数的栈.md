##[155. 最小栈](https://leetcode.cn/problems/min-stack)
## [剑指 Offer 30. 包含min函数的栈](https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof)

## 题目描述

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

## 题目考点

栈

## 题解
 
```
class MinStack() {

    /** initialize your data structure here. */

    // 单调栈
    private val minStack = ArrayDeque<Int>()

    // 数据栈
    private val dataStack = ArrayDeque<Int>()

    fun push(x: Int) {
        dataStack.push(x)
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x)
        }
    }

    fun pop() {
        val top = dataStack.pop()
        if (top == minStack.peek()) {
            minStack.pop()
        }
    }

    fun top(): Int {
        return dataStack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(`val`)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
```
