package ch.neukom.advent2022.day7;

import java.util.stream.Stream;

import ch.neukom.advent2022.day7.commands.CommandParser;
import ch.neukom.advent2022.day7.computer.Computer;

public class Util {
    private Util() {
    }

    public static Computer getComputer(Stream<String> input) {
        CommandParser parser = new CommandParser();
        input.forEach(parser::parse);
        Computer computer = new Computer();
        parser.getCommands().forEach(command -> command.execute(computer));
        return computer;
    }
}
