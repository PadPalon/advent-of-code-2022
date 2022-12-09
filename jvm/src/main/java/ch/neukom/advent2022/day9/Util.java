package ch.neukom.advent2022.day9;

import java.util.List;
import java.util.stream.Stream;

import static ch.neukom.advent2022.util.Repeater.*;

public class Util {
    private Util() {
    }


    static List<Direction> parseMoves(Stream<String> input) {
        return input
            .map(line -> line.split(" "))
            .map(parts -> (direction:Direction.parse(parts[0]), count:Integer.parseInt(parts[1])))
            .<Direction>mapMulti((tuple, consumer) -> {
                repeat(tuple.count, () -> consumer.accept(tuple.direction));
            })
            .toList();
    }
}
