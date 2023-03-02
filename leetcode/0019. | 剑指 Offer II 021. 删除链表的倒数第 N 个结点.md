## [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/)
## [剑指 Offer II 021. 删除链表的倒数第 n 个结点](https://leetcode.cn/problems/SLwz0R/description/)

## 题目描述

给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

## 题目考点

快慢指针

## 题解一（计算链表长度）
 
```
class Solution {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (null == head) return head
        // 链表长度
        var length = 0
        var node = head
        while (null != node) {
            length++
            node = node.next
        }
        // 正好删除头节点
        if (length == n) return head.next
        var point = head
        for (count in 0 until length - n - 1) {
            point = point?.next
        }
        point?.next = point?.next?.next
        return head
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 

## 题解二（快慢指针）
 
```
class Solution {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (null == head) return head
        // 哨兵节点
        val dummy = ListNode(-1).apply {
            this.next = head
        }
        // 快慢指针
        var fast = dummy
        var slow = dummy
        // 快指针先走 n 步
        for (count in 0 until n) {
            fast = fast?.next
        }
        // 当快指针到达末位时，慢指针正好位于第 n 个节点的前驱节点
        while (null != fast?.next) {
            fast = fast.next
            slow = slow?.next
        }
        // 删除慢指针的后继节点
        slow?.next = slow?.next?.next
        return dummy.next
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
