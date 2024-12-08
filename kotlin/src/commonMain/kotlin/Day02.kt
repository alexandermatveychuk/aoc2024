import kotlin.math.abs
import kotlin.math.sign

object Day02 {
    fun part1(input: List<String>): Int {
        return input.mapToInts()
            .count { it.isSafe() }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    fun main() {
        val input = readInput("input02")
        sequenceOf(
            listOf(1) to false,
            listOf(1, 1) to false,
            listOf(-1, 0) to true,
            listOf(1, 2, 3, 4, 5) to true,
            listOf(5, 4, 3, 2, 1) to true,
            listOf(1, 3, 5, 7, 9) to true,
            listOf(1, 4, 6, 8, 10) to true,
            listOf(1, 5, 7, 9, 11) to false,
            listOf(1, 2, 3, 3, 4) to false,
            listOf(-5, -3, -2, -5) to false,
            listOf(1, 2, 3, 2, 5) to false,
            listOf(1, 2, 3, 5, 1) to false,
        ).forEach { (ints, r) ->
            println("$ints: ${ints.isSafe()} - $r")
        }
        part1(input).println()
        part2(input).println()
    }
}

private fun List<String>.mapToInts(): Sequence<List<Int>> {
    return asSequence()
        .map { line ->
            line.split(' ')
                .filter { it.isNotEmpty() }
                .map {
                    runCatching {
                        it.toInt()
                    }.getOrElse { exception ->
                        println("Cannot convert to int: $line")
                        println(exception.message)
                        throw exception
                    }
                }
        }
}

private fun List<Int>.isSafe(): Boolean {
    return if (isEmpty()) {
        false
    } else if (size == 1) {
        false
    } else {
        var index = 0
        var current = this[index++]
        var next = this[index++]
        var diff = next - current
        val compareGroup = diff.sign
        if (diff == 0 || abs(diff) > 3) {
            false
        } else {
            while (index < size) {
                current = next
                next = this[index++]
                diff = next - current
                val compare = diff.sign
                if (compare != compareGroup || abs(diff) > 3) {
                    return false
                }
            }
            true
        }
    }
}
