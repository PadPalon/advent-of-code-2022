package ch.neukom.advent2022.day7.commands;

import java.util.List;
import java.util.Map;

import ch.neukom.advent2022.day7.computer.Computer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ListCommand implements Command {
    public static final String DIR_START = "dir ";

    private final List<String> folders = Lists.newArrayList();
    private final Map<String, Long> files = Maps.newHashMap();

    @Override
    public void execute(Computer computer) {
        folders.forEach(computer::addFolder);
        files.forEach(computer::addFile);
    }

    public void addResult(String line) {
        if (line.startsWith(DIR_START)) {
            String folderName = line.substring(DIR_START.length());
            this.folders.add(folderName);
        } else {
            String[] parts = line.split(" ");
            files.put(parts[1], Long.parseLong(parts[0]));
        }
    }
}
