## [LCP 40. 心算挑战](https://leetcode.cn/problems/uOAnQW/description/)

## 题目描述

「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。

## 题目考点

贪心、排序

参考：https://leetcode.cn/problems/uOAnQW/solutions/991699/pai-xu-tan-xin-by-endlesscheng-wgk7/

## 题解
 
```
class Solution {
    fun maxmiumScore(cards: IntArray, cnt: Int): Int {
        // 贪心思路：优先选择最后 cnt 张卡片
        cards.sort()
        var sum = 0
        for (index in cards.size - 1 downTo cards.size - cnt) {
            sum += cards[index]
        }
        if (sum and 1 == 0) return sum
        if (cnt == cards.size) return 0
        var choice1 = sum
        var choice2 = sum
        // case 1：将倒数第 cnt 张卡片替换为 [0,cnt) 最近另一种类型的卡片
        for (index in cards.size - cnt - 1 downTo 0) {
            // 末位异或结果为 1
            if ((cards[cards.size - cnt] xor cards[index]) and 1 == 1) {
                choice1 -= cards[cards.size - cnt]
                choice1 += cards[index]
                break
            }
        }
        if (choice1 and 1 == 1) choice1 = 0
        // case 2: 替换 (cnt,size) 中最近的另一种类型的卡片
        if (1 != cnt) {
            for (index in cards.size - cnt + 1 until cards.size) {
                // 末位异或结果为 1
                if ((cards[cards.size - cnt] xor cards[index]) and 1 == 1) {
                    choice2 -= cards[index]
                    break
                }
            }

            var choice2Temp = choice2
            if (choice2 != sum) {
                for (index in cards.size - cnt - 1 downTo 0) {
                    // 末位异或结果为 0
                    if ((cards[cards.size - cnt] xor cards[index]) and 1 == 0) {
                        choice2Temp += cards[index]
                        break
                    }
                }
            }
            // 无效
            if (choice2Temp == choice2) choice2 = sum else choice2 = choice2Temp
        }
        if (choice2 and 1 == 1) choice2 = 0
        return Math.max(choice1, choice2)
    }
}
```

**复杂度分析：**

- 时间复杂度：O(nlgn + n)
- 空间复杂度：O(lgn) 
