/**
 * Reads lines from the given input txt file.
 */
expect fun readInput(name: String): List<String>

/**
 * Converts string to md5 hash.
 */
expect fun String.md5(): String

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun CharSequence.splitAsCharSequence(delimiter: Char): Sequence<CharSequence> {
    return sequence {
        var start = 0
        var indexOfDelimiter: Int = -1
        while (indexOfDelimiter != length) {
            indexOfDelimiter = indexOf(delimiter, startIndex = start)
            if (indexOfDelimiter == -1) {
                indexOfDelimiter = length
            }
            yield(start until indexOfDelimiter)
            start = indexOfDelimiter + 1
        }
    }.map { subSequence(it) }
}