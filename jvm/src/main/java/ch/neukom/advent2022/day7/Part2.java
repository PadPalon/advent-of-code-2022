package ch.neukom.advent2022.day7;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import ch.neukom.advent2022.day7.computer.Computer;
import ch.neukom.advent2022.day7.computer.Folder;
import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.collect.Lists;

import static java.util.Comparator.*;

public class Part2 {
    public static final long TOTAL_SIZE = 70000000;
    public static final long REQUIRED_SPACE = 30000000;

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Computer computer = Util.getComputer(reader.readInput());

        Folder root = computer.getRoot();
        long currentlyUnusedSpace = TOTAL_SIZE - root.getSize();
        long spaceToBeDeleted = REQUIRED_SPACE - currentlyUnusedSpace;
        Folder folderToDelete = gatherFolders(root)
            .map(folder -> (folder, difference:spaceToBeDeleted - folder.getSize()))
            .filter(tuple -> tuple.difference < 0)
            .max(comparingLong(tuple -> tuple.difference))
            .map(tuple -> tuple.folder)
            .orElseThrow();

        System.out.println("The size of the folder to be deleted is ${folderToDelete.getSize()}");
    }

    private static List<Folder> gatherFolders(Folder folder) {
        List<Folder> folders = Lists.newArrayList(folder);
        folder.getFolders()
            .map(Part2::gatherFolders)
            .flatMap(Collection::stream)
            .forEach(folders::add);
        return folders;
    }
}
