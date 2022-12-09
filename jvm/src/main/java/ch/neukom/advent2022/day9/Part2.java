package ch.neukom.advent2022.day9;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import ch.neukom.advent2022.util.InputResourceReader;
import ch.neukom.advent2022.util.Repeater;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Part2 {
    private static final int KNOT_COUNT = 10;

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Set<Coordinate> visitedTailCoordinates = Sets.newHashSet(new Coordinate(0, 0));

        List<Coordinate> rope = Lists.newArrayListWithCapacity(KNOT_COUNT);
        Repeater.repeat(KNOT_COUNT, () -> rope.add(new Coordinate(0, 0)));

        List<Direction> moves = Util.parseMoves(reader.readInput());

        for (Direction directionToMove : moves) {
            moveHead(rope, directionToMove);
            pullRope(rope);
            visitedTailCoordinates.add(rope.last());
        }

        System.out.println("Tail visisted ${visitedTailCoordinates.size()} positions");
    }

    private static void moveHead(List<Coordinate> rope, Direction directionToMove) {
        Coordinate lastMoved = rope.get(0).move(directionToMove);
        rope.set(0, lastMoved);
    }

    private static void pullRope(List<Coordinate> rope) {
        Coordinate lastMoved = rope.get(0);
        int indexToMove = 1;
        while (indexToMove < rope.size()) {
            Coordinate toMove = rope.get(indexToMove);
            int distance = toMove.distance(lastMoved);
            if (distance > 1) {
                lastMoved = toMove.moveTowards(lastMoved);
                rope.set(indexToMove, lastMoved);
            } else {
                // no reason to propagate further down the rope if an in-between knot didn't move
                break;
            }
            indexToMove++;
        }
    }
}
