package ch.neukom.advent2022.day9;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.collect.Sets;

public class Part1 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Coordinate headCoordinate = new Coordinate(0, 0);
        Coordinate tailCoordinate = new Coordinate(0, 0);
        Set<Coordinate> visitedTailCoordinates = Sets.newHashSet(tailCoordinate);

        List<Direction> moves = Util.parseMoves(reader.readInput());

        for (Direction directionToMove : moves) {
            headCoordinate = headCoordinate.move(directionToMove);
            int distance = headCoordinate.distance(tailCoordinate);
            if (distance > 1) {
                tailCoordinate = tailCoordinate.moveTowards(headCoordinate);
            }
            visitedTailCoordinates.add(tailCoordinate);
        }

        System.out.println("Tail visisted ${visitedTailCoordinates.size()} positions");
    }
}
