package ch.neukom.advent2022.day10

import ch.neukom.advent2022.util.InputResourceReader

object Part2

fun main() {
    InputResourceReader(Part2.javaClass).use(::run)
}

private fun run(reader: InputResourceReader) {
    val finalState = Sequence { reader.readInput().iterator() }
        .map { Instruction.parse(it) }
        .fold(Cpu()) { cpu, instruction -> instruction.execute(cpu) }
    val screenWidth = 40
    finalState.getRegisterHistory()
        .forEachIndexed { index, register ->
            val pixelColumnToDraw = index % screenWidth
            val symbolToDraw = if (pixelColumnToDraw >= register - 1 && pixelColumnToDraw <= register + 1) '#' else '.'
            if (pixelColumnToDraw == screenWidth - 1) {
                print("$symbolToDraw\n")
            } else {
                print(symbolToDraw)
            }
        }
}
