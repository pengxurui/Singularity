## [167. 两数之和 II - 输入有序数组](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/)
## [剑指 Offer II 006. 排序数组中两个数字之和](https://leetcode.cn/problems/kLl5u1/)

## 题目描述

给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。

以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。

你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

你所设计的解决方案必须只使用常量级的额外空间。

## 题目考点

双指针

## 题解一（双指针）
 
```
class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1
        while (left < right) {
            val sum = numbers[left] + numbers[right]
            if (sum == target) break
            else if (sum < target) left++
            else right--
        }
        return intArrayOf(left + 1, right + 1)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 

## 题解二（二分查找）

固定第一个数，再用二分查找寻找另一个数

```
class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for (index in 0..numbers.size - 2) {
            val targetRight = target - numbers[index]
            // 二分查找
            var left = index + 1
            var right = numbers.size - 1
            while (left < right) {
                val mid = (left + right) ushr 1
                if (numbers[mid] < targetRight) {
                    left = mid + 1
                } else {
                    right = mid
                }
            }
            if (targetRight == numbers[left]) return intArrayOf(index + 1, left + 1)
        }
        return IntArray(0)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn)
- 空间复杂度：O(1) 

