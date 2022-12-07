package ch.neukom.advent2022.day7.commands;

import ch.neukom.advent2022.day7.computer.Computer;

public interface Command {
    void execute(Computer computer);
}
