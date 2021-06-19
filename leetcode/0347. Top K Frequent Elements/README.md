## [347. Top K Frequent Elements](https://leetcode-cn.com/problems/top-k-frequent-elements/)

## [LeetCode 题解](https://leetcode-cn.com/problems/top-k-frequent-elements/solution/347-top-k-frequent-elements-qian-k-ge-gao-pin-yuan/)

## 题目描述

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

## 题目大意

前 k 个高频元素

## 解法一（小顶堆）
```
class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map.append(num)
        }
        // 小顶堆
        val queue = PriorityQueue<Map.Entry<Int, Int>>() { v1, v2 -> v1.value - v2.value }
        for (entity in map) {
            queue.offer(entity)
            if (queue.size > k) {
                queue.poll()
            }
        }
        return IntArray(k) {
            queue.poll().key
        }
    }

    // 扩展函数
    private fun <T> HashMap<T, Int>.append(key: T) where T : Any {
        if (null == this[key]) {
            this[key] = 1
        } else {
            this[key] = this[key]!! + 1
        }
    }
}
```
**复杂度分析：**
- 时间复杂度：O(nlgk)
- 空间复杂度：哈希表 O(n)，堆 O(k)，总体为 O(n)

## 解法二（快速排序思想）

```
class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map.append(num)
        }
        // 快速排序思想
        val iterator = map.iterator()
        val freqs = Array<Map.Entry<Int, Int>>(map.size) { iterator.next() }

        var from = 0
        var to = freqs.size - 1
        var targetNum = k

        val random = Random()

        fun partition(from: Int, to: Int): Int {
            // 随机化
            val randomIndex = random.nextInt(to - from + 1) + from
            freqs.swap(randomIndex, to)

            var lt = from
            for (index in from until to) {
                if (freqs[index].value > freqs[to].value) {
                    freqs.swap(index, lt++)
                }
            }

            freqs.swap(to, lt)
            return lt
        }

        while (true) {
            val pivot = partition(from, to)
            val leftNum = pivot - from + 1
            if (leftNum == targetNum) {
                break
            } else if (leftNum < targetNum) {
                targetNum -= leftNum
                from = pivot + 1
            } else {
                to = pivot - 1
            }
        }

        return IntArray(k) { freqs[it].key }
    }

    private fun <T> HashMap<T, Int>.append(key: T) where T : Any {
        if (null == this[key]) {
            this[key] = 1
        } else {
            this[key] = this[key]!! + 1
        }
    }

    private fun <T> Array<T>.swap(x: Int, y: Int) {
        val temp = this[x]
        this[x] = this[y]
        this[y] = temp
    }
}
```

**复杂度分析：**
- 时间复杂度：O(nlgn)
- 空间复杂度：哈希表 O(n)，数组 O(n)，总体为 O(n)

## 解法三（桶排序）

```
import java.util.*
import kotlin.collections.HashMap

class Solution {
    fun topKFrequent(nums: IntArray, k: Int): List<Int> {
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map.append(num)
        }
        // 桶排序思想
        val buckets = Array<MutableList<Int>?>(nums.size + 1) { null }

        // 频率为下标，值为该频率的数字列表
        for ((key, value) in map) {
            if (null == buckets[value]) {
                buckets[value] = LinkedList()
            }
            buckets[value]!!.add(key)
        }

        val result = LinkedList<Int>()

        for (index in buckets.size - 1 downTo 0) {
            if (result.size < k && null != buckets[index]) {
                result.addAll(buckets[index]!!)
            }
        }

        return result
    }

    private fun <T> HashMap<T, Int>.append(key: T) where T : Any {
        if (null == this[key]) {
            this[key] = 1
        } else {
            this[key] = this[key]!! + 1
        }
    }

    private fun <T> Array<T>.swap(x: Int, y: Int) {
        val temp = this[x]
        this[x] = this[y]
        this[y] = temp
    }
}
```

**复杂度分析：**
- 时间复杂度：O(n)
- 空间复杂度：哈希表 O(n)，数组 O(n)，总体为 O(n)
