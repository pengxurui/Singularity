## [面试题 02.02. 返回倒数第 k 个节点](https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/)
## [剑指 Offer 22. 链表中倒数第k个节点](https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

## 题目描述

实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。

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
    fun kthToLast(head: ListNode?, k: Int): Int {
        var fast: ListNode? = head
        var slow: ListNode? = head

        for (count in 0 until k) {
            fast = fast!!.next
        }

        while (null != fast) {
            fast = fast!!.next
            slow = slow!!.next
        }
        return slow!!.`val`
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
