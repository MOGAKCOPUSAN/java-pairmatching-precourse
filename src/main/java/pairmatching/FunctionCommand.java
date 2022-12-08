package pairmatching;

import java.util.Arrays;

public enum FunctionCommand {
    PAIR_MATCHING("1"),
    PAIR_FIND("2"),
    PAIR_INITIALIZE("3"),
    END("Q");

    private String key;

    FunctionCommand(String key) {
        this.key = key;
    }

    public static FunctionCommand getCommand(String functionCommand) {
        return Arrays.stream(values())
                .filter(value -> value.key.equals(functionCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "기능 커맨드를 제대로 입력해주세요."));
    }
}
