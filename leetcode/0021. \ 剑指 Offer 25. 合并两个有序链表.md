## [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)
## [剑指 Offer 25. 合并两个排序的链表](https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

## 题目描述

将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

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
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (null == list1) return list2
        if (null == list2) return list1

        // 哨兵节点
        val sentnel = ListNode(-1)
        var rear = sentnel

        var p1 = list1
        var p2 = list2

        while (null != p1 && null != p2) {
            if (p1.`val` < p2.`val`) {
                rear.next = p1
                rear = p1
                p1 = p1.next
            } else {
                rear.next = p2
                rear = p2
                p2 = p2.next
            }
        }
        rear.next = if (null == p1) p2 else p1
        return sentnel.next
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n+m)
- 空间复杂度：O(1) 
