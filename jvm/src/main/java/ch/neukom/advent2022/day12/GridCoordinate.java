package ch.neukom.advent2022.day12;

public record GridCoordinate(int x, int y) {
    public boolean valid(int width, int height) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public int distance(GridCoordinate other) {
        return Math.abs(x() - other.x()) + Math.abs(y() - other.y());
    }
}
