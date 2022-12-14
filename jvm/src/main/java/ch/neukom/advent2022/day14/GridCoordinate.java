package ch.neukom.advent2022.day14;

public record GridCoordinate(int x, int y) {
    public GridCoordinate down() {
        return new GridCoordinate(x, y + 1);
    }

    public GridCoordinate right() {
        return new GridCoordinate(x + 1, y);
    }

    public GridCoordinate left() {
        return new GridCoordinate(x - 1, y);
    }
}
