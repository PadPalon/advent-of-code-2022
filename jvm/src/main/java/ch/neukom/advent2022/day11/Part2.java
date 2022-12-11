package ch.neukom.advent2022.day11;

import java.io.IOException;
import java.util.Map;

import ch.neukom.advent2022.util.InputResourceReader;
import ch.neukom.advent2022.util.Repeater;

/**
 * Had to look up the actual math for this part, but the general approach you need to find is that the actual level of
 * worry is irrelevant. Instead, the only relevant information is if the new worry level passes any of the other monkeys
 * tests. A useful realization then is that the puzzle was designed in a way that all tests are a divisions by prime
 * numbers. The math then works out so that multiplying all the divisors together and using that least common
 * multiple to take the modulus of the worry level gives us a number that passes the same tests, but stays small.
 * <p>
 * Apparently in other regions modular arithmetic is taught more, so this was quite unintuitive for me. Do not think
 * that I am especially smart, I had to look up most of the math. Only the implementation is fully my own creation.
 */
public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        long leastCommonMultiple = Util.findMonkeyBlocks(reader.readInput())
            .mapToLong(Monkey.Parser::findDivisor)
            .reduce(1, (accumulator, value) -> accumulator * value);
        Map<Long, Monkey> monkeys = Util.parseMonkeys(reader.readInput(), value -> value % leastCommonMultiple);
        Repeater.repeat(10000, () -> Util.runRound(monkeys));
        Long monkeyBusiness = Util.calculateMonkeyBusiness(monkeys);
        System.out.println("The monkey business is $monkeyBusiness");
    }
}
