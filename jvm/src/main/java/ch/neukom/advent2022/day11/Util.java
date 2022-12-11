package ch.neukom.advent2022.day11;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Strings;

public class Util {

    public static final int BLOCK_SIZE = 6;

    private Util() {
    }

    public static Map<Long, Monkey> parseMonkeys(Stream<String> input, Function<Long, Long> postOperation) {
        return findMonkeyBlocks(input)
            .map(block -> Monkey.Parser.parseMonkey(block, postOperation))
            .toMap(Monkey::getMonkeyNumber, monkey -> monkey);
    }

    public static Stream<List<String>> findMonkeyBlocks(Stream<String> input) {
        return input.filter(string -> !Strings.isNullOrEmpty(string))
            .mapWithIndex((line, index) -> (line, group:index / BLOCK_SIZE))
            .collect(Collectors.groupingBy(tuple -> tuple.group))
            .values()
            .stream()
            .map(list -> list.stream().map(tuple -> tuple.line).map(String::trim).toList())
            .filter(block -> block.size() == BLOCK_SIZE);
    }

    public static void runRound(Map<Long, Monkey> monkeys) {
        monkeys.entrySet()
            .stream()
            .sorted(Comparator.comparingLong(Map.Entry::getKey))
            .map(Map.Entry::getValue)
            .forEach(monkey -> monkey.turn().forEach(action -> monkeys.get(action.targetMonkey()).addItem(action.item())));
    }

    public static Long calculateMonkeyBusiness(Map<Long, Monkey> monkeys) {
        return monkeys.values()
            .stream()
            .map(Monkey::getInspectionCount)
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(1L, (accumulator, current) -> accumulator * current);
    }
}
