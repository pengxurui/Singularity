## [剑指 Offer 09. 用两个栈实现队列](https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

## 题目描述

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

## 题目考点

栈、队列

## 题解
 
```
class CQueue() {

    private val firstStack = ArrayDeque<Int>()
    private val secondStack = ArrayDeque<Int>()

    fun appendTail(value: Int) {
        firstStack.push(value)
    }

    fun deleteHead(): Int {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop())
            }
        }
        return if (!secondStack.isEmpty()) secondStack.pop() else -1
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
```

**复杂度分析：**

- 时间复杂度：入栈和出栈均摊时间复杂度为 O(1)
- 空间复杂度：O(n)
