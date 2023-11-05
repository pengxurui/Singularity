## [LCP 06. 拿硬币](https://leetcode.cn/problems/na-ying-bi/description/)

## 题解（模拟）

```
class Solution {
    fun minCount(coins: IntArray): Int {
        return coins.fold(0) { arr, num -> arr + (num + 1) / 2 }
    }
}
```

**复杂度分析：**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
