## [203. 移除链表元素](https://leetcode.cn/problems/remove-linked-list-elements/)
## [剑指 Offer 18. 删除链表的节点](https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

## 题目描述

给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。

## 题目考点

链表

## 题解
 
```
class Solution {
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        // 哨兵节点
        val sentinel = ListNode(-1)
        sentinel.next = head

        var pre = sentinel
        var cur: ListNode? = sentinel
        while (null != cur) {
            val removeNode = if (`val` == cur.`val`) {
                // 移除
                pre.next = cur.next
                cur
            } else {
                pre = cur
                null
            }
            cur = cur.next
            if (null != removeNode) {
                removeNode.next = null
            }
        }

        return sentinel.next
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
