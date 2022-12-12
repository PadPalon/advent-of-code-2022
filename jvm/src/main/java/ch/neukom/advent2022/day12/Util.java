package ch.neukom.advent2022.day12;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import manifold.ext.rt.api.auto;

public class Util {
    private Util() {
    }

    public static int[][] loadGrid(Stream<String> input, int width, int height) {
        int[][] grid = new int[width][height];
        input.mapWithIndex((line, y) ->
                line.codePoints()
                    .mapToObj(c -> (char) c)
                    .mapWithIndex((character, x) -> (x, y, character))
            )
            .flatMap(i -> i)
            .forEach(tuple -> grid[(int) tuple.x][(int) tuple.y] = tuple.character);
        return grid;
    }

    public static auto buildNodeGraph(int[][] grid, int width, int height) {
        Node startNode = null;
        Node targetNode = null;
        Map<GridCoordinate, Node> coordinateMap = Maps.newHashMap();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentHeight = grid[x][y];
                GridCoordinate coordinate = new GridCoordinate(x, y);
                Node node = new Node(getActualHeight(currentHeight), coordinate);
                coordinateMap.put(coordinate, node);
                if (currentHeight == 'S') {
                    startNode = node;
                } else if (currentHeight == 'E') {
                    targetNode = node;
                }

                connectNeighbours(width, height, coordinateMap, node);
            }
        }

        assert startNode != null;
        assert targetNode != null;

        return startNode, targetNode, coordinateMap;
    }

    private static void connectNeighbours(int width,
                                          int height,
                                          Map<GridCoordinate, Node> coordinateMap,
                                          Node node) {
        Direction.values()
            .stream()
            .map(direction -> direction.getMoveFunction().apply(node.getCoordinate()))
            .filter(neighbourCoordinate -> neighbourCoordinate.valid(width, height))
            .filter(coordinateMap::containsKey)
            .map(coordinateMap::get)
            .forEach(neighbour -> {
                neighbour.connectNeighbour(node);
                node.connectNeighbour(neighbour);
            });
    }

    private static int getActualHeight(int currentHeight) {
        if (currentHeight == 'S') {
            return 'a';
        } else if (currentHeight == 'E') {
            return 'z';
        } else {
            return currentHeight;
        }
    }

    public static List<Node> runPathFinding(Node startNode, Node targetNode) {
        Map<Node, Node> previousNodes = Maps.newHashMap();
        List<Node> nodesToSearch = Lists.newArrayList();
        nodesToSearch.add(startNode);
        Map<Node, Integer> actualScores = Maps.newHashMap();
        actualScores.put(startNode, 0);
        Map<Node, Integer> guessedScores = Maps.newHashMap();
        guessedScores.put(startNode, startNode.calculateDistance(targetNode));

        while (!nodesToSearch.isEmpty()) {
            Node currentNode = getNodeWithLowestGuessedScore(nodesToSearch, guessedScores);

            if (currentNode.equals(targetNode)) {
                return reconstructPath(startNode, targetNode, previousNodes);
            }

            currentNode.getNeighbours().forEach(neighbour -> {
                int score = actualScores.get(currentNode) + 1;
                if (score < actualScores.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    previousNodes.put(neighbour, currentNode);
                    actualScores.put(neighbour, score);
                    guessedScores.put(neighbour, score + neighbour.calculateDistance(targetNode));
                    if (!nodesToSearch.contains(neighbour)) {
                        nodesToSearch.add(neighbour);
                    }
                }
            });
        }

        return List.of();
    }

    private static Node getNodeWithLowestGuessedScore(List<Node> nodesToSearch, Map<Node, Integer> guessedScores) {
        return nodesToSearch.stream()
            .min(Comparator.comparingInt(node -> guessedScores.getOrDefault(node, Integer.MAX_VALUE)))
            .filter(nodesToSearch::remove)
            .orElseThrow();
    }

    private static List<Node> reconstructPath(Node startNode, Node targetNode, Map<Node, Node> previousNodes) {
        List<Node> path = Lists.newArrayList(targetNode);
        Node currentNode = previousNodes.get(targetNode);
        while (!currentNode.equals(startNode)) {
            path.add(currentNode);
            currentNode = previousNodes.get(currentNode);
        }
        path.add(startNode);
        return path.reversed();
    }
}
