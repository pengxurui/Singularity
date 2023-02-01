## [703. 数据流中的第 K 大元素](https://leetcode.cn/problems/kth-largest-element-in-a-stream/description/)
## [剑指 Offer II 059. 数据流的第 K 大数值](https://leetcode.cn/problems/jBjn9C/description/?favorite=e8X3pBZi)

## 题目描述

设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。

请实现 KthLargest 类：

KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。

## 题目考点

堆

## 题解
 
```
class KthLargest(val k: Int, nums: IntArray) {

    // 小顶堆
    private val heap = PriorityQueue<Int>()

    init {
        for (element in nums) {
            add(element)
        }
    }

    fun add(`val`: Int): Int {
        if (heap.size < k) {
            heap.offer(`val`)
        } else if (`val` > heap.peek()) {
            heap.poll()
            heap.offer(`val`)
        }
        return heap.peek()
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = KthLargest(k, nums)
 * var param_1 = obj.add(`val`)
 */
```

**复杂度分析：**

- 时间复杂度：O(lgk)
- 空间复杂度：O(k) 
