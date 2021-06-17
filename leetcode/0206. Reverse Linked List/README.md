## [206. Reverse Linked List](https://leetcode-cn.com/problems/reverse-linked-list/)

## [LeetCode 题解](https://leetcode-cn.com/problems/reverse-linked-list/solution/206-reverse-linked-list-fan-zhuan-lian-biao-by-pen/)

## 题目描述

Given the head of a singly linked list, reverse the list, and return the reversed list.

## 题目大意

反转链表

## 题解一（递归）

```
class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        if(null == head || null == head.next){
            return head
        }
        val prefix = reverseList(head.next)
        head.next.next = head
        head.next = null
        return prefix
    }
}
```
**复杂度分析：**

- 时间复杂度：每个节点扫描一次，时间复杂度为 O(n)
- 空间复杂度：使用了递归栈，空间复杂度为 O(n)

## 题解二（迭代）

```
class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        var cur: ListNode? = head
        var headP: ListNode? = null

        while (null != cur) {
            val tmp = cur.next
            cur.next = headP
            headP = cur
            cur = tmp
        }

        return headP
    }
}
```
**复杂度分析：**

- 时间复杂度：每个节点扫描一次，时间复杂度为 O(n)
- 空间复杂度：使用了常量级别变量，空间复杂度为 O(1)
