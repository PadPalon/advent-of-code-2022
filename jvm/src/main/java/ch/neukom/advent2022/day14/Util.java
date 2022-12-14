package ch.neukom.advent2022.day14;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import manifold.ext.rt.api.auto;

public class Util {
    private static final Splitter PATH_PART_SPLITTER = Splitter.on(" -> ").trimResults().omitEmptyStrings();
    private static final boolean DRAW_CAVE = true;

    private Util() {
    }

    public static Map<GridCoordinate, TileType> parseCave(Stream<String> input) {
        Map<GridCoordinate, TileType> cave = Maps.newHashMap();
        input.forEach(line -> PATH_PART_SPLITTER.splitToStream(line)
            .map(part -> part.split(","))
            .map(coordinates -> new GridCoordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])))
            .reduce((start, end) -> {
                cave.put(start, TileType.ROCK);
                cave.put(end, TileType.ROCK);
                UnaryOperator<GridCoordinate> move;
                if (start.x() != end.x()) {
                    int direction = (int) Math.signum(end.x() - start.x());
                    move = original -> new GridCoordinate(original.x() + direction, original.y());
                } else if (start.y() != end.y()) {
                    int direction = (int) Math.signum(end.y() - start.y());
                    move = original -> new GridCoordinate(original.x(), original.y() + direction);
                } else {
                    move = original -> original;
                }
                GridCoordinate current = move.apply(start);
                while (!current.equals(end)) {
                    cave.put(current, TileType.ROCK);
                    current = move.apply(current);
                }
                return end;
            })
        );
        cave.put(getSourceCoordinate(), TileType.SOURCE);

        return cave;
    }

    public static auto getCaveStats(Map<GridCoordinate, TileType> cave) {
        return getCaveStats(cave, 0);
    }

    public static auto getCaveStats(Map<GridCoordinate, TileType> cave, int increasedHeight) {
        int minX = cave.keySet().stream().mapToInt(GridCoordinate::x).min().orElseThrow();
        int maxX = cave.keySet().stream().mapToInt(GridCoordinate::x).max().orElseThrow();
        int width = maxX - minX;
        int minY = cave.keySet().stream().mapToInt(GridCoordinate::y).min().orElseThrow();
        int maxY = cave.keySet().stream().mapToInt(GridCoordinate::y).max().orElseThrow();
        int height = maxY - minY + increasedHeight;
        return (width, height, minX, maxX, minY, maxY);
    }

    public static GridCoordinate getSourceCoordinate() {
        return new GridCoordinate(500, 0);
    }

    public static CaveDisplay getCaveDisplay(Map<GridCoordinate, TileType> cave, int drawSpeed) {
        if (DRAW_CAVE) {
            auto caveStats = getCaveStats(cave);
            return new DrawingCaveDisplay(caveStats.width, caveStats.height, caveStats.minX, caveStats.maxX, caveStats.minY, caveStats.maxY, cave, drawSpeed);
        } else {
            return new FakeCaveDisplay();
        }
    }

    public static CaveDisplay getCaveDisplay(Map<GridCoordinate, TileType> cave, int drawSpeed, int width, int height) {
        if (DRAW_CAVE) {
            auto caveStats = getCaveStats(cave);
            return new DrawingCaveDisplay(width, height, caveStats.minX - width / 2, caveStats.maxX, caveStats.minY, caveStats.maxY, cave, drawSpeed);
        } else {
            return new FakeCaveDisplay();
        }
    }

    public static void updateTile(GridCoordinate lastCoordinate,
                                  GridCoordinate newCoordinate,
                                  Map<GridCoordinate, TileType> cave,
                                  CaveDisplay caveDisplay) {
        if (lastCoordinate.equals(getSourceCoordinate())) {
            cave.set(lastCoordinate, TileType.SOURCE);
            caveDisplay.handleChange(lastCoordinate, TileType.SOURCE);
        } else {
            cave.set(lastCoordinate, TileType.VOID);
            caveDisplay.handleChange(lastCoordinate, TileType.VOID);
        }
        cave.set(newCoordinate, TileType.SAND);
        caveDisplay.handleChange(newCoordinate, TileType.SAND);
    }

    public static GridCoordinate calculateNewCoordinate(GridCoordinate source, Predicate<GridCoordinate> validSandTilePredicate) {
        return Stream.of(
                source.down(),
                source.down().left(),
                source.down().right()
            )
            .filter(validSandTilePredicate)
            .findFirst()
            .orElse(source);
    }
}
