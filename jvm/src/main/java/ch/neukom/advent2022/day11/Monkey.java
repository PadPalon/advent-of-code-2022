package ch.neukom.advent2022.day11;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Monkey {
    private static final Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

    private final long monkeyNumber;
    private final List<Long> items;
    private final Function<Long, Long> valueTransformation;
    private final UnaryOperator<Long> throwTargetFinder;

    private long inspectionCount = 0;

    public Monkey(long monkeyNumber,
                  List<Long> items,
                  Function<Long, Long> valueTransformation,
                  UnaryOperator<Long> throwTargetFinder) {
        this.monkeyNumber = monkeyNumber;
        this.items = Lists.newArrayList(items);
        this.valueTransformation = valueTransformation;
        this.throwTargetFinder = throwTargetFinder;
    }

    public List<Action> turn() {
        inspectionCount += items.size();
        List<Action> actions = items.stream()
            .map(valueTransformation)
            .map(item -> new Action(throwTargetFinder.apply(item), item))
            .toList();
        items.clear();
        return actions;
    }

    public long getMonkeyNumber() {
        return monkeyNumber;
    }

    public long getInspectionCount() {
        return inspectionCount;
    }

    public void addItem(long item) {
        items.add(item);
    }

    public static class Parser {
        public static Monkey parseMonkey(List<String> block, Function<Long, Long> postOperation) {
            String monkeyLine = block.get(0);
            long monkeyNumber = Long.parseLong(monkeyLine.charAt(monkeyLine.indexOf(':') - 1));

            String startingLine = block.get(1);
            List<Long> startingItems = COMMA_SPLITTER.splitToStream(startingLine.substringAfterLast(':'))
                .map(Long::parseLong)
                .toList();

            Function<Long, Long> valueTransformation = parseOperation(block.get(2)).andThen(postOperation);

            long divisor = findDivisor(block);
            Predicate<Long> test = value -> value % divisor == 0;
            long trueTarget = Long.parseLong(block.get(4).last());
            long falseTarget = Long.parseLong(block.get(5).last());
            UnaryOperator<Long> throwTargetFinder = value -> test.test(value) ? trueTarget : falseTarget;

            return new Monkey(monkeyNumber, startingItems, valueTransformation, throwTargetFinder);
        }

        public static long findDivisor(List<String> block) {
            return Long.parseLong(block.get(3).substringAfterLast(' '));
        }

        private static UnaryOperator<Long> parseOperation(String line) {
            UnaryOperator<Long> argumentSupplier = getArgumentSupplier(line);
            char operation = line.substringBeforeLast(' ').last();
            return switch (operation) {
                case '+' -> value -> value + argumentSupplier.apply(value);
                case '-' -> value -> value - argumentSupplier.apply(value);
                case '*' -> value -> value * argumentSupplier.apply(value);
                case '/' -> value -> value / argumentSupplier.apply(value);
                default -> throw new IllegalArgumentException("unknown operation $operation");
            };
        }

        private static UnaryOperator<Long> getArgumentSupplier(String line) {
            String argumentString = line.substringAfterLast(' ');
            if (argumentString.equals("old")) {
                return value -> value;
            } else {
                long argument = Long.parseLong(argumentString);
                return value -> argument;
            }
        }
    }
}
