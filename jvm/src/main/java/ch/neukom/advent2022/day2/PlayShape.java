package ch.neukom.advent2022.day2;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PlayShape {
    ROCK(1), PAPER(2), SCISSORS(3);

    private final int points;

    PlayShape(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public boolean winsAgainst(PlayShape other) {
        int otherPoints = other.getPoints();
        int thisPoints = this.getPoints();
        return (thisPoints - 1 == otherPoints % PlayShape.values().length);
    }

    public PlayShape getWeakness() {
        return getCounterPart(winningPredicate().or(this::equals).negate());
    }

    public PlayShape getStrength() {
        return getCounterPart(winningPredicate());
    }

    private Predicate<PlayShape> winningPredicate() {
        return this::winsAgainst;
    }

    private PlayShape getCounterPart(Predicate<PlayShape> predicate) {
        return Arrays.stream(PlayShape.values()).filter(predicate).findAny().orElseThrow();
    }
}
