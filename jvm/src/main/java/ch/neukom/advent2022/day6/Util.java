package ch.neukom.advent2022.day6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;

public class Util {
    private Util() {}

    public static int findStart(String input, int startMarkerLength) {
        int[] characters = input.codePoints().toArray();
        Deque<Integer> buffer = new ArrayDeque<>(startMarkerLength);
        int counter = 1;
        for (int character : characters) {
            if (buffer.size() >= startMarkerLength) {
                buffer.pollLast();
            }
            buffer.push(character);
            if (buffer.size() >= startMarkerLength) {
                boolean allCharactersUnique = true;
                for (Integer knownCharacter : buffer) {
                    Predicate<Integer> predicate = i -> i.equals(knownCharacter);
                    allCharactersUnique &= buffer.indexOfFirst(predicate) == buffer.indexOfLast(predicate);
                }
                if (allCharactersUnique) {
                    return counter;
                }
            }
            counter++;
        }
        return -1;
    }
}
