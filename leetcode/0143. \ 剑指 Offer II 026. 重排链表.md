## [143. 重排链表](https://leetcode.cn/problems/reorder-list/)
## [剑指 Offer II 026. 重排链表](https://leetcode.cn/problems/LGjMqU/)

## 题目描述

给定一个单链表 L 的头节点 head ，单链表 L 表示为：

 L0 → L1 → … → Ln-1 → Ln 
请将其重新排列后变为：

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

## 题目考点

链表

## 题解

将链表拆分为两部分后反转，再按要求合并链表

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
    fun reorderList(head: ListNode?): Unit {
        if (null == head?.next) return
        var slow = head
        var fast = head.next
        while (null != fast?.next) {
            slow = slow?.next
            fast = fast?.next?.next
        }
        // 反转第二部分
        var firstHead: ListNode? = head
        var secondHead: ListNode? = reverse(slow!!.next)
        slow.next = null
        // 重新构建链表
        val dummyNode = ListNode(-1)
        var tail = dummyNode
        while (null != firstHead && null != secondHead) {
            val temp1 = firstHead.next
            val temp2 = secondHead.next

            firstHead.next = secondHead
            tail.next = firstHead
            tail = secondHead

            firstHead = temp1
            secondHead = temp2
        }
        tail.next = if(null != firstHead) firstHead else secondHead
        return
    }

    private fun reverse(head: ListNode): ListNode {
        if (null == head.next) return head
        val nextNode = head.next
        val newHead = reverse(nextNode)
        nextNode.next = head
        head.next = null
        return newHead
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
