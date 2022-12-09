package ch.neukom.advent2022.day9;

public record Coordinate(int x, int y) {
    public Coordinate plus(Coordinate other) {
        return new Coordinate(x() + other.x(), y() + other.y());
    }

    public Coordinate move(Direction direction) {
        return this + direction.getCoordinate();
    }

    public int distance(Coordinate other) {
        int xDistance = Math.abs(x() - other.x());
        int yDistance = Math.abs(y() - other.y());
        return Math.max(xDistance, yDistance);
    }

    public Coordinate moveTowards(Coordinate other) {
        return this + new Coordinate(
            getMove(other.x(), x()),
            getMove(other.y(), y())
        );
    }

    private int getMove(int target, int source) {
        if (target == source) {
            return 0;
        } else {
            return target > source ? 1 : -1;
        }
    }
}
