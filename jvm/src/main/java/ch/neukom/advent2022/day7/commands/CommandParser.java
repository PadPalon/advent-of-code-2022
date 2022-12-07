package ch.neukom.advent2022.day7.commands;

import java.util.List;

import com.google.common.collect.Lists;

public class CommandParser {
    private static final String COMMAND_START = "$ ";
    private static final String CD_START = "cd ";
    private static final String LS_START = "ls";

    private final List<Command> parsedCommands = Lists.newArrayList();

    private Command currentCommand;

    public void parse(String line) {
        if (line.startsWith(COMMAND_START)) {
            currentCommand = parseCommand(line.substring(COMMAND_START.length()));
            parsedCommands.add(currentCommand);
        } else if (currentCommand instanceof ListCommand listCommand) {
            listCommand.addResult(line);
        }
    }

    public List<Command> getCommands() {
        return parsedCommands;
    }

    private Command parseCommand(String line) {
        if (line.startsWith(CD_START)) {
            return new ChangeDirectoryCommand(line.substring(CD_START.length()));
        } else if (line.startsWith(LS_START)) {
            return new ListCommand();
        } else {
            throw new IllegalArgumentException("Unparsable line $line");
        }
    }
}
