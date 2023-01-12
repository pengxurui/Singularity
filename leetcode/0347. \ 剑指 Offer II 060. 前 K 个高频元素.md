## [347. Top K Frequent Elements](https://leetcode-cn.com/problems/top-k-frequent-elements/)
## [剑指 Offer II 060. 出现频率最高的 k 个数字](https://leetcode.cn/problems/g5c51o/)

## [LeetCode 题解](https://leetcode-cn.com/problems/top-k-frequent-elements/solution/347-top-k-frequent-elements-qian-k-ge-gao-pin-yuan/)

## 题目描述

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

## 题目大意

前 k 个高频元素

## 解法一（小顶堆）
```
class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequencyMap = HashMap<Int, Int>()
        for (element in nums) {
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
        }
        // 小顶堆（数组升序）
        val heap = PriorityQueue<Int>() { s1, s2 ->
            frequencyMap[s1]!! - frequencyMap[s2]!!
        }
        // 利用堆的性质
        for ((element, frequency) in frequencyMap) {
            heap.offer(element)
            // 超过 K 个数
            if (heap.size > k) {
                heap.poll()
            }
        }
        return IntArray(k) {
            heap.poll()
        }
    }
}
```
**复杂度分析：**
- 时间复杂度：O(nlgk)
- 空间复杂度：哈希表 O(n)，堆 O(k)，总体为 O(n)

## 解法二（暴力排序）

```
class Solution {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // 计数
        val frequencyMap = HashMap<Int, Int>()
        for (element in nums) {
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
        }
        val resultList = LinkedList<Int>()
        for ((element, _) in frequencyMap) {
            resultList.add(element)
        }
        // 降序（Lambda 会报错）
        resultList.sortWith(Comparator { s1, s2 ->
            frequencyMap[s2]!! - frequencyMap[s1]!!
        })
        return resultList.subList(0, k).toIntArray()
    }
}
```

**复杂度分析：**
- 时间复杂度：O(nlgn) 
- 空间复杂度：哈希表

## 解法三（快速排序思想）

```
class Solution {

    // private val random = Random(0)

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // 计数
        val frequencyMap = HashMap<Int, Int>()
        for (element in nums) {
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
        }
        val elements: Array<Map.Entry<Int, Int>> = frequencyMap.entries.toTypedArray()
        // 快速排序
        elements.quickSort(k)
        return IntArray(k) { elements[it].key }
    }


    // 按照出现频率降序排列
    private fun Array<Map.Entry<Int, Int>>.quickSort(k: Int) {
        var left = 0
        var right = size - 1
        while (left < right) {
            val pivot = partition(left, right)
            if (pivot == k - 1)
            // 完成
                return
            else if (pivot < k - 1)
            // 左区间已经满足
                left = pivot + 1
            else
            // 右区间已经满足
                right = pivot - 1
        }
    }

    // 分区
    // return：索引
    private fun Array<Map.Entry<Int, Int>>.partition(left: Int, right: Int): Int {
        val randomIndex = (Math.random() * (right - left + 1)).toInt() + left
        // val randomIndex = random.nextInt(right - left + 1) + left
        // 移到数组末位
        this.swap(randomIndex, right)

        // [2,3,4,5,6,9,0] -> [2,3,0,5,6,9,4] -> [5,3,0,2,6,9,4] -> [5,6,0,2,3,9,4] -> [5,6,9,2,3,0,4] -> [5,6,9,4,3,0,2]
        var pivot = left
        for (index in left until right) {
            if (this[index].value > this[right].value) {
                this.swap(index, pivot++)
            }
        }
        swap(pivot, right)
        return pivot
    }

    private fun <T> Array<T>.swap(from: Int, to: Int) {
        val temp = this[from]
        this[from] = this[to]
        this[to] = temp
    }
}
```

**复杂度分析：**
- 时间复杂度：O(n) 排序过程每次只执行一半区间，所以时间只需要 O(n)
- 空间复杂度：哈希表 O(n)，数组 O(n)，总体为 O(n)

## 解法四（桶排序）

```
class Solution {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // 计数
        val frequencyMap = HashMap<Int, Int>()
        for (element in nums) {
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
        }
        // 桶排序：[0, 数组长度 / 最大频率]
        // frequency to List<element>
        val buckets = Array<MutableList<Int>?>(nums.size + 1) { null }
        for ((element, frequency) in frequencyMap) {
            if (null == buckets[frequency]) {
                buckets[frequency] = LinkedList<Int>()
            }
            buckets[frequency]!!.add(element)
        }

        val result = LinkedList<Int>()

        for (index in buckets.size - 1 downTo 0) {
            if (result.size >= k) break
            if (null != buckets[index]) {
                result.addAll(buckets[index]!!)
            }
        }
        return result.toIntArray()
    }
}
```

// 空间优化：

```
class Solution {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // 计数
        var maxFrequuency = 0
        val frequencyMap = HashMap<Int, Int>()
        for (element in nums) {
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
            maxFrequuency = Math.max(maxFrequuency, frequencyMap[element]!!)
        }
        // 桶排序：[0, 数组长度 / 最大频率]
        // frequency to List<element>
        val buckets = Array<MutableList<Int>?>(maxFrequuency + 1) { null }
        for ((element, frequency) in frequencyMap) {
            if (null == buckets[frequency]) {
                buckets[frequency] = LinkedList<Int>()
            }
            buckets[frequency]!!.add(element)
        }

        val result = LinkedList<Int>()

        for (index in buckets.size - 1 downTo 0) {
            if (result.size >= k) break
            if (null != buckets[index]) {
                result.addAll(buckets[index]!!)
            }
        }
        return result.toIntArray()
    }
}
```

**复杂度分析：**
- 时间复杂度：O(n)
- 空间复杂度：哈希表 O(n)，数组 O(maxFrequuency)，总体为 O(n)
