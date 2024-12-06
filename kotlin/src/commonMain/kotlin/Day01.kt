import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.math.abs

object Day01 {
    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.toPairOfNumbers() }
            .unzip()
            .let { (first, second) ->
                first.sorted()
                    .zip(second.sorted())
                    .fold(0) { acc, pair -> acc + abs(pair.first - pair.second) }
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.toPairOfNumbers() }
            .unzip()
            .let { (first, second) ->
                val secondMap = mutableMapOf<Int, Int>()
                for (i in second) {
                    val c = secondMap[i] ?: 0
                    secondMap[i] = c + 1
                }
                first.fold(0) { acc, i ->
                    acc + (secondMap[i] ?: 0) * i
                }
            }
    }

    fun main() {
        val input = readInput("input01")
        part1(input).println()
        part2(input).println()
    }
}

private fun String.toPairOfNumbers(): Pair<Int, Int> {
    return split(' ')
        .mapNotNull { it.toIntOrNull() }
        .takeIf { it.size == 2 }
        ?.let { (first, second) -> first to second }
        ?: throw RuntimeException("Line couldn't be converted")
}