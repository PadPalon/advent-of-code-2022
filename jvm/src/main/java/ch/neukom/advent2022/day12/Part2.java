package ch.neukom.advent2022.day12;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import ch.neukom.advent2022.util.InputResourceReader;
import manifold.ext.rt.api.auto;

/**
 * you'd think running this many path findings would take ages, but that algorithm is just disgustingly efficient
 */
public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        int height = (int) reader.getLineCount();
        int width = reader.getFirstLine().length();

        int[][] grid = Util.loadGrid(reader.readInput(), width, height);

        auto nodeGraph = Util.buildNodeGraph(grid, width, height);

        List<Node> shortestPath = nodeGraph.coordinateMap.values()
            .filter(node -> node.getHeight() == 'a')
            .map(node -> Util.runPathFinding(node, nodeGraph.targetNode))
            .filter(path -> path.size() > 0)
            .min(Comparator.comparingInt(List::size))
            .orElseThrow();

        System.out.println("The shortest possible path is ${shortestPath.size() - 1}");
    }
}
