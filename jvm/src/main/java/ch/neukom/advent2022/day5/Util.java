package ch.neukom.advent2022.day5;

import java.util.List;
import java.util.stream.Collectors;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.base.Splitter;

public class Util {
    private static final Splitter NEW_LINE_SPLITTER = Splitter.on('\n');

    private Util() {
    }

    public static void solvePuzzle(InputResourceReader reader, Storage.StorageType type) {
        String input = reader.readInput().collect(Collectors.joining("\n"));
        String[] parts = input.split("\n\n");
        assert parts.length == 2;
        List<String> initialConfiguration = NEW_LINE_SPLITTER.splitToList(parts[0]);
        Storage storage = Storage.initializeStorage(initialConfiguration, type);
        String moves = parts[1];
        executeMoves(storage, moves);
        String topCrates = storage.getTopCrates().join("");
        System.out.println("The top most crates are $topCrates");
    }

    private static void executeMoves(Storage storage, String moves) {
        NEW_LINE_SPLITTER.splitToStream(moves)
            .map(Storage.Move::parseMove)
            .forEach(storage::executeMove);
    }
}
