package ch.neukom.advent2022.day12;

import java.util.Set;

import com.google.common.collect.Sets;

public class Node {
    private final int height;
    private final GridCoordinate coordinate;
    private final Set<Node> neighbours = Sets.newHashSet();

    public Node(int height, GridCoordinate coordinate) {
        this.height = height;
        this.coordinate = coordinate;
    }

    public boolean connectNeighbour(Node neighbour) {
        if (neighbour.getHeight() <= getHeight() + 1) {
            return neighbours.add(neighbour);
        } else {
            return false;
        }
    }

    public int getHeight() {
        return height;
    }

    public Set<Node> getNeighbours() {
        return neighbours;
    }

    public GridCoordinate getCoordinate() {
        return coordinate;
    }

    public int calculateDistance(Node other) {
        return coordinate.distance(other.getCoordinate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return coordinate.equals(node.coordinate);
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode();
    }
}
