package ch.neukom.advent2022.day7.computer;

public class Computer {
    private final Folder root;

    private Folder current;

    public Computer() {
        this.root = new Folder("", null);
        this.current = this.root;
    }

    public Folder move(String name) {
        if (name.equals("/")) {
            current = root;
            return root;
        }

        if (name.equals("..")) {
            return moveUp();
        }

        current = current.getFolder(name).orElseGet(() -> addFolder(name));
        return current;
    }

    public Folder moveUp() {
        Folder parent = current.getParent();
        if (parent != null) {
            current = parent;
        }
        return current;
    }

    public Folder addFolder(String name) {
        Folder folder = new Folder(name, current);
        current.addFolder(folder);
        return folder;
    }

    public File addFile(String name, long size) {
        File file = new File(size, name, current);
        current.addFile(file);
        return file;
    }

    public Folder getRoot() {
        return root;
    }
}
