package ch.neukom.advent2022.day8;

public record GridCoordinate(int x, int y) {
    public boolean isValid(int width, int height) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }
}
