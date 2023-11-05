
```
// 2023.02.27 at 14:45:13 GMT
import java.io.*
import java.util.*
import kotlin.math.*
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis

// 1. Modded
const val p = 1000000007L
const val pI = p.toInt()
fun Int.adjust(): Int {
    if (this >= pI) {
        return this - pI
    } else if (this < 0) {
        return this + pI
    };return this
}

fun Int.snap(): Int {
    return if (this >= pI) {
        this - pI
    } else this
}

infix fun Int.mm(b: Int): Int {
    return ((this.toLong() * b) % pI).toInt()
}

infix fun Int.mp(b: Int): Int {
    val ans = this + b;return if (ans >= pI) ans - pI else ans
}

infix fun Int.ms(b: Int): Int {
    val ans = this - b;return if (ans < 0) ans + pI else ans
}

fun Int.inverse(): Int = intPow(this, pI - 2, pI)
infix fun Int.modDivide(b: Int): Int {
    return this mm (b.inverse())
}

fun intPow(x: Int, e: Int, m: Int): Int {
    var xx = x
    var ee = e
    var y = 1
    while (ee > 0) {
        if (ee and 1 == 0) {
            xx = ((1L * xx * xx) % m).toInt()
            ee = ee shr 1
        } else {
            y = ((1L * xx * y) % m).toInt()
            ee -= 1
        }
    }
    return y
}

// 2. DP initial values
const val plarge = 1_000_000_727
const val nLarge = -plarge
const val pHuge = 2_727_000_000_000_000_000L
const val nHuge = -pHuge

// 3. convenience conversions
val Boolean.chi: Int get() = if (this) 1 else 0 //characteristic function
val BooleanArray.array: IntArray get() = IntArray(this.size) { this[it].chi }
val Char.code: Int get() = this.code - 'a'.code

//3. hard to write stuff
fun IntArray.put(i: Int, v: Int) {
    this[i] = (this[i] + v).adjust()
}

val mint get() = mutableListOf<Int>()
val among get() = mutableListOf<Long>()
val mChar: MutableList<Char> get() = mutableListOf()

//4. more outputs
fun List<Char>.concat(): String = this.joinToString("")
val CharArray.concat: String get() = this.joinToString("")
val IntArray.concat: String get() = this.joinToString(" ")

@JvmName("concatInt")
fun List<Int>.concat(): String = this.joinToString(" ")
val LongArray.concat: String get() = this.joinToString(" ")

@JvmName("concatLong")
fun List<Long>.concat(): String = this.joinToString(" ")

//5. Pair of ints
const val long_mask = (1L shl 32) - 1
fun makePair(a: Int, b: Int): Long = (a.toLong() shl 32) xor (long_mask and b.toLong())
val Long.first get() = (this ushr 32).toInt()
val Long.second get() = this.toInt()

//6. strings
val String.size get() = this.length
const val randCount = 100

//7. bits
fun Int.has(i: Int): Boolean = (this and (1 shl i) != 0)
fun Long.has(i: Int): Boolean = (this and (1L shl i) != 0L)

//8 TIME
inline fun time(f: () -> Unit) {
    val t = measureTimeMillis {
        f()
    }
    println("$t ms")
}

//9.ordered pair
fun order(a: Int, b: Int): Pair<Int, Int> {
    return Pair(minOf(a, b), maxOf(a, b))
}

//10 rand
fun rand(x: Int) = Random.nextInt(x)
fun rand(x: IntRange) = Random.nextInt(x)
val coin: Boolean get() = Random.nextBoolean()
//11 typing issues, rename
typealias ints = IntArray
typealias longs = LongArray
typealias pii = Pair<Int, Int>

//11 Base
import java.io.BufferedInputStream
import java.io.File
import java.io.PrintWriter

object Reader {
    private const val BS = 1 shl 16
    private const val NC = 0.toChar()
    private val buf = ByteArray(BS)
    private var bId = 0
    private var size = 0
    private var c = NC
    private var warningActive = true
    var fake = StringBuilder()
    private var IN: BufferedInputStream = BufferedInputStream(System.`in`, BS)
    val OUT: PrintWriter = PrintWriter(System.out)
    private val char: Char
        get() {
            while (bId == size) {
                size = IN.read(buf) // no need for checked exceptions
                if (size == -1) return NC
                bId = 0
            }
            return buf[bId++].toInt().toChar()
        }

    fun nextInt(): Int {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0
        while (c in '0'..'9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }

    fun nextLong(): Long {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0L
        while (c in '0'..'9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }

    fun nextString(): String {
        val ret = StringBuilder()
        while (true) {
            c = char
            if (!isWhitespace(c)) {
                break
            }
        }
        ret.append(c)
        while (true) {
            c = char
            if (isWhitespace(c)) {
                break
            }
            ret.append(c)
        }
        return ret.toString()
    }

    private fun isWhitespace(c: Char): Boolean {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t'
    }

    fun flush() {
        OUT.flush()
    }

    fun takeFile(name: String) {
        IN = BufferedInputStream(File(name).inputStream(), BS)
    }
}

fun output(aa: Any) = Reader.OUT.println(aa)
fun done() = Reader.OUT.close()

fun nextString() = Reader.nextString()
fun nextInt() = Reader.nextInt()
fun nextLong() = Reader.nextLong()
fun nextArrayI(n: Int) = IntArray(n) { nextInt() }
fun nextArrayL(n: Int) = LongArray(n) { nextLong() }
fun nextArrayS(n: Int) = Array<String>(n) { nextString() }

fun main(args: Array<String>) {
    
    // ------------------------------------
    
    done()
}
```

https://paste.nugine.xyz/ekhgf2ny
