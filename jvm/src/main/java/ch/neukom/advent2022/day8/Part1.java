package ch.neukom.advent2022.day8;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.collect.Sets;

public class Part1 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        int height = (int) reader.getLineCount();
        int width = reader.getFirstLine().length();

        int[][] grid = Util.loadGrid(reader.readInput(), width, height);

        int visibleTreeCount = getVisibleCoordinates(width, height, grid).size();
        System.out.println("There are $visibleTreeCount visible trees");
    }

    private static Set<GridCoordinate> getVisibleCoordinates(int width, int height, int[][] grid) {
        Set<GridCoordinate> visibleCoordinates = Sets.newHashSet();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GridCoordinate coordinate = new GridCoordinate(x, y);
                boolean treeVisible = Direction.values()
                    .stream()
                    .anyMatch(direction -> isTreeVisible(grid, width, height, coordinate, direction));
                if (treeVisible) {
                    visibleCoordinates.add(coordinate);
                }
            }
        }
        return visibleCoordinates;
    }

    private static boolean isTreeVisible(int[][] grid, int width, int height, GridCoordinate coordinate, Direction direction) {
        int sourceHeight = grid[coordinate.x()][coordinate.y()];
        return Stream.iterate(
                direction.getMoveFunction().apply(coordinate),
                generatedCoordinate -> generatedCoordinate.isValid(width, height),
                direction.getMoveFunction()
            )
            .mapToInt(c -> grid[c.x()][c.y()])
            .allMatch(tree -> tree < sourceHeight);
    }
}
