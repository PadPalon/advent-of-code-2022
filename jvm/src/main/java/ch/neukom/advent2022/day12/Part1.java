package ch.neukom.advent2022.day12;

import java.io.IOException;
import java.util.List;

import ch.neukom.advent2022.util.InputResourceReader;
import manifold.ext.rt.api.auto;

/**
 * full A* algorithm, is probably overkill, but it's just fun to implement
 */
public class Part1 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        int height = (int) reader.getLineCount();
        int width = reader.getFirstLine().length();

        int[][] grid = Util.loadGrid(reader.readInput(), width, height);

        auto nodeGraph = Util.buildNodeGraph(grid, width, height);

        List<Node> path = Util.runPathFinding(nodeGraph.startNode, nodeGraph.targetNode);
        System.out.println("The shortest possible path is ${path.size() - 1}");
    }
}
