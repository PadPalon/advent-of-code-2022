package ch.neukom.advent2022.day3;

import java.io.IOException;

import ch.neukom.advent2022.util.InputResourceReader;

public class Part1 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        long sumOfCommonItemTypes = reader.readInput()
            .map(line -> (left:line.substring(0, line.length() / 2), right:line.substring((line.length() / 2))))
            .flatMapToInt(tuple -> Util.findCommonChars(tuple.left, tuple.right))
            .map(Util::getPriorityForItemType)
            .sum();
        System.out.println("The sum of priorities of common item types is $sumOfCommonItemTypes");
    }
}
