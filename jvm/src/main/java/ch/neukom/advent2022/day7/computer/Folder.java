package ch.neukom.advent2022.day7.computer;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

public class Folder {
    private final String name;
    @Nullable
    private final Folder parent;
    private final List<Folder> folders = Lists.newArrayList();
    private final List<File> files = Lists.newArrayList();

    private long size = -1;

    public Folder(String name, @Nullable Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Folder getParent() {
        return parent;
    }

    public Optional<Folder> getFolder(String name) {
        return folders.stream().filter(folder -> folder.getName().equals(name)).findFirst();
    }

    public void addFolder(Folder folder) {
        this.folders.add(folder);
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public long getSize() {
        if (size < 0) {
            size = calculateSize();
        }
        return size;
    }

    private long calculateSize() {
        return LongStream.concat(
            folders.stream().mapToLong(Folder::getSize),
            files.stream().mapToLong(File::size)
        ).sum();
    }

    public List<Folder> getFolders() {
        return folders;
    }
}
