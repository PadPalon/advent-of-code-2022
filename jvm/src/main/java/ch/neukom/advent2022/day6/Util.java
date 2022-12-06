package ch.neukom.advent2022.day6;

public class Util {
    private Util() {
    }

    public static int findStart(String input, int startMarkerLength) {
        int start = 0;
        int end = start + startMarkerLength;
        int max = input.length();
        do {
            String part = input.substring(start, end);
            if (part.codePoints().distinct().count() == startMarkerLength) {
                return end;
            }
            start++;
            end++;
        } while (end < max);
        return -1;
    }
}
