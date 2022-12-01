package ch.neukom.advent2022.day1

import ch.neukom.advent2022.util.InputResourceReader
import java.util.stream.Collectors

object Part1

fun main() {
    InputResourceReader(Part1.javaClass).use(::run)
}

private fun run(reader: InputResourceReader) {
    val input = reader.readInput().collect(Collectors.joining("\n"))
    val highestAmountOfCalories = input.splitToSequence("\n\n")
        .map { parseNumbers(it) }
        .map { it.sum() }
        .max()
    println("The highest amount of calories carried is $highestAmountOfCalories")
}

private fun parseNumbers(it: String) = it.splitToSequence("\n").map { it.toLong() }
