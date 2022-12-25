## [剑指 Offer 32 - III. 从上到下打印二叉树 III](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/description/?favorite=xb9nqhhg)

## 题目描述

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

## 题目考点

二叉树

## 题解
 
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
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (null == root) return Collections.emptyList()

        var directionFlag = true
        val result = LinkedList<List<Int>>()
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val level = LinkedList<Int>().also { result.add(it) }
            for (count in 0 until queue.size) {
                val node = queue.poll()

                if (directionFlag) {
                    level.addLast(node.`val`)
                } else {
                    level.addFirst(node.`val`)
                }

                if (null != node.left) {
                    queue.offer(node.left)
                }
                if (null != node.right) {
                    queue.offer(node.right)
                }
            }
            directionFlag = !directionFlag
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
