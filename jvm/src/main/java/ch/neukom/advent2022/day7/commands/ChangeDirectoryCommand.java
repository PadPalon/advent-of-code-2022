package ch.neukom.advent2022.day7.commands;

import ch.neukom.advent2022.day7.computer.Computer;

public class ChangeDirectoryCommand implements Command {
    private final String name;

    public ChangeDirectoryCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Computer computer) {
        computer.move(name);
    }
}
