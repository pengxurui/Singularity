## [LCP 33. 蓄水](https://leetcode.cn/problems/o8SXZn/description/)

## 问题描述

给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：

升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
蓄水：将全部水桶接满水，倒入各自对应的水缸
每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。

注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。

## 题解一（枚举 + 贪心）

```
class Solution {
    fun storeWater(bucket: IntArray, vat: IntArray): Int {
        if (bucket.size != bucket.size) return -1
        if (bucket.size == 0) return 0
        var ret = Integer.MAX_VALUE
        // 枚举蓄水次数，那么水桶的最低容量为 vat_i/x，升级次数为 vat_i/x - bucket_i
        val mx = vat.max()!!
        if (0 == mx) return 0
        // 枚举蓄水次数
        for (i in 1 .. mx) {
            var x = i
            // 枚举水桶
            for (j in bucket.indices) {
                x += Math.max(0, ((vat[j] + i - 1) / i /* 向上取整 */ - bucket[j]))
            }
            ret = Math.min(ret, x)
        }
        return ret
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nC)
- 空间复杂度：O(1)

## 题解二（枚举 + 优先队列 · TODO）

```
```
