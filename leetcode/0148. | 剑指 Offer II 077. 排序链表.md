## [148. 排序链表](https://leetcode.cn/problems/sort-list/description/)
## [剑指 Offer II 077. 链表排序](https://leetcode.cn/problems/7WHec2/description/)

## 题目描述

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

## 题目考点

链表

## 题解

注意：将终止条件设置为 null 或 head.next == tail 的写法更简洁

```
class Solution {
    fun sortList(head: ListNode?): ListNode? {
        // 归并排序
        return sortList(head, null)
    }

    // tail：不包含
    private fun sortList(head: ListNode?, tail: ListNode?): ListNode? {
        // 终止条件：空节点
        if (null == head) {
            return null
        }
        // 终止条件：单节点
        if (head.next == tail) {
            head.next = null
            return head
        }
        var slow: ListNode? = head
        var fast: ListNode? = head
        while (tail != fast && tail != fast?.next) {
            slow = slow?.next
            fast = fast?.next?.next
        }
        // 分治
        var leftHead: ListNode? = sortList(head, slow)
        var rightHead: ListNode? = sortList(slow!!, tail)
        // 合并
        val dummyHead = ListNode(-1)
        var point = dummyHead
        while (null != leftHead && null != rightHead) {
            if (leftHead.`val` <= rightHead.`val`) {
                point.next = leftHead
                leftHead = leftHead.next
            } else {
                point.next = rightHead
                rightHead = rightHead.next
            }
            point = point.next!!
        }
        point.next = if (null != leftHead) leftHead else rightHead
        return dummyHead.next!!
    }
}
```

简化技巧：在拆分后将中心点的 next 指针指向 null，这样就可以简化右端点判断

```
class Solution {
    fun sortList(head: ListNode?): ListNode? {
        if (null == head || null == head.next) return head
        // 寻找左中点
        var slow = head
        var fast = head.next
        while (null != fast?.next) {
            slow = slow?.next
            fast = fast?.next?.next
        }
        val nextHead = slow?.next
        slow?.next = null
        // 分治
        var leftHead = sortList(head)
        var rightHead = sortList(nextHead)
        // 合并
        val dummy = ListNode(-1)
        var point = dummy
        while (null != leftHead && null != rightHead) {
            if (leftHead?.`val` < rightHead?.`val`) {
                point.next = leftHead
                leftHead = leftHead.next
            } else {
                point.next = rightHead
                rightHead = rightHead.next
            }
            point = point.next!!
        }
        point.next = if (null != leftHead) leftHead else rightHead
        return dummy.next
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn)
- 空间复杂度：O(lgn) 

## 题解二（迭代 · TODO）

从长度为 1 开始，逐渐合并长度为 2、4、8、直到完整链表

```
```

**复杂度分析：**

- 时间复杂度：O(n + nlgn)
- 空间复杂度：O(lgn) 
