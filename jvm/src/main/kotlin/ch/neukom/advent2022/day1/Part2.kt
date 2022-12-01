package ch.neukom.advent2022.day1

import ch.neukom.advent2022.util.InputResourceReader
import java.util.stream.Collectors

object Part2

fun main() {
    InputResourceReader(Part2.javaClass).use(::run)
}

private fun run(reader: InputResourceReader) {
    val input = reader.readInput().collect(Collectors.joining("\n"))
    val sumOfThreeMostCarried = input.splitToSequence("\n\n")
        .map { parseNumbers(it) }
        .map { it.sum() }
        .sortedDescending()
        .take(3)
        .sum()
    println("The sum of the three highest amounts of calories carried is $sumOfThreeMostCarried")
}

private fun parseNumbers(it: String) = it.splitToSequence("\n").map { it.toLong() }
