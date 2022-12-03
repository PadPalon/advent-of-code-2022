package ch.neukom.advent2022.day3;

import java.util.stream.IntStream;

public class Util {
    private Util() {
    }

    public static IntStream findCommonChars(String left, String right) {
        return left.codePoints().filter(c -> right.contains((char) c)).distinct();
    }

    public static int getPriorityForItemType(int c) {
        if (Character.isLowerCase(c)) {
            return c - 96;
        } else {
            return c - 38;
        }
    }
}
