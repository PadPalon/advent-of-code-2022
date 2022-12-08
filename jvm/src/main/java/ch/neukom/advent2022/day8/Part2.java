package ch.neukom.advent2022.day8;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.collect.Maps;
import kotlin.math.MathKt;

public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        int height = (int) reader.getLineCount();
        int width = reader.getFirstLine().length();

        int[][] grid = Util.loadGrid(reader.readInput(), width, height);

        Map<GridCoordinate, Long> coordinateScores = getCoordinateScores(width, height, grid);
        long bestScenicScore = coordinateScores.entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElseThrow();
        System.out.println("The best possible score is $bestScenicScore");
    }

    private static Map<GridCoordinate, Long> getCoordinateScores(int width, int height, int[][] grid) {
        Map<GridCoordinate, Long> coordinateScores = Maps.newHashMap();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GridCoordinate coordinate = new GridCoordinate(x, y);
                long[] scores = Arrays.stream(Direction.values())
                        .mapToLong(direction -> getViewDistance(grid, width, height, coordinate, direction))
                        .toArray();
                long score = LongStream.of(scores)
                        .reduce(1, Math::multiplyExact);
                coordinateScores.put(coordinate, score);
            }
        }
        return coordinateScores;
    }

    private static long getViewDistance(int[][] grid, int width, int height, GridCoordinate coordinate, Direction direction) {
        int sourceHeight = grid[coordinate.x()][coordinate.y()];
        int[] trees = Stream.iterate(
                        direction.getMoveFunction().apply(coordinate),
                        generatedCoordinate -> generatedCoordinate.isValid(width, height),
                        direction.getMoveFunction()
                )
                .mapToInt(c -> grid[c.x()][c.y()])
                .toArray();
        if (trees.isEmpty()) {
            return 0;
        } else {
            // if there are ANY trees in that direction, you will see at least one, but at most the total amount of trees
            return Math.min(IntStream.of(trees).takeWhile(tree -> tree < sourceHeight).count() + 1, trees.length);
        }
    }
}
