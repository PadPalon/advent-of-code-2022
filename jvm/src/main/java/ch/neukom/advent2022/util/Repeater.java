package ch.neukom.advent2022.util;

import java.util.stream.IntStream;

public class Repeater {
    private Repeater() {
    }

    public static void repeat(int amount, Runnable toRepeat) {
        IntStream.range(0, amount).forEach(i -> toRepeat.run());
    }
}
