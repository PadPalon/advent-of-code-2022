package ch.neukom.advent2022.day9;

public enum Direction {
    UP(new Coordinate(0, 1)),
    RIGHT(new Coordinate(1, 0)),
    DOWN(new Coordinate(0, -1)),
    LEFT(new Coordinate(-1, 0));

    private final Coordinate coordinate;

    Direction(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public static Direction parse(String direction) {
        return switch (direction) {
            case "U" -> UP;
            case "R" -> RIGHT;
            case "D" -> DOWN;
            case "L" -> LEFT;
            default -> throw new IllegalArgumentException("Unknown direction");
        };
    }
}
