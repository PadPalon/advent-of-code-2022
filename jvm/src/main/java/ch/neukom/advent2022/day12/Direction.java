package ch.neukom.advent2022.day12;

import java.util.function.UnaryOperator;

public enum Direction {
    NORTH(coordinate -> new GridCoordinate(coordinate.x(), coordinate.y() - 1)),
    EAST(coordinate -> new GridCoordinate(coordinate.x() + 1, coordinate.y())),
    SOUTH(coordinate -> new GridCoordinate(coordinate.x(), coordinate.y() + 1)),
    WEST(coordinate -> new GridCoordinate(coordinate.x() - 1, coordinate.y()));

    private final UnaryOperator<GridCoordinate> moveFunction;

    Direction(UnaryOperator<GridCoordinate> moveFunction) {
        this.moveFunction = moveFunction;
    }

    public UnaryOperator<GridCoordinate> getMoveFunction() {
        return moveFunction;
    }
}
