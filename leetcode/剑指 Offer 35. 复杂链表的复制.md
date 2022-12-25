## [剑指 Offer 35. 复杂链表的复制](https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/description/)

## 题目描述

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

## 题目考点

快慢指针

## 题解一（散列表）
 
```
class Solution {

    // 方法 1：我们使用散列表记录随机指针的节点的拷贝情况
    // 方法 2：为了消除散列表，我们使用节点的 next 指针记录复制后节点。先复制链表，构建 ”原节点 1 -> 新节点 1 -> 原节点 2 -> 新节点 2 ->“ 的拼接链表，然后构建随机指针指向，最后拆分出偶数位的节点

    public Node copyRandomList(Node head) {
        if (null == head) return null;

        Node node = head;
        while (null != node) {
            // 下一个原节点
            Node next = node.next;
            // 创建拷贝节点
            Node newNode = new Node(node.val);
            newNode.next = next;
            // 将拷贝节点拼接在原节点后
            node.next = newNode;
            node = next;
        }

        // 构建随机节点指针
        node = head;
        while (null != node) {
            node.next.random = (null != node.random) ? node.random.next : null;
            // 下一个原节点（两次 next）
            node = node.next.next;
        }

        // 拆分与还原
        Node result = head.next;

        node = head;
        while (null != node) {
            Node copiedNode = node.next;
            // 还原原链表
            node.next = node.next.next;
            // 拆分新链表
            copiedNode.next = (null != copiedNode.next) ? copiedNode.next.next : null;
            node = node.next;
        }

        return result;
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n)

## 题解二（消除散列表）

```
class Solution {

    // 方法 1：我们使用散列表记录随机指针的节点的拷贝情况
    // 方法 2：为了消除散列表，我们使用节点的 next 指针记录复制后节点。先复制链表，构建 ”原节点 1 -> 新节点 1 -> 原节点 2 -> 新节点 2 ->“ 的拼接链表，然后构建随机指针指向，最后拆分出偶数位的节点

    public Node copyRandomList(Node head) {
        if (null == head) return null;

        Node node = head;
        while (null != node) {
            // 下一个原节点
            Node next = node.next;
            // 创建拷贝节点
            Node newNode = new Node(node.val);
            newNode.next = next;
            // 将拷贝节点拼接在原节点后
            node.next = newNode;
            node = next;
        }

        // 构建随机节点指针
        node = head;
        while (null != node) {
            node.next.random = (null != node.random) ? node.random.next : null;
            // 下一个原节点（两次 next）
            node = node.next.next;
        }

        // 拆分与还原
        Node result = head.next;

        node = head;
        while (null != node) {
            Node copiedNode = node.next;
            // 还原原链表
            node.next = node.next.next;
            // 拆分新链表
            copiedNode.next = (null != copiedNode.next) ? copiedNode.next.next : null;
            node = node.next;
        }

        return result;
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
