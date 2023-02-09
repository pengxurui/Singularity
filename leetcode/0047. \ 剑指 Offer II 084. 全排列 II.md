## [47. 全排列 II](https://leetcode.cn/problems/permutations-ii/description/)
## [剑指 Offer II 084. 含有重复元素集合的全排列](https://leetcode.cn/problems/7p8L0Z/description/)

## 题目描述

给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

## 题目考点

排列组合

## 题解
 
```
class Solution {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        nums.sort()
        return LinkedList<List<Int>>().apply {
            permuteUnique(nums, BooleanArray(nums.size) {false}, LinkedList<Int>(), this)
        }
    }

    private fun permuteUnique(nums: IntArray, used: BooleanArray, path: LinkedList<Int>, result: MutableList<List<Int>>) {
        if (path.size == nums.size) {
            result.add(ArrayList(path))
            return
        }
        for (index in nums.indices) {
            if (used[index]) continue
            if (index > 0 && nums[index] == nums[index - 1] && !used[index - 1]) continue
            // 选择
            used[index] = true
            path.add(nums[index])
            // 递归
            permuteUnique(nums, used, path, result)
            // 回溯
            path.removeLast()
            used[index] = false
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n!)
- 空间复杂度：O(n) 
