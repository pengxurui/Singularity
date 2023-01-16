## [220. 存在重复元素 III](https://leetcode.cn/problems/contains-duplicate-iii/)

## 题目描述

给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。

如果存在则返回 true，不存在返回 false。

## 题目考点

散列表

## 题解一（散列表 + 排序 · 错误）
 
```
class Solution {
    fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
        if (nums.size <= 1) return false
        // 双指针：不断右移右指针到窗口不超过 k，差值大于 t 时右移左指针
        // 问题：如何快速地求出滑动窗口的最大值和最小值
        // 排序：理解错误，题目是寻找接近的数
        nums.sort()
        var left = 0
        for (right in 1 until nums.size) {
            // 窗口不超过 k
            while (right - left > k) left++
            if (left != right && Math.abs(nums[right] - nums[left]) <= t) return true
            // 差值不超过 t
            while (nums[right] - nums[left] > t) left++
        }
        return false
    }
}
```

## 题解二（滑动窗口 + 队列）

```
class Solution {
    fun containsNearbyAlmostDuplicate(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
        // 滑动窗口 + 队列
        val queue = LinkedList<Int>()
        for (index in 0 until nums.size) {
            // 检查窗口内是否存在 [x - t, x + t] 范围的数：
            for (element in queue) {
                if (element in nums[index] - valueDiff..nums[index] + valueDiff) return true
            }
            queue.add(nums[index])
            if (queue.size > indexDiff) {
                queue.removeAt(0)
            }
        }
        return false
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nk)
- 空间复杂度：O(k) 

## 题解三（滑动窗口 + 二叉搜索树）

使用有序集合快速寻找目标范围的数：判断大于 x - t 的最小数是否小于 x + t

```
class Solution {
    fun containsNearbyAlmostDuplicate(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
        // 滑动窗口 + 二叉搜索树
        val set = TreeSet<Int>()
        for (index in 0 until nums.size) {
            // 检查窗口内是否存在 [x - t, x + t] 范围的数：
            val ceil = set.ceiling(nums[index] - valueDiff)
            if (null != ceil && ceil <= nums[index] + valueDiff) return true
            set.add(nums[index])
            if (set.size > indexDiff) {
                set.remove(nums[index - indexDiff])
            }
        }
        return false
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgk)
- 空间复杂度：O(k) 

## 题解三（桶排序）

由于对于每个元素，我们需要在大小为 k 的二叉搜素树上搜索接近 x 的数，所以无法实现线性时间复杂的。

我们可以换个思路：将多个元素划分到同一个桶中，桶的大小为 t + 1（x 影响的元素值区间是 [x - t, x + t])），

- 情况 1：如果两个元素位于同一个桶中，那么两个元素的差值一定不超过 t；
- 情况 2：如果两个元素位于相邻的桶中，那么需要检查两个元素的差值不超过 t；
- 情况 3：如果两个元素不位于同一个桶或相邻的桶中，那么两个元素的差值一定超过 t；

分桶的主体逻辑是 nums / size，例如：0 1 2 3 | 4 5 6 7 | 8 9 10 11 | 12 13 14 15 | …

但是在处理负数时需要特殊处理：由于负数比负数少了一个数，所以继续使用 num / size 会出错，例如 [-4, | -3,-2,-1] 会被分在不同的桶中。
为了正确处理负数，我们将负数在坐标轴上整体右移得到 (num + 1）/ size，这样就能正确分割 [-4,-3,-2,-1] 到同一个桶（0 号）中。
但是，0 号桶已经在正数部分使用过了，所以需要减一区分。

在实现滑动窗口时，我们会将 nums[index - k] 元素从它所在的桶中移除，但这就不有一个问题，如果在 [index-k,index] 的范围内也曾经出现重复元素，那么草率地将它移除就会出错。

其实不会出错，因为重复的元素早在情况 1 中短路跳出，所以是事实上每个桶中最多只会存储一个元素。

```
class Solution {
    fun containsNearbyAlmostDuplicate(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
        // 滑动窗口 + 桶排序
        // 桶 to 元素
        val buckets = HashMap<Int, Int>()
        val bucketSize = valueDiff + 1
        for ((index, element) in nums.withIndex()) {
            val bucket = getBucket(element, bucketSize)
            // 检查相同桶
            if (buckets.containsKey(bucket)) return true
            // 检查相邻桶
            if (buckets.containsKey(bucket - 1) && nums[index] - buckets[bucket - 1]!! <= valueDiff) return true
            if (buckets.containsKey(bucket + 1) && buckets[bucket + 1]!! - nums[index] <= valueDiff) return true
            // 建立新桶
            buckets[bucket] = nums[index]
            // 滑动窗口
            if (index >= indexDiff) {
                buckets.remove(getBucket(nums[index - indexDiff], bucketSize))
            }
        }
        return false
    }

    // size：桶的大小
    // return：桶编号
    private fun getBucket(num: Int, size: Int): Int {
        return if (num >= 0) num / size else (num + 1) / size - 1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(k) 
