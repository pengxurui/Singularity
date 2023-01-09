## [1122. 数组的相对排序](https://leetcode.cn/problems/relative-sort-array/description/)
## [剑指 Offer II 075. 数组相对排序](https://leetcode.cn/problems/0H97ZC/description/)

## 题目描述

给定两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

## 题目考点

排序

## 题解一（自定义排序）

如果 xxx 和 yyy 都出现在哈希表中，那么比较它们对应的值 rank[x]\textit{rank}[x]rank[x] 和 rank[y]\textit{rank}[y]rank[y]；

如果 xxx 和 yyy 都没有出现在哈希表中，那么比较它们本身；

对于剩余的情况，出现在哈希表中的那个元素较小。

```
class Solution {
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        // arr2 中的每个元素都出现在 arr1 中，但出现次数不一定相同
        // num to index
        val arr2Map = HashMap<Int, Int>().apply {
            for (index in arr2.indices) {
                this[arr2[index]] = index
            }
        }
        return arr1.sortedWith(object : Comparator<Int> {
            override fun compare(s1: Int, s2: Int): Int {
                return if (arr2Map.containsKey(s1) && arr2Map.containsKey(s2)) {
                    // 同时包含在 arr2
                    arr2Map[s1]!! - arr2Map[s2]!!
                } else if (!arr2Map.containsKey(s1) && !arr2Map.containsKey(s2)) {
                    // 同时不包含在 arr2
                    s1 - s2
                } else if (arr2Map.containsKey(s1)) {
                    -1
                } else {
                    1
                }
            }

        }).toIntArray()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n + mlgm) m 是 arr1 数组的长度，n 是 arr2 数组的长度
- 空间复杂度：O(n + lgm) 散列表 + 排序，不考虑结果数组

## 题解二（计数排序）

使用长度为 1001 的数组记录 arr1 中每个数字的出现频次，再遍历 arr2（自定义排序）将 arr1 中对应频次的数字加入到结果中。

此时，arr2 已出现的部分已经处理完。

最后，再遍历 frequency 将剩下的部分升序排列

```
class Solution {
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        // 计数排序
        // 统计 arr1 中元素的出现频次
        val arr1Frequency = IntArray(1001) { 0 }
        var upper = 0
        for (element in arr1) {
            ++arr1Frequency[element]
            upper = Math.max(upper, element)
        }
        var index = 0
        // arr2 中出现的部分
        for (element in arr2) {
            if (arr1Frequency[element] > 0) {
                for (count in 0 until arr1Frequency[element]) {
                    arr1[index++] = element
                }
                arr1Frequency[element] = 0
            }
        }
        // arr2 中未出现部分
        for (element in 0..upper) {
            if (arr1Frequency[element] > 0) {
                for (count in 0 until arr1Frequency[element]) {
                    arr1[index++] = element
                }
                arr1Frequency[element] = 0
            }
        }
        return arr1
    }
}
```

空间优化：先计算 arr1 数组的最大值

```
class Solution {
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        // 计数排序
        // 查询 arr1 中的最大值
        var upper = 0
        for (element in arr1) {
            upper = Math.max(upper, element)
        }
        // 统计 arr1 中元素的出现频次
        val arr1Frequency = IntArray(upper + 1) { 0 }
        for (element in arr1) {
            ++arr1Frequency[element]
        }
        var index = 0
        // arr2 中出现的部分
        for (element in arr2) {
            if (arr1Frequency[element] > 0) {
                for (count in 0 until arr1Frequency[element]) {
                    arr1[index++] = element
                }
                arr1Frequency[element] = 0
            }
        }
        // arr2 中未出现部分
        for ((element, frequency) in arr1Frequency.withIndex()) {
            if (frequency > 0) {
                for (count in 0 until frequency) {
                    arr1[index++] = element
                }
            }
        }
        return arr1
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n + m + upper)
- 空间复杂度：O(1001) -> O(upper)
