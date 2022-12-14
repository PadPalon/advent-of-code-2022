package ch.neukom.advent2022.day14;

public interface CaveDisplay {
    void update();

    void handleChange(GridCoordinate lastCoordinate, TileType source);
}
