## [102. Binary Tree Level Order Traversal](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

## [LeetCode 题解](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/102-binary-tree-level-order-traversal-ceng-xu-bian/)

## 题目描述

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

## 题目大意

二叉树的层序遍历

## 解法一（迭代）
```
class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<List<Int>>()

        val queue: Queue<TreeNode> = LinkedList()

        if (null != root) {
            queue.offer(root)
        }

        while (!queue.isEmpty()) {
            val line = ArrayList<Int>()
            // 处理一层
            for (index in 0 until queue.size) {
                val node = queue.poll()
                line.add(node.`val`)
                if(null != node.left){
                    queue.offer(node.left)
                }
                if(null != node.right){
                    queue.offer(node.right)
                }
            }
            if(line.isNotEmpty()){
                result.add(line)
            }
        }
        return result
    }
}
```
**复杂度分析：**
- 时间复杂度：O(n)
- 空间复杂度：平均O(lgn)，最差O(n)，最佳O(lgn)
