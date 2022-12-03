package ch.neukom.advent2022.day3;

import java.io.IOException;
import java.util.List;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.collect.Streams;

public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        int sumOfBadgeTypes = Streams.mapWithIndex(reader.readInput(), (value, index) -> (index, value))
            .groupingBy(tuple -> (int) tuple.index / 3)
            .values()
            .stream()
            .map(list -> list.stream().map(tuple -> tuple.value).toList())
            .map(lines -> findCommonCharsInAll(lines))
            .filter(i -> i.length() == 1)
            .map(i -> i.get(0))
            .mapToInt(Util::getPriorityForItemType)
            .sum();
        System.out.println("The sum of of priorities of the badge types is $sumOfBadgeTypes");
    }

    private static String findCommonCharsInAll(List<String> lines) {
        return lines.reduce(
            (left, right) -> Util.findCommonChars(left, right)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
        );
    }
}
