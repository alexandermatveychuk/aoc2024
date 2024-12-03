import kotlin.math.abs

object Day01 {
    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { line ->
                line.split(' ')
                    .mapNotNull { it.toIntOrNull() }
                    .takeIf { it.size == 2 }
                    ?.let { (first, second) -> first to second }
                    ?: throw RuntimeException("Line couldn't be converted")
            }
            .unzip()
            .let { (first, second) ->
                first.sorted()
                    .zip(second.sorted())
                    .fold(0) { acc, pair -> acc + abs(pair.first - pair.second) }
            }
    }

    fun main() {
        val input = readInput("input01")
        part1(input).println()
    }
}