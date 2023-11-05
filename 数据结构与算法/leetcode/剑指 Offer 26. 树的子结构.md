## [剑指 Offer 26. 树的子结构](https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/description/?favorite=xb9nqhhg)

## 题目描述

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

## 题目考点

对称性递归（symmetric recursion）：

在将原问题分解为子问题时（一般指二叉树结构），不单独考虑一个子问题的解，而是需要同时考虑左右两个子问题的解，这种问题就是对称性递归

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
class Solution {
    fun isSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
        // 递归：需要注意在开始从一个节点匹配 B 树后，如果最终匹配不成功，那么还需要回到这个节点的左右子节点重新匹配
        if (null == A || null == B) return false
        return checkForSameRoot(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B)
    }

    // return：从 A 和 B 为根节点开始，是否匹配
    private fun checkForSameRoot(A: TreeNode?, B: TreeNode?): Boolean {
        if (null == B) return true
        if (null == A) return false
        if (A.`val` != B.`val`) return false
        return checkForSameRoot(A.left, B.left) && checkForSameRoot(A.right, B.right)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(M·N) 每次 checkForSameRoot 花费 O(N）
- 空间复杂度：O(M)  
