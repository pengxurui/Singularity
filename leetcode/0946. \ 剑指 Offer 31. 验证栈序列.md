## [946. 验证栈序列](https://leetcode.cn/problems/validate-stack-sequences/)
## [剑指 Offer 31. 栈的压入、弹出序列](https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/?favorite=xb9nqhhg)

## 题目描述

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

## 题目考点

栈

## 题解
 
```
class Solution {
    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
        val stack = ArrayDeque<Int>()
        var pushPoint = 0
        for (poppedElement in popped) {
            while (stack.peek() != poppedElement && pushPoint < pushed.size) {
                stack.push(pushed[pushPoint++])
            }
            if (stack.peek() == poppedElement) {
                stack.pop()
            } else {
                return false
            }
        }
        return true
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
