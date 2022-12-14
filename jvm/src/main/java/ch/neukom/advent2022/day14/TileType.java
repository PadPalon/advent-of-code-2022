package ch.neukom.advent2022.day14;

import java.awt.Color;

public enum TileType {
    ROCK(Color.GRAY),
    SAND(Color.PINK),
    SOURCE(Color.GREEN),
    VOID(Color.BLACK);

    private final Color color;

    TileType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
