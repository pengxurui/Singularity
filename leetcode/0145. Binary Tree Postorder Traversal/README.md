## [145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

## [LeetCode 题解](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/145-binary-tree-postorder-traversal-er-cha-shu-de-/)

## 题目描述

Given the root of a binary tree, return the postorder traversal of its nodes' values.

## 题目大意

二叉树后序遍历

## 解法一（递归）
```
class Solution {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()

        fun inorder(root: TreeNode?) {
            if (null == root) {
                return;
            }
            inorder(root.left)
            inorder(root.right)
            result.add(root.`val`)
        }
        inorder(root)
        return result
    }
}
```
## 解法二（DFS）
```
class Solution {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = LinkedList<Int>()

        val stack: Deque<TreeNode?> = LinkedList()

        stack.push(root)

        while (!stack.isEmpty()) {
            val node = stack.pop()
            if (null != node) {
                result.addFirst(node.`val`)
                stack.push(node.left)
                stack.push(node.right)
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：$O(n)$
- 空间复杂度：平均$O(lgn)$，最差$O(n)$，最佳$O(lgn)$