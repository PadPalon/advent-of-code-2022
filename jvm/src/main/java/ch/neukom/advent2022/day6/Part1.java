package ch.neukom.advent2022.day6;

import java.io.IOException;

import ch.neukom.advent2022.util.InputResourceReader;

import static java.util.stream.Collectors.*;

public class Part1 {
    public static final int START_MARKER_LENGTH = 4;

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        String input = reader.readInput().collect(joining("\n"));
        int start = Util.findStart(input, START_MARKER_LENGTH);
        System.out.println("The $START_MARKER_LENGTH-character marker starts at $start");
    }
}
