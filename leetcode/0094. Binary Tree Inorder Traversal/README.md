## [94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

## [LeetCode 题解](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/94-binary-tree-inorder-traversal-er-cha-shu-de-zho/)

## 题目描述

Given the root of a binary tree, return the inorder traversal of its nodes' values.

## 题目大意

二叉树中序遍历

## 解法一（递归）
```
class Solution {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()
        
        fun inorder(root:TreeNode?){
            if(null == root){
                return;
            }
            inorder(root.left)
            result.add(root.`val`)
            inorder(root.right)
        }
        inorder(root)
        return result
    }
}
```
## 解法二（DFS）
```
class Solution {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()
        val stack = LinkedList<TreeNode>()

        var cur = root
        while (!stack.isEmpty() || null != cur) {
            if (null != cur) {
                stack.push(cur)
                cur = cur.left
            } else {
                // 栈顶为最左节点
                val node = stack.pop()
                result.add(node.`val`)
                cur = node.right
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：平均 O(lgn)，最差 O(n)，最佳 O(lgn)
