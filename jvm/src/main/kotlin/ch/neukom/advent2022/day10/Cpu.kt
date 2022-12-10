package ch.neukom.advent2022.day10

class Cpu(private val registerHistory: List<Long> = listOf(1)) {
    fun increaseCycle(): Cpu {
        val (lastRegister) = registerHistory
        return Cpu(buildList {
            add(lastRegister)
            addAll(registerHistory)
        })
    }

    fun addToRegister(number: Long): Cpu {
        val (lastRegister) = registerHistory
        return Cpu(buildList {
            add(number + lastRegister)
            addAll(registerHistory)
        })
    }

    fun getRegisterHistory(): List<Long> {
        return registerHistory.reversed()
    }
}
