package ch.neukom.advent2022.day13;

import java.io.IOException;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.base.Strings;

import static java.util.stream.Collectors.*;

public class Part1 {
    private static final int COMPARISON_SIZE = 2;

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Long sumOfValidPairIndexes = reader.readInput()
            .filter(line -> !Strings.isNullOrEmpty(line))
            .map(Util::parsePart)
            .mapWithIndex((part, index) -> (part, group:index / COMPARISON_SIZE))
            .collect(groupingBy(tuple -> tuple.group, mapping(tuple -> tuple.part, toList())))
            .values()
            .stream()
            .filter(pair -> pair.size() == COMPARISON_SIZE)
            .mapWithIndex((pair, index) -> (index:index + 1, left:pair.get(0), right:pair.get(1)))
            .filter(tuple -> tuple.left.compareTo(tuple.right) < 0)
            .mapToLong(tuple -> tuple.index)
            .sum();
        System.out.println("The sum of the indexes of the pairs in the right order is $sumOfValidPairIndexes");
    }
}
