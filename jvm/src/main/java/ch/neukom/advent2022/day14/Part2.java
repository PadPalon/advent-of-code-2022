package ch.neukom.advent2022.day14;

import java.io.IOException;
import java.util.Map;

import ch.neukom.advent2022.util.InputResourceReader;
import manifold.ext.rt.api.auto;

public class Part2 {
    private static final int DRAW_SPEED = 1000;

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Map<GridCoordinate, TileType> cave = Util.parseCave(reader.readInput());
        auto caveStats = Util.getCaveStats(cave, 2);
        CaveDisplay caveDisplay = Util.getCaveDisplay(cave, DRAW_SPEED, caveStats.width + 300, caveStats.height + 2);

        int restingUnitsOfSand = 0;
        while (isEmpty(Util.getSourceCoordinate(), cave, caveStats.height)) {
            cave.set(Util.getSourceCoordinate(), TileType.SAND);

            GridCoordinate lastCoordinate = Util.getSourceCoordinate();
            boolean isMoving = true;
            while (isMoving) {
                GridCoordinate newCoordinate = Util.calculateNewCoordinate(
                    lastCoordinate,
                    coordinate -> isEmpty(coordinate, cave, caveStats.height)
                );
                if (newCoordinate.equals(lastCoordinate)) {
                    isMoving = false;
                    restingUnitsOfSand++;
                }
                Util.updateTile(lastCoordinate, newCoordinate, cave, caveDisplay);
                lastCoordinate = newCoordinate;
            }
        }
        caveDisplay.update();

        System.out.println("There are $restingUnitsOfSand resting units of sand on the rocks");
    }

    private static boolean isEmpty(GridCoordinate coordinate, Map<GridCoordinate, TileType> cave, int height) {
        if (coordinate.y() == height) {
            return false;
        } else {
            TileType type = cave.getOrDefault(coordinate, TileType.VOID);
            return type.equals(TileType.VOID) || type.equals(TileType.SOURCE);
        }
    }
}
