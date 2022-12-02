package ch.neukom.advent2022.day2;

import java.io.IOException;

import ch.neukom.advent2022.util.InputResourceReader;

public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        int totalScore = reader.readInput()
            .map(line -> line.split(" "))
            .filter(parts -> parts.length == 2)
            .map(parts -> new Game(parts[0], parts[1]))
            .mapToInt(Game::calculatePoints)
            .sum();
        System.out.println("Our total score would be ${totalScore}");
    }

    private static class Game {
        private final PlayShape opponentShape;
        private final PlayShape playerShape;

        public Game(String opponent, String player) {
            this.opponentShape = parseShape(opponent);
            this.playerShape = findShapeForOutcome(player, this.opponentShape);
        }

        public int calculatePoints() {
            return playerShape.getPoints() + calculateOutcome();
        }

        private int calculateOutcome() {
            if (playerShape == opponentShape) {
                return 3;
            } else if (playerShape.winsAgainst(opponentShape)) {
                return 6;
            } else {
                return 0;
            }
        }

        private PlayShape parseShape(String shape) {
            return switch (shape) {
                case "A" -> PlayShape.ROCK;
                case "B" -> PlayShape.PAPER;
                case "C" -> PlayShape.SCISSORS;
                default -> throw new IllegalArgumentException("Unexpected symbol");
            };
        }

        private PlayShape findShapeForOutcome(String shape, PlayShape opponentShape) {
            return switch (shape) {
                case "X" -> opponentShape.getStrength();
                case "Y" -> opponentShape;
                case "Z" -> opponentShape.getWeakness();
                default -> throw new IllegalArgumentException("Unexpected symbol");
            };
        }
    }
}
