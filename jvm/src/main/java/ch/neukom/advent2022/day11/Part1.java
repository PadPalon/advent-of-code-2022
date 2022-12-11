package ch.neukom.advent2022.day11;

import java.io.IOException;
import java.util.Map;

import ch.neukom.advent2022.util.InputResourceReader;
import ch.neukom.advent2022.util.Repeater;

public class Part1 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Map<Long, Monkey> monkeys = Util.parseMonkeys(reader.readInput(), value -> value / 3);
        Repeater.repeat(20, () -> Util.runRound(monkeys));
        Long monkeyBusiness = Util.calculateMonkeyBusiness(monkeys);
        System.out.println("The monkey business is $monkeyBusiness");
    }
}
