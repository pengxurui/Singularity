## [144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

## [LeetCode 题解](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/144-binary-tree-preorder-traversal-er-cha-shu-de-q/)

## 题目描述

Given the root of a binary tree, return the preorder traversal of its nodes' values.

## 题目大意

二叉树前序遍历

## 解法一（递归）
```
class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()

        fun preorder(root: TreeNode?) {
            if (null == root) {
                return;
            }
            result.add(root.`val`)
            if (null != root.left) {
                preorder(root.left)
            }
            if (null != root.right) {
                preorder(root.right)
            }
        }
        preorder(root)
        return result
    }
}
```
## 解法二（DFS）
```
class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()
        
        val stack : Deque<TreeNode?> = LinkedList<TreeNode?>()
        stack.push(root)

        while(!stack.isEmpty()){
            val node = stack.pop()
            if(null != node){
                result.add(node.`val`)
                stack.push(node.right)
                stack.push(node.left)
            }
        }

        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：平均 O(lgn)，最差 O(n)，最佳 O(lgn)
