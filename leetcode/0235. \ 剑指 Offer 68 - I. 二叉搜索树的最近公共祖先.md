## [235. 二叉搜索树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/)
## [剑指 Offer 68 - I. 二叉搜索树的最近公共祖先](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/)

## 题目描述

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

## 题目考点

二叉搜索树

## 题解一（二叉树通用解法）
 
```
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // 共同祖先可能是节点本身
        // 方法 1：搜索路径后对比
        // 方法 2：一次遍历（递归）
        // 方法 3：一次遍历（迭代）
        if (null == root || root == p || root == q) return root
        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)
        // 如果左子树不存在解，那么解一定在右子树
        if (null == left) return right
        // 如果右子树不存在解，那么解一定在左子树
        if (null == right) return left
        // 左右子树都存在解，那么当前节点就是解
        return root
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 递归栈

## 题解二（利用二叉搜索树性质剪枝）

```
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // 共同祖先可能是节点本身
        // 方法 1：搜索路径后对比
        // 方法 2：一次遍历（递归）
        // 方法 3：一次遍历（迭代）
        if (null == root || null == p || null == q) return null
        // 利用二叉搜索树的性质判断解可能的子树（利用差值简化代码）
        return if (1L * (root.`val` - p.`val`) * (root.`val` - q.`val`) > 0) {
            // 位于同一侧
            if (p.`val` < root.`val`)
                lowestCommonAncestor(root.left, p, q)
            else
                lowestCommonAncestor(root.right, p, q)
        } else root
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 递归栈

## 题解三（迭代）

```
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // 共同祖先可能是节点本身
        // 方法 1：搜索路径后对比
        // 方法 2：一次遍历（递归）
        // 方法 3：一次遍历（迭代）
        if (null == root || null == p || null == q) return null
        // 利用二叉搜索树的性质判断解可能的子树（利用差值简化代码）
        var result = root
        while (null != result) {
            if (p.`val` < result.`val` && q.`val` < result.`val`)
                result = result.left
            else if (p.`val` > result.`val` && q.`val` > result.`val`)
                result = result.right
            else
                return result
        }
        return null
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
