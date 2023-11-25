## 基础算法核心代码（Kotlin 语言描述）

## 1. 杂项

### 1.1 位运算

**二进制操作：**

![](https://github.com/pengxurui/Singularity/assets/25008934/853aaaa5-0b0e-43e7-9e49-65c3677fa055)

**常用库函数：**

| 操作 | Java | Python | C++ |
| --- | --- | --- | --- |
| 前缀 0 的个数 | Integer.numberOfLeadingZeros(s) | 32 - s.bit_length() | \_\_builtin_clz(s) |
| 后缀 0 的个数 | Integer.numberOfTrailingZeros(s) | (s&-s).bit_length()-1 | \_\_builtin_ctz(s) |
| 二进制位 1 的个数 | Integer.bitcount(s) | s.bit_count() | \_\_builtin_popcount(s) |
| 二进制位长度 | 32-Integer.numberOfLeadingZeros(s) | s.bit_length() | bits.Len(s) |

### 1.2 离散化

[OI - 离散化](https://oi-wiki.org/misc/discrete/)

当数据范围很大而影响最终结果的只有数据之间的相对大小事，我们可以使用离散化来预处理数据。离散化本质上是一种哈希，它在保证数据原有的排序关系前提下对数据进行哈希操作。

---

## 2. 数据结构

### 树

完全二叉树

![](https://github.com/pengxurui/Singularity/assets/25008934/26438c18-03fa-4765-b576-91a4d3963aac)

### 并查集

z

### 堆

[OI - 二叉堆](https://oi-wiki.org/ds/binary-heap/)

> 二叉堆是一棵 **完全二叉树** ，其每个节点都有一个键值，且每个节点的键值都大于等于/小于等于其父节点的键值。每个节点的键值都大于等于其父节点键值的堆叫做小根堆，否则叫做大根堆。

**二叉堆上浮：**

```kotlin
fun IntArray.swin(x: Int) {
		var i = x
		while (i >= 0 && this[i] > this[(i - 1) / 2]) {
				this.swap(i, (i - 1) / 2)
				i = (i - 1) / 2
		}
}
```

**二叉堆下沉：**

```kotlin
fun IntArray.sink(x: Int) {
    var i = x
    while (i * 2 + 1 < size) {
        var j = i * 2 + 1
        if (j + 1 < size && this[j + 1] > this[j]) j = j + 1
        if (this[i] >= this[j]) break
        this.swap(i, j)
        i = j
    }
}
```



**二叉堆的插入：**

> 将新元素插入到完全二叉树最后的位置，然后执行上浮操作，时间复杂度是 $O(lgn)$
> 

```kotlin

```

**二叉堆的删除：**

> 将根节点与完全二叉树最后的位置交换，然后执行下沉操作，时间复杂度是 $O(lgn)$
> 

```kotlin

```

**二叉堆的堆化：**

> 辅助数据结构：从空堆开始一个个插入元素，整体时间复杂度是 $O(nlgn)$
> 

> 原地堆化 1：从根节点开始，按照 BFS 顺序执行上浮操作，整体时间复杂度是 $O(nlgn)$；
> 

> 原地堆化 2：从第 n - 1 层节点开始，执行下沉操作合并两个子堆，整体复杂度是 $O(n)$，二叉堆能实现 $O(n)$ 建堆的本质是堆的排序性质很弱。
> 

```kotlin
fun IntArray.heapify() {
    for (i in size / 2 - 1 downTo 0) {
        this.sink(i)
    }
}
```

**对顶堆：**

> 动态维护一个序列上第 $k$ 大的数，且 $k$ 值可能会发生变化。
> 

> [LeetCode 480. 滑动窗口中位数](https://leetcode.cn/problems/sliding-window-median)
> 

```kotlin

```

### 块状数据结构

> [OI - 分块思想](https://oi-wiki.org/ds/decompose/)
> 
> 
> 分块的基本思想是，通过对原数据的适当划分，并在划分后的每一个块上预处理部分信息，是一种优化的暴力。分块的时间复杂度主要取决于分块的块长，一般可以使用 \sqrt(n) 作为块长，块的数量也是 $\sqrt n$，单次查询操作的时间复杂度是 $O(\sqrt n)$

### 线段树

[OI - 线段树](https://oi-wiki.org/ds/seg/)

> **区间信息数据结构**
> 
> - 1、静态数组求区间和：「前缀和数组」、「树状数组」、「线段树」
> - 2、频繁单点更新，求区间和：「树状数组」、「线段树」
> - 3、频繁区间更新，求具体位置：「差分数组」
> - 4、频繁区间更新，求区间和：「Lazy 线段树」

> 线段树可以在 $O(lgn)$ 时间复杂度内实现单点修改、区间修改、区间查询（区间求和，求区间最大值，求区间最小值）等操作。
> 

![](https://github.com/pengxurui/Singularity/assets/25008934/81978117-9027-49a2-9c6f-c5d80a104221)

**Lazy 线段树**

> 如果执行区间修改操作时，要把所有包含在区间中的节点都遍历一次、修改一次，时间复杂度会退化到 $O(n)$，我们这里要引入一个叫做**「Lazy 懒惰标记」**的技巧，延迟对节点信息的修改：在每个节点上增加 lazy 标记，每次修改操作向下分发到包含左右子区间的节点停止。例如修改 $[1,3]$ 区间，只需要向下分发到 $[1,2]$ 和 $[3,3]$ 区间停止，不需要分发到 $[1,1]$ 和 $[2,2]$。
> 

**支持区间修改为具体值的线段树：**

```kotlin
private class LazySegementTree(private val data: IntArray) {
    // 线段树节点（区间范围与区间值）
    private class Node(val left: Int, val right: Int, var sum: Int, var lazy: Boolean = false, var value: Int = 0)

    // 线段树数组
    private val tree = Array<Node?>(4 * data.size) { null } as Array<Node>

    // 左子节点的索引
    private val Int.left get() = this * 2 + 1

    // 右子节点的索引
    private val Int.right get() = this * 2 + 2

    init {
        // 建树
        buildNode(0, 0, data.size - 1)
    }

    // 构建线段树节点
    private fun buildNode(index: Int, left: Int, right: Int) {
        if (left == right) {
            // 叶子节点
            tree[index] = Node(left, right, data[left])
            return
        }
        val mid = (left + right) ushr 1
        // 构建左子节点
        buildNode(index.left, left, mid)
        // 构建左子节点
        buildNode(index.right, mid + 1, right)
        // 合并左右子节点
        tree[index] = Node(left, right, tree[index.left].sum + tree[index.right].sum)
    }

    // 区间更新
    fun update(left: Int, right: Int, value: Int) {
        update(0, left, right, value)
    }

    // 区间更新
    private fun update(index: Int, left: Int, right: Int, value: Int) {
        // 1、当前节点不处于区间范围内
        if (tree[index].left > right || tree[index].right < left) return
        // 2、当前节点完全处于区间范围之内
        if (tree[index].left >= left && tree[index].right <= right) {
            lazyUpdate(index)
            return
        }
        // 3、pushdown 到子节点
        if (tree[index].lazy) {
            lazyUpdate(index.left)
            lazyUpdate(index.right)
            tree[index].lazy = false
        }
        // 4、更新左子树
        update(index.left, left, right, value)
        // 5、更新右子树
        update(index.right, left, right, value)
        // 6、合并子节点的结果
        tree[index].sum = tree[index.left].sum + tree[index.right].sum
    }

    // 单点更新
    fun set(pos: Int, value: Int) {
        set(0, pos, value)
    }

    // 单点更新
    private fun set(index: Int, pos: Int, value: Int) {
        // 1、当前节点不处于区间范围内
        if (tree[index].left > pos || tree[index].right < pos) return
        // 2、叶子节点
        if (tree[index].left == tree[index].right) {
            lazyUpdate(index)
            return
        }
        // 3、pushdown 到子节点
        if (tree[index].lazy) {
            lazyUpdate(index.left)
            lazyUpdate(index.right)
            tree[index].lazy = false
        }
        // 4、更新左子树
        set(index.left, pos, value)
        // 5、更新右子树
        set(index.right, pos, value)
        // 6、合并子节点的结果
        tree[index].sum = tree[index.left].sum + tree[index.right].sum
    }

    // 区间查询
    fun query(left: Int, right: Int): Int {
        return query(0, left, right)
    }

    // 区间查询
    private fun query(index: Int, left: Int, right: Int): Int {
        // 1、当前节点不处于区间范围内
        if (tree[index].left > right || tree[index].right < left) return 0
        // 2、当前节点完全处于区间范围之内
        if (tree[index].left >= left && tree[index].right <= right) return tree[index].sum
        // 3、pushdown 到子节点
        if (tree[index].lazy) {
            lazyUpdate(index.left)
            lazyUpdate(index.right)
            tree[index].lazy = false
        }
        // 4、合并子节点的结果
        return query(index.left, left, right) + query(index.right, left, right)
    }

    // 懒更新
    private fun lazyUpdate(index: Int) {
        tree[index].sum = (tree[index].right - tree[index].left + 1) * tree[index].value
        tree[index].lazy = true
    }
}
```

**支持区间增加差值的线段树：**

```kotlin
private class LazySegementTree(private val n: Int) {
    // 线段树节点（区间范围与区间值）
    private class Node(val left: Int, val right: Int, var sum: Int, var lazy: Boolean = false, var delta: Int = 0)

    // 线段树数组
    private val tree = Array<Node?>(4 * n) { null } as Array<Node>

    // 左子节点的索引
    private val Int.left get() = this * 2 + 1

    // 右子节点的索引
    private val Int.right get() = this * 2 + 2

    init {
        // 建树
        buildNode(0, 0, n - 1)
    }

    // 构建线段树节点
    private fun buildNode(index: Int, left: Int, right: Int) {
        if (left == right) {
            // 叶子节点
            tree[index] = Node(left, right, 0)
            return
        }
        val mid = (left + right) ushr 1
        // 构建左子节点
        buildNode(index.left, left, mid)
        // 构建左子节点
        buildNode(index.right, mid + 1, right)
        // 合并左右子节点
        tree[index] = Node(left, right, tree[index.left].sum + tree[index.right].sum)
    }

    // 区间更新
    fun update(left: Int, right: Int, delta: Int) {
        update(0, left, right, delta)
    }

    // 区间更新
    private fun update(index: Int, left: Int, right: Int, delta: Int) {
        // 1、当前节点不处于区间范围内
        if (tree[index].left > right || tree[index].right < left) return
        // 2、当前节点完全处于区间范围之内
        if (tree[index].left >= left && tree[index].right <= right) {
            lazyUpdate(index, delta)
            return
        }
        // 3、pushdown 到子节点
        if (tree[index].lazy) {
            lazyUpdate(index.left, tree[index].delta)
            lazyUpdate(index.right, tree[index].delta)
            tree[index].lazy = false
            tree[index].delta = 0
        }
        // 4、更新左子树
        update(index.left, left, right, delta)
        // 5、更新右子树
        update(index.right, left, right, delta)
        // 6、合并子节点的结果
        tree[index].sum = tree[index.left].sum + tree[index.right].sum
    }

    // 单点更新
    fun set(pos: Int, delta: Int) {
        set(0, pos, delta)
    }

    // 单点更新
    private fun set(index: Int, pos: Int, delta: Int) {
        // 1、当前节点不处于区间范围内
        if (tree[index].left > pos || tree[index].right < pos) return
        // 2、叶子节点
        if (tree[index].left == tree[index].right) {
            lazyUpdate(index, delta)
            return
        }
        // 3、pushdown 到子节点
        if (tree[index].lazy) {
            lazyUpdate(index.left, tree[index].delta)
            lazyUpdate(index.right, tree[index].delta)
            tree[index].lazy = false
            tree[index].delta = 0
        }
        // 4、更新左子树
        set(index.left, pos, delta)
        // 5、更新右子树
        set(index.right, pos, delta)
        // 6、合并子节点的结果
        tree[index].sum = tree[index.left].sum + tree[index.right].sum
    }

    // 区间查询
    fun query(left: Int, right: Int): Int {
        return query(0, left, right)
    }

    // 区间查询
    private fun query(index: Int, left: Int, right: Int): Int {
        // 1、当前节点不处于区间范围内
        if (tree[index].left > right || tree[index].right < left) return 0
        // 2、当前节点完全处于区间范围之内
        if (tree[index].left >= left && tree[index].right <= right) return tree[index].sum
        // 3、pushdown 到子节点
        if (tree[index].lazy) {
            lazyUpdate(index.left, tree[index].delta)
            lazyUpdate(index.right, tree[index].delta)
            tree[index].lazy = false
            tree[index].delta = 0
        }
        // 4、合并子节点的结果
        return query(index.left, left, right) + query(index.right, left, right)
    }

    // 懒更新
    private fun lazyUpdate(index: Int, delta: Int) {
        tree[index].sum += (tree[index].right - tree[index].left + 1) * delta
        tree[index].delta += delta
        tree[index].lazy = true
    }
}
```

**动态开点线段树：**

> 
> 

```kotlin

```

### 树状数组

[OI - 树状数组](https://oi-wiki.org/ds/fenwick/)

> 树状数组是一种支持单点修改和区间查询的，代码量小的数据结构。树状数组能把一段前缀和 [1, n] 拆分为不多于 lgn 段区间，把合并 n 个信息转换为合并 lgn 个信息。事实上，树状数组能解决的问题是线段树能解决的问题的子集。
> 

![](https://github.com/pengxurui/Singularity/assets/25008934/d2648c10-1161-419c-bb10-eda25b0fa2e4)

**朴素树状数组：**

```kotlin
// 树状数组
private class BST(private val n: Int) {

    // base 1
    private val data = IntArray(n + 1)

    init {
        // O(nlgn) 建树
        // for (i in 0 .. n) {
        //     update(i, 1)
        // }
        // O(n) 建树
        for (i in 1 .. n) {
            data[i] += 1
            val parent = i + lowbit(i)
            if (parent <= n) data[parent] += data[i]
        }
    }

    fun rangeSum(i1: Int, i2: Int): Int {
        return preSum(i2 + 1) - preSum(i1)
    }

    fun dec(i: Int) {
        update(i + 1, -1)
    }

    private fun preSum(i: Int): Int {
        var x = i
        var sum = 0
        while (x > 0) {
            sum += data[x]
            x -= lowbit(x)
        }
        return sum
    }

    private fun update(i: Int, delta: Int) {
        var x = i
        while (x <= n) {
            data[x] += delta
            x += lowbit(x)
        }
    }

    private fun lowbit(x: Int) = x and (-x)
}
```

### 二叉搜索树 & 平衡树

**Treap 树堆**

> [OI - Treap](https://oi-wiki.org/ds/treap/)
> 
> 
> Treap（树堆）是一种符合二叉搜索树和堆的弱平衡的二叉搜索树，它通过随机化的 priority 属性以及堆的性质打断了节点的插入顺序，从而避免二叉搜索树退化成链的问题。
> 

### ST 表

### 跳表

### 霍夫曼树

---

## 字符串

### 字符串基础概念

### Kotlin String 标准库

### 字符串匹配

[OI - 字符串匹配](https://oi-wiki.org/string/match/)

字符串匹配：给定主串和模式串（Pattern），要求在主串中寻找模式串。

> **暴力解法：时间复杂度 $O(nm)$**
> 
> 
> 从主串的第一个字符开始和模式串的第一个字符进行比较，若相等，则继续比较二者的后续字符；否则，模式串回退到第一个字符，重新和主串的第二个字符进行比较。如此往复，直到主串或模式串中所有字符比较完毕。
> 

> **滚动哈希（字符串哈希）：时间复杂度 $O(n + m)$**
> 
> 
> 定义将字符串映射到整数的 $Hash$ 函数，通过比较哈希值来判断两个字符串是否相等（基于 String 的散列表本质上也是字符串哈希）。在计算字符串哈希时，一般会使用滚动哈希的技巧优化时间复杂度中的常数 $C$：已知主串中长度为 $C$ 的字符串 $[i, j]$ 的哈希值 $h$，以 $O(1)$ 时间复杂求出 $[i + 1, j + 1]$ 的哈希值.
> 

> **KMP 算法：时间复杂度 $O(n + m)$**
> 

**字符串 Hash 函数设计**

多项式哈希

> [187. 重复的DNA序列](https://leetcode.cn/problems/repeated-dna-sequences/description/)
> 

**KMP 算法**

最长回文子串与 Manacher 算法

---

## 数学

### 组合数学

**组合数**

预处理组合数：

```kotlin
val H = 15
val comb = Array(H * 2 + 1) { IntArray(H + 1) }
init {
    comb[0][0] = 1
    for (i in 1 .. H * 2) {
        comb[i][0] = 1
        for (j in 1 .. min(i, H)) {
            comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j]
        }
    }
}
```

### 排列组合

全排列：

```kotlin
private fun permute(nums: IntArray, start: Int, result: MutableList<List<Int>>) {
    if (start == nums.size) {
        result.add(nums.toList())
        return
    }
    for (index in start until nums.size) {
        nums.swap(index, start)
        permute(nums, start + 1, result)
        nums.swap(index, start)
    }
}

private fun IntArray.swap(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
}
```

### 判断素数

[OI - 素数](https://oi-wiki.org/math/number-theory/prime)

枚举法：枚举 $[2, n - 1]$，时间复杂度是 $O(n)$

```kotlin
fun isPrime(num: Int): Boolean {
    var x = 2
    while (x < num) {
        if (num % x == 0) return false
        x++
    }
    return true
}
```

枚举优化：枚举 $[2, \sqrt{n}]$，时间复杂度是 $O(\sqrt{n})$

```kotlin
fun isPrime(num: Int): Boolean {
    var x = 2
    while (x * x <= num) {
        if (num % x == 0) return false
        x++
    }
    return true
}
```

### 枚举素数

[OI - 素数筛法](https://oi-wiki.org/math/number-theory/sieve/)

> 枚举法：枚举 $[2, n]$ ，判断它是不是质数，整体时间复杂度是 $O(n\sqrt{n})$
> 

```kotlin
// 暴力求质数
fun getPrimes(max: Int): IntArray {
    val primes = LinkedList<Int>()
    for (num in 2..max) {
        if (isPrime(num)) primes.add(num)
    }
    return primes.toIntArray()
}

// 质数判断
fun isPrime(num: Int): Boolean {
    var x = 2
    while (x * x <= num) {
        if (num % x == 0) return false
        x++
    }
    return true
}
```

> Eratosthenes 埃氏筛：如果 $x$ 是质数，那么 $x$ 的整数倍 $2x$、$3x$ 一定不是质数。我们设 `isPrime[i]` 表示 $i$ 是否为质数。从小开始遍历，如果 $i$ 是质数，则同时将所有倍数标记为合数，整体时间复杂度是 $O(nlgn)$
> 

> 为什么要从 $x^2$, $2x^2$ 开始标记，而不是 $2x$, $3x$ 开始标记，因为 $2x$, $3x$ 已经被小于 $x$ 的质数标记过。
> 

![](https://github.com/pengxurui/Singularity/assets/25008934/76810a34-f078-4c73-a100-0ced1db2d2ae)

```kotlin
// 埃氏筛求质数
val primes = LinkedList<Int>()
val isPrime = BooleanArray(U + 1) { true }
for (i in 2..U) {
    // 检查是否为质数，这里不需要调用 isPrime() 函数判断是否质数，因为它没被小于它的数标记过，那么一定不是合数
    if (!isPrime[i]) continue
    primes.add(i)
    // 标记
    var x = i * i
    while (x <= U) {
        isPrime[x] = false
        x += i
    }
}
```

> Euler 欧氏线性筛：尽管我们从 $x^2$ 开始标记来减少重复标记，但埃氏筛还是会重复标记合数。为了避免重复标记，标记 $x$ 与 “小于等于 $x$ 的最小质因子的质数” 的乘积为合数，保证每个合数只被标记最小的质因子标记，整体时间复杂度是 $O(n)$
> 

```kotlin
// 线性筛求质数
val primes = LinkedList<Int>()
val isPrime = BooleanArray(U + 1) { true }
for (i in 2..U) {
    // 检查是否为质数，这里不需要调用 isPrime() 函数判断是否质数，因为它没被小于它的数标记过，那么一定不是合数
    if (isPrime[i]) {
        primes.add(i)
    }
    // 标记
    for (e in primes) {
        if (i * e > U) break
        isPrime[i * e] = false
        if (i % e == 0) break // 重点
    }
}
```

> 提示：筛法求素数也可用于求每个数的最小质因子（最先标记的因子一定是最小的因子）。
> 

### 质因数分解

[OI - 分解质因数](https://oi-wiki.org/math/number-theory/pollard-rho/)

> 单次质因数分解算法：扫描 $[2, \sqrt{n}]$，时间复杂度 $O(\sqrt{n})$
> 

```kotlin
var primes = LinkedList<Int>()
var x = e
var prime = 2
while (prime * prime <= x) {
    if (x % prime == 0) {
        primes.add(prime)
        while (x % prime == 0) x /= prime // 消除相同因子
    }
    prime++
}
if (x > 1) primes.add(x) // 剩余的质因子
```

> 筛法：如果 $x$ 是质数，那么 $x$ 的整数倍 $2x$、$3x$ 一定包含质数 x。我们设 `prime[i]` 表示 $i$ 的质数列表。从小开始遍历，如果 $i$ 是质数，则同时将其添加到所有倍数的质数列表，整体时间复杂度是 $O(nlgn)$
> 

```kotlin

```

---

## 动态规划

### 树形 DP

[OI - 树形 DP](https://oi-wiki.org/dp/tree/)

```kotlin
fun dp(i: Int, fa: Int) {
    for (to in graph[i]) {
        if (to == fa) continue
        // 状态转移
        dp(to, i)
    }
}
```

**换根 DP**

> 树形 DP 中的换根 DP 问题又被称为二次扫描，通常不会指定根结点，并且根结点的变化会对一些值，例如子结点深度和、点权和等产生影响。通常需要两次 DFS，第一次 DFS 预处理诸如深度，点权和之类的信息，在第二次 DFS 开始运行换根动态规划。
> 

```kotlin
fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
    val son = IntArray(n)
    val dp = IntArray(n)

    // 建图
    val graph = Array(n) { LinkedList<Int>() }
    for ((from, to) in edges) {
        graph[from].add(to)
        graph[to].add(from)
    }

    // 以 0 为根节点
    fun dfs(i: Int, fa: Int, depth: Int) {
        dp[0] += depth
        son[i] += 1
        for (to in graph[i]) {
            if (to == fa) continue
            dfs(to, i, depth + 1)
            son[i] += son[to]
        }
    }

    fun dp(i: Int, fa: Int) {
        for (to in graph[i]) {
            if (to == fa) continue
            // 状态转移
            dp[to] = dp[i] + n - 2 * son[to]
            dp(to, i)
        }
    }

    dfs(0, -1, 0)
    dp(0, -1)
    return dp
}
```

树上背包：

---

## 计算几何

> 利用计算机建立数学模型解决几何问题。
> 

### 距离

[OI - 距离](https://oi-wiki.org/geometry/distance/#%E6%AC%A7%E6%B0%8F%E8%B7%9D%E7%A6%BB)

> 欧几里得距离：在二维空间内，两个点之间的欧几里得距离为两点的直线距离：$\sqrt{(x_2-x_1)^2 + (y_2-y_1)^2}$
> 

> 曼哈顿距离：在二维空间内，两个点之间的曼哈顿距离（Manhattan distance）为它们横坐标之差的绝对值与纵坐标之差的绝对值之和：$|x_2-x_1| + |y_2-y_1|$
> 

![](https://github.com/pengxurui/Singularity/assets/25008934/e08ea4cf-b46b-4e38-a52e-37744d21b242)

> 切比雪夫距离：在二维空间内，两个点之间的切比雪夫距离为它们横坐标之差的绝对值与纵坐标之差的绝对值的最大值：$max(|x_2-x_1|, |y_2-y_1|)$


![](https://github.com/pengxurui/Singularity/assets/25008934/94371a25-9742-4652-9186-e579500597ff)

> 汉明距离：汉明距离是两个字符串之间的距离，它表示两个长度相同的字符串对应位字符不同的数量。


### 扫描线

[OI - 扫描线](https://oi-wiki.org/geometry/scanning/)

> 扫描线一般运用在图形上面，就是一条线在整个图上扫来扫去，用来解决图形面积，周长，以及二维数点等问题，本质上是采用合并贡献的思想将多个图形对结果的贡献合并计算。
> 


---

## 图论

### 拓扑排序

[OI - 拓扑排序](https://oi-wiki.org/graph/topo)

Khan 算法：

```kotlin
// 入度表
val inDegrees = IntArray(n)
// 邻接表
val graph = HashMap<Int, MutableList<Int>>()
// 队列
val queue = LinkedList<Int>()
// 计数
var path = LinkedList<Int>()
for (p in prerequisites) {
    graph.getOrPut(p[1]) { LinkedList<Int>() }.add(p[0])
    inDegrees[p[0]]++
}
// 将入度为 0 的节点入队
for (index in inDegrees.indices) {
    if (inDegrees[index] == 0) queue.offer(index)
}
while (!queue.isEmpty()) {
    // 删除入度为 0 的节点
    val node = queue.poll()
    // 修改相邻节点
    for (edge in graph[node] ?: Collections.emptyList()) {
        if (--inDegrees[edge] == 0) queue.offer(edge)
    }
    path.add(node)
}
return if (path.size == n) path.toIntArray() else IntArray(0)
```

DFS 写法：

```kotlin
// 领接表
val graph = HashMap<Int, LinkedList<Int>>().apply {
    for (p in prerequisites) {
        this.getOrPut(p[0]) { LinkedList<Int>() }.add(p[1])
    }
}
// 0：未搜索、1：搜索中、2：已完成
val flags = IntArray(n)
val path = LinkedList<Int>()

fun dfs(index: Int): Boolean {
    // 标记搜索中
    flags[index] = 1
    for (to in graph[index] ?: Collections.emptyList()) {
        // 成环
        if (flags[to] == 1) return false
        if (flags[to] == 2) continue
        // 递归
        if (!dfs(to)) return false
    }
    // 标记已完成
    flags[index] = 2
    path.add(index)
    return true
}

// DFS
for (index in flags.indices) {
    if (0 == flags[index]) {
        if (!dfs(index)) return IntArray(0)
    }
}
return path.toIntArray()
```

求字典序最大/最小的拓扑排序：将 Kahn 算法中的队列替换成最大堆/最小堆实现的优先队列即可。

```kotlin

```

### 最短路 / 最长路

[OI - 最短路](https://oi-wiki.org/graph/shortest-path/)

| 最短路算法 | Floyd | Johnson | Bellman-Ford | Dijkstra |
| --- | --- | --- | --- | --- |
| 最短路类型 | 每对结点之间的最短路 | 每对结点之间的最短路 | 单源最短路 | 单源最短路 |
| 作用于 | 任意图 | 任意图 | 任意图 | 非负权图 |
| 能否检测负环？ | 能 | 能 | 能 | 不能 |
| 时间复杂度 | O(n^3) | O(nmlgm) | O(nm) | O(mlgn)最小堆 |

Floyd：

Johnson：

Bellman-Ford：

Dijkstra：

### 连通性相关

- 强连通：若有向图中的节点两两可达，则称这张图是强连通的；
- 弱连通：若有向图中的边替换为无向图后两两可达，则称这张图为弱连通的；
- 强连通分量：极大的强连通子图；
- 弱连通分量：极大的弱连通子图；
- 内向基环树：若有向弱连通图中每个点的出度都是 1，则它是一棵内向基环树；
- 外向基环树：若有向弱连通图中每个点的入度都是 1，则它是一棵外向基环树；
- 双连通分量：
- 割点：
- 桥：

<img width="567" alt="Untitled 6" src="https://github.com/pengxurui/Singularity/assets/25008934/0e228e4b-f7de-443e-aebd-92dddc49705d">

### 最大环（基环树找最大环）

[OI - 强连通分量](https://oi-wiki.org/graph/scc/)

> 算法：先拓扑排序得到所有环，再 BFS / DFS 找出最大环，时间复杂度为 $O(n)$
> 

```kotlin
// 拓扑排序
var queue = LinkedList<Int>()
var cnt = 0
for (i in 0 until n) {
    if (indegree[i] == 0) queue.offer(i)
}
while(!queue.isEmpty()) {
    val temp = LinkedList<Int>()
    for (node in queue) {
        cnt++
        for (to in graph[node]) {
            if (0 == -- indegree[to]) temp.offer(to)
        }
    }
    queue = temp
}
if (n == cnt) return -1
// DFS
val visit = BooleanArray(n)
fun dfs(i: Int): Int {
    if (visit[i]) return 0
    if (indegree[i] == 0) return 0
    visit[i] = true
    return 1 + dfs(edges[i])
}
return (0 ..< n).maxOf { dfs(it) } 
```

> 时间戳：记录访问每个点的时间 time，对图走 BFS，如果遇到一个已经访问过的点，分类讨论：访问时间 < 当前轮次的起始时间，说明该点是在之前轮中访问；否则说明找到新的环，两次访问的时间差就是环的大小，时间复杂度为 $O(n)$
> 

```kotlin
val n = edges.size
var time = 0
val times = IntArray(n)
var ret = -1
for (i in 0 until n) {
    if (times[i] != 0) continue
    var x = i
    val startTime = time
    while (x != -1) {
        if (times[x] != 0) {
            if (times[x] >= startTime) ret = max(ret, time - times[x]) // 找到新的环
            break
        }
        times[x] = time++
        x = edges[x]
    }
}
return ret
```

Tarjan 算法求强连通分量：

![](https://github.com/pengxurui/Singularity/assets/25008934/a250df97-e431-4d1b-88e2-eaca929fc088)

### 最小环（基环树找最小环）

### 网络流

> 2023/11/26 更新
