## [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/description/)
## [剑指 Offer 28. 对称的二叉树](https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/description/)

## 题目描述

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

## 题目考点

递归

## 题解一（递归）
 
```
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (null == root) return true
        return isSymmetric(root.left, root.right)
    }

    private fun isSymmetric(root1: TreeNode?, root2: TreeNode?): Boolean {
        if (null == root1) return null == root2
        if (null == root2) return null == root1
        if (root1.`val` != root2.`val`) return false
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 

## 题解二（迭代）
 
```
import java.util.*

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (null == root) {
            return true
        }
        // 迭代
        // 每次取两个节点比较，然后将两个节点的左右子节点分别以相反的顺序添加到队列中
        val queue = LinkedList<TreeNode?>()
        queue.offer(root)
        queue.offer(root)
        while (!queue.isEmpty()) {
            val left = queue.poll()
            val right = queue.poll()
            if (null == left && null == right) continue
            if (null == left || null == right) return false
            if (left?.`val` != right?.`val`) return false

            queue.add(left.left)
            queue.add(right.right)

            queue.add(left.right)
            queue.add(right.left)
        }
        return true
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
