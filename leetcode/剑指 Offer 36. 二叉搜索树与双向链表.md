## [剑指 Offer 36. 二叉搜索树与双向链表](https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/description/)

## 题目描述

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

## 题目考点

二叉搜索树 链表

## 题解

```
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {

    private Node leftNode;
    private Node head;

    public Node treeToDoublyList(Node root) {
        if (null == root) return null;

        inorder(root);
        head.left = leftNode;
        leftNode.right = head;

        return head;
    }

    private void inorder(Node root) {
        if (null == root) return;
        inorder(root.left);
        if (null != leftNode) {
            leftNode.right = root;
        } else {
            // 新的头节点
            head = root;
        }
        root.left = leftNode;
        // 新的前驱节点(二叉搜索树的中序遍历是递增序列)
        leftNode = root;
        inorder(root.right);
    }
}
```

```
class Solution {

    class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public Node treeToDoublyList(Node root) {
        if(null == root) return root;
        Pair<Node, Node> result = treeToDoublyListPair(root);
        result.first.left = result.second;
        result.second.right = result.first;

        return result.first;
    }

    // return：子树的首尾节点
    public Pair<Node, Node> treeToDoublyListPair(Node root) {
        if (null == root) return null;

        Pair<Node, Node> left = treeToDoublyListPair(root.left);
        Pair<Node, Node> right = treeToDoublyListPair(root.right);

        Node curLeft = root;
        Node curRight = root;

        if (null != left) {
            left.second.right = root;
            root.left = left.second;

            curLeft = left.first;
        }

        if (null != right) {
            right.first.left = root;
            root.right = right.first;

            curRight = right.second;
        }

        return new Pair<>(curLeft, curRight);
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
