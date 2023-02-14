## [40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii/description/)
## [剑指 Offer II 082. 含有重复元素集合的组合](https://leetcode.cn/problems/4sjJUc/)

## 题目描述

给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。 

## 题目考点

排列组合

## 题解一（使用 used 数组）
 
使用 used 数组避免同一层重复使用同一个数

```
class Solution {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        // 排序
        candidates.sort()
        return LinkedList<List<Int>>().apply {
            combinationSum(candidates, BooleanArray(candidates.size), 0, LinkedList<Int>(), target, this)
        }
    }

    private fun combinationSum(candidates: IntArray, used:BooleanArray,start: Int, path: MutableList<Int>, target: Int, result: MutableList<List<Int>>) {
        if (target < 0) return
        if (target == 0) {
            result.add(ArrayList(path))
            return
        }
        if (start >= candidates.size) return
        for (index in start until candidates.size) {
            // 消除重复
            if (index > 0 && candidates[index] == candidates[index - 1] && !used[index - 1]) {
                continue
            }
            used[index] = true
            path.add(candidates[index])
            combinationSum(candidates, used, index + 1 /* 消除重复 */, path, target - candidates[index], result)
            path.removeAt(path.size - 1)
            used[index] = false
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n!)
- 空间复杂度：O(n) 

## 题解二（不使用 used 数组）

使用 index > start 避免同一层重复选择同一个数

一般在排列问题中使用 used 数组，在组合问题中使用 start 变量

```
class Solution {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
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
        if (start >= candidates.size) return
        for (index in start until candidates.size) {
            // 消除重复
            if (index > start && candidates[index] == candidates[index - 1]) {
                continue
            }
            path.add(candidates[index])
            combinationSum(candidates, index + 1 /* 消除重复 */, path, target - candidates[index], result)
            path.removeAt(path.size - 1)
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n·2^n)
- 空间复杂度：O(target) 
