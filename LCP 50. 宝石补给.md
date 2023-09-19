## [LCP 50. 宝石补给](https://leetcode.cn/problems/WHnhjV/?envType=daily-question&envId=2023-09-15)

## 题解（模拟）

```
class Solution {
    fun giveGem(gem: IntArray, operations: Array<IntArray>): Int {
        for ((x, y) in operations) {
            gem[y] += gem[x] / 2
            gem[x] -= gem[x] / 2
        }
        var mn = Integer.MAX_VALUE
        var mx = Integer.MIN_VALUE
        for (e in gem) {
            mn = min(mn, e)
            mx = max(mx, e)
        }
        return mx - mn
    }
}
```

- O(m + n)
- O(1)
