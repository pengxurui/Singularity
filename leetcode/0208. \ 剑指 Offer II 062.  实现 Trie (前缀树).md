## [208. 实现 Trie (前缀树)](https://leetcode.cn/problems/implement-trie-prefix-tree/description/)
## [剑指 Offer II 062. 实现前缀树](https://leetcode.cn/problems/QC3q1f/description/?favorite=e8X3pBZi)

## 题目描述

Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。

## 题目考点

字典树

## 题解
 
```
class Trie() {

    private val root = TireNode()

    fun insert(word: String) {
        var curNode = root
        for (element in word) {
            if (null == curNode.children[element - 'a']) {
                curNode.children[element - 'a'] = TireNode()
            }
            curNode = curNode.children[element - 'a']!!
        }
        curNode.wordCount++
    }

    fun search(word: String): Boolean {
        var curNode = root
        for (element in word) {
            curNode = curNode.children[element - 'a'] ?: return false
        }
        return curNode.wordCount > 0
    }

    fun startsWith(prefix: String): Boolean {
        var curNode = root
        for (element in prefix) {
            curNode = curNode.children[element - 'a'] ?: return false
        }
        return true
    }

    private class TireNode() {
        var wordCount = 0
        val children = arrayOfNulls<TireNode>(26)
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */
```

**复杂度分析：**

- 时间复杂度：O(S) 单词操作的时间复杂度取决于字符串长度 S
- 空间复杂度：O(T · 26)  T 是节点数，也就是所有字符串的长度之和
