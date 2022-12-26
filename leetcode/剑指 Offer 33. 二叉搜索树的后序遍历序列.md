## [剑指 Offer 33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/description/)

## 题目描述

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

## 题目考点

二叉搜索树

## 题解一（递归）
 
```
class Solution {
    fun verifyPostorder(postorder: IntArray): Boolean {
        // 后序遍历：[左子树，右子树，根]，其中右子树从第一个大于根的节点开始：[1,3,2,6,5]
        // [1,3,4]:左子树
        // [6]:右子树
        // [5]:根
        return verifyPostorder(postorder, 0, postorder.size - 1)
    }

    private fun verifyPostorder(postorder: IntArray, left: Int, right: Int): Boolean {
        if (left >= right) return true
        // 寻找第一个大于根的位置
        var point = left
        while (postorder[point] < postorder[right]) point++
        val rightTreeIndex = point
        // 验证右子树中所有节点都大于根（左子树已经验证过）
        while (postorder[point] > postorder[right]) point++
        // 验证左右子树
        return point == right && verifyPostorder(postorder, left, rightTreeIndex - 1) && verifyPostorder(postorder, rightTreeIndex, right - 1)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n^2)
- 空间复杂度：O(1) 

## 题解二（单调栈）

```
class Solution {
    fun verifyPostorder(postorder: IntArray): Boolean {
        // 后序遍历：[左子树，右子树，根]，其中右子树从第一个大于根的节点开始：[1,3,2,6,5]
        // [1,3,4]:左子树
        // [6]:右子树
        // [5]:根
        // 方法 1：递归
        // 方法 2：我们将 postorder 反转，就得到 根->右子树->左子树 的逆先序遍历，并且存在单调性
        // 如果遇到一个”逆序“，则说明遇到一次从右子树->左子树的变换，此时我们就区分出左右子树，并需要验证左右两个子树都满足二叉搜索树的性质，而根节点就是[根，右子树]中第一个大于”逆序节点“。
        // 综上，这个问题与”下一个更大问题“类似

        // 从栈底到栈顶单调递增
        val stack = ArrayDeque<Int>()
        // 初始的根节点视为无穷大
        var root = Integer.MAX_VALUE
        // 逆序遍历
        for (element in postorder.reversed()) {
            if (element >= root) {
                // 检查左子树
                return false
            }

            // 逆序，说明从右子树进入左子树
            // 找到根节点以验证左子树满足二叉搜索树（右子树已经验证了)
            while (!stack.isEmpty() && stack.peek() > element) {
                // 找到一个更小的根，满足”比当前的根小“一定满足”比上一个根小“
                root = stack.pop()
            }
            stack.push(element)
        }
        return true
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(n) 
