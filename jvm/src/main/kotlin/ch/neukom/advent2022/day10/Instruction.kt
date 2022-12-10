package ch.neukom.advent2022.day10

interface Instruction {
    fun execute(cpu: Cpu): Cpu

    companion object {
        fun parse(line: String): Instruction {
            return if (line == "noop") {
                NoopInstruction()
            } else if (line.startsWith("addx")) {
                AddInstruction(line.splitToSequence(" ").last().toLong())
            } else {
                throw Exception("unknown instruction")
            }
        }
    }
}

class NoopInstruction : Instruction {
    override fun execute(cpu: Cpu): Cpu {
        return cpu.increaseCycle()
    }

}

class AddInstruction(private val number: Long) : Instruction {
    override fun execute(cpu: Cpu): Cpu {
        return cpu.increaseCycle().addToRegister(number)
    }
}
