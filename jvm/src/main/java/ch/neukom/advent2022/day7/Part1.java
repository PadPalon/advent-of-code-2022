package ch.neukom.advent2022.day7;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import ch.neukom.advent2022.day7.computer.Computer;
import ch.neukom.advent2022.day7.computer.Folder;
import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.collect.Lists;

public class Part1 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        Computer computer = Util.getComputer(reader.readInput());

        long sumOfSmallFolders = findSmallFolders(computer.getRoot())
            .stream()
            .mapToLong(Folder::getSize)
            .sum();

        System.out.println("The sum of the sizes of the small folders is $sumOfSmallFolders");
    }

    private static List<Folder> findSmallFolders(Folder folder) {
        List<Folder> smallFolders = Lists.newArrayList();
        if (folder.getSize() <= 100000) {
            smallFolders.add(folder);
        }
        folder.getFolders()
            .map(Part1::findSmallFolders)
            .flatMap(Collection::stream)
            .forEach(smallFolders::add);
        return smallFolders;
    }
}
