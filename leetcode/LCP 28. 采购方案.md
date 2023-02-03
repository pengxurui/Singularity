## [LCP 28. 采购方案](https://leetcode.cn/problems/4xy4Wx/description/)

## 题目描述

小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。

注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1

## 题目考点

双指针

## 题解
 
```
class Solution {
    fun purchasePlans(nums: IntArray, target: Int): Int {
        nums.sort()
        var result = 0
        var left = 0
        var right = nums.size - 1
        // 1,2,2,9
        // 1,1,1,1
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--
            } else {
                // left 与 [left + 1, right] 的组合
                result += right - left
                result %= 1000000007
                left++
            }
        }
        return result
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1) 
