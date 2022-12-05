package ch.neukom.advent2022.day5;

import java.io.IOException;

import ch.neukom.advent2022.day5.Storage.StorageType;
import ch.neukom.advent2022.util.InputResourceReader;

public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Util.solvePuzzle(reader, StorageType.CrateMover9001);
    }
}
