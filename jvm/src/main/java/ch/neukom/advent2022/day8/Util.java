package ch.neukom.advent2022.day8;

import java.util.stream.Stream;

public class Util {
    private Util() {
    }

    public static int[][] loadGrid(Stream<String> input, int width, int height) {
        int[][] grid = new int[width][height];
        input.mapWithIndex((line, y) ->
                        line.codePoints()
                                .mapToObj(c -> (char) c)
                                .mapWithIndex((character, x) -> (x, y, character))
                )
                .flatMap(i -> i)
                .forEach(tuple -> grid[(int) tuple.x][(int) tuple.y] = tuple.character);
        return grid;
    }
}
