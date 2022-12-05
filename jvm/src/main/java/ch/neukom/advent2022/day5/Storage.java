package ch.neukom.advent2022.day5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Storage {
    private static final Splitter SPACE_SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();
    private static final Splitter NAME_SPLITTER = Splitter.fixedLength(4).trimResults();

    private final List<Deque<Character>> piles;

    private final boolean moveMultipleCrates;

    public Storage(int size, boolean moveMultipleCrates) {
        piles = Lists.newArrayList(IntStream.range(0, size).mapToObj(i -> new ArrayDeque<Character>()).toList());
        this.moveMultipleCrates = moveMultipleCrates;
    }

    public static Storage initializeStorage(List<String> initialConfiguration, StorageType type) {
        String pileList = initialConfiguration.get(initialConfiguration.size() - 1);
        Storage storage = new Storage(SPACE_SPLITTER.splitToList(pileList).size(), type.equals(StorageType.CrateMover9001));
        storage.setupStorage(initialConfiguration);
        return storage;
    }

    public void setupStorage(List<String> initialConfiguration) {
        for (int i = initialConfiguration.size() - 2; i >= 0; i--) {
            String line = initialConfiguration.get(i);
            NAME_SPLITTER.splitToStream(line)
                .map(name -> name.length() == 3 ? name.get(1) : null)
                .mapWithIndex((name, index) -> (index, name))
                .filter(tuple -> tuple.name != null)
                .forEach(tuple -> addCrate((int) tuple.index, tuple.name));
        }
    }

    public void addCrate(int index, Character name) {
        piles.get(index).push(name);
    }

    public void executeMove(Move move) {
        if (moveMultipleCrates) {
            moveCrate(move.from(), move.to(), move.count());
        } else {
            IntStream.range(0, move.count()).forEach(i -> moveCrate(move.from(), move.to()));
        }
    }

    public List<Character> getTopCrates() {
        return piles.stream().filter(pile -> !pile.isEmpty()).map(Deque::peek).toList();
    }

    private void moveCrate(int from, int to) {
        Character crate = piles.get(from).pop();
        piles.get(to).push(crate);
    }

    private void moveCrate(int from, int to, int count) {
        Deque<Character> moving = new ArrayDeque<>(count);
        Deque<Character> fromPile = piles.get(from);
        IntStream.range(0, count).mapToObj(i -> fromPile.pop()).forEach(moving::push);
        Deque<Character> toPile = piles.get(to);
        while (!moving.isEmpty()) {
            toPile.push(moving.pop());
        }
    }

    public record Move(int from, int to, int count) {
        private static final Pattern MOVE_PATTERN = Pattern.compile("move ([0-9]*) from ([0-9]*) to ([0-9]*)");

        public static Move parseMove(String line) {
            Matcher matcher = MOVE_PATTERN.matcher(line);
            if (matcher.matches()) {
                return new Move(
                    Integer.parseInt(matcher.group(2)) - 1,
                    Integer.parseInt(matcher.group(3)) - 1,
                    Integer.parseInt(matcher.group(1))
                );
            } else {
                throw new IllegalStateException("Move line did not match pattern");
            }
        }
    }

    public enum StorageType {
        CrateMover9000, CrateMover9001
    }
}
