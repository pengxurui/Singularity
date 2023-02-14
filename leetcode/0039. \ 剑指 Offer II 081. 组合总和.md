## [39. 组合总和](https://leetcode.cn/problems/combination-sum/description/)
## [剑指 Offer II 081. 允许重复选择元素的组合](https://leetcode.cn/problems/Ygoe9J/description/?page=2)

## 题目描述

给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 

对于给定的输入，保证和为 target 的不同组合数少于 150 个。

## 题目考点

排列组合

## 题解
 
```
class Solution {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        // 排序
        candidates.sort()
        return LinkedList<List<Int>>().apply {
            combinationSum(candidates, 0, LinkedList<Int>(), target, this)
        }
    }

    private fun combinationSum(candidates: IntArray, start: Int, path: MutableList<Int>, target: Int, result: MutableList<List<Int>>) {
        if (target < 0) return
        if (target == 0) {
            result.add(ArrayList(path))
            return
        }
        for (index in start until candidates.size) {
            path.add(candidates[index])
            combinationSum(candidates, index, path, target - candidates[index], result)
            path.removeAt(path.size - 1)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·2^n) 一共有 2^n 个子问题，每个问题 O(n)
- 空间复杂度：O(target) 最差情况需要递归 target 层 
