package ch.neukom.advent2022.day10

import ch.neukom.advent2022.util.InputResourceReader

object Part1

fun main() {
    InputResourceReader(Part1.javaClass).use(::run)
}

private fun run(reader: InputResourceReader) {
    val finalState = Sequence { reader.readInput().iterator() }
        .map { Instruction.parse(it) }
        .fold(Cpu()) { cpu, instruction -> instruction.execute(cpu) }
    val sumOfSignalStrengths = finalState.getRegisterHistory()
        .mapIndexed { index, register -> (index + 1) * register }
        .filterIndexed { index, _ -> (index + 1 - 20) % 40 == 0 }
        .sum()
    println("The sum of the signal strengths is $sumOfSignalStrengths")
}
