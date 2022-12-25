## [剑指 Offer 40. 最小的k个数](https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/description/)

## 题目描述

输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

## 题目考点

堆

## 题解
 
```
class Solution {
    fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
        if (0 == k) return IntArray(0)
        if (0 == arr.size) return arr
        // 大顶堆，如果比堆顶小则入队
        val queue = PriorityQueue<Int>() { s1, s2 ->
            s2 - s1
        }
        for (element in arr) {
            if (queue.size < k) {
                queue.offer(element)
                continue
            }
            if (element < queue.peek()) {
                queue.offer(element)
                queue.poll()
            }
        }
        return IntArray(queue.size) {
            queue.poll()
        }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgk)
- 空间复杂度：O(k) 
