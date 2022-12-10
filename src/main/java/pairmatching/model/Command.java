package pairmatching.model;

import java.util.Arrays;

public enum Command {
    MATCHING("1"),
    FIND("2"),
    INITIALIZE("3"),
    QUIT("Q");

    private String word;

    Command(String word) {
        this.word = word;
    }

    public static Command of(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.word.equals(input))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
