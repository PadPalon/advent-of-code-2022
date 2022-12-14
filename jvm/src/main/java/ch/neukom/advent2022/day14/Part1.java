package ch.neukom.advent2022.day14;

import java.io.IOException;
import java.util.Map;

import ch.neukom.advent2022.util.InputResourceReader;
import manifold.ext.rt.api.auto;

public class Part1 {
    private static final int DRAW_SPEED = 10;

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Map<GridCoordinate, TileType> cave = Util.parseCave(reader.readInput());
        auto caveStats = Util.getCaveStats(cave, 2);
        CaveDisplay caveDisplay = Util.getCaveDisplay(cave, DRAW_SPEED);

        int restingUnitsOfSand = 0;
        boolean outOfFrame = false;
        while (!outOfFrame) {
            GridCoordinate lastCoordinate = Util.getSourceCoordinate();
            boolean isMoving = true;
            while (isMoving) {
                GridCoordinate newCoordinate = Util.calculateNewCoordinate(
                    lastCoordinate,
                    coordinate -> isVoid(coordinate, cave)
                );
                if (newCoordinate.equals(lastCoordinate)) {
                    Util.updateTile(lastCoordinate, newCoordinate, cave, caveDisplay);
                    isMoving = false;
                    restingUnitsOfSand++;
                } else if (newCoordinate.y() > caveStats.height) {
                    isMoving = false;
                    outOfFrame = true;
                } else {
                    Util.updateTile(lastCoordinate, newCoordinate, cave, caveDisplay);
                }
                lastCoordinate = newCoordinate;
            }
        }
        caveDisplay.update();

        System.out.println("There are $restingUnitsOfSand resting units of sand on the rocks");
    }

    public static boolean isVoid(GridCoordinate coordinate, Map<GridCoordinate, TileType> cave) {
        return cave.getOrDefault(coordinate, TileType.VOID).equals(TileType.VOID);
    }
}
