## [剑指 Offer 06. 从尾到头打印链表](https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof)

## 题目描述

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

## 题目考点

链表

## 题解
 
```
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun reversePrint(head: ListNode?): IntArray {
        var p = head
        val list = LinkedList<Int>()
        while (null != p) {
            list.add(0, p.`val`)
            p = p.next
        }
        return IntArray(list.size) { list[it] }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
