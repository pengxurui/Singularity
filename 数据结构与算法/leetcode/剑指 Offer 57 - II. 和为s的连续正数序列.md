## [剑指 Offer 57 - II. 和为s的连续正数序列](https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/description/?favorite=xb9nqhhg)

## 题目描述

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

## 题目考点

双指针

## 题解
 
```
class Solution {
    fun findContinuousSequence(target: Int): Array<IntArray> {
        // 方法 1：枚举从 1 到 target/2 开始的整数序列
        val result = LinkedList<IntArray>()
        var sum = 0
        outer@ for (start in 1..target / 2) {
            for (num in start until Integer.MAX_VALUE) {
                sum += num
                if (sum == target) {
                    result.add(IntArray(num - start + 1) {
                        start + it
                    })
                } else if (sum > target) {
                    sum = 0
                    continue@outer
                }
            }
        }
        return result.toTypedArray()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(target·target的平方根) 内层不会超过O(target的平方根)
- 空间复杂度：O(1) 

## 题解二（双指针呢 + 求和公式）

```
import java.util.*

class Solution {
    fun findContinuousSequence(target: Int): Array<IntArray> {
        // 方法 1：枚举从 1 到 target/2 开始的整数序列
        // 方法 2：使用双指针 + 求和公式
        val result = LinkedList<IntArray>()

        var left = 1
        var right = 2
        while (left < right) {
            val sum = (right - left + 1) * (left + right) / 2
            if (sum == target) {
                result.add(IntArray(right - left + 1) {
                    left + it
                })
                left++
            } else if (sum < target) {
                // 增加右区间
                right++
            } else {
                // 缩小左区间
                left++
            }
        }

        return result.toTypedArray()
    }
}
```

**复杂度分析：**

- 时间复杂度：O(target)
- 空间复杂度：O(1) 
