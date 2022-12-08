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

    public static void validateFunctionCommand(String functionCommand) {
        if (isWrongFunctionCommand(functionCommand)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "기능 커맨드를 제대로 입력해주세요.");
        }
    }

    private static boolean isWrongFunctionCommand(String functionCommand) {
        long matchCount = Arrays.stream(values())
                .map(value -> value.key)
                .filter(key -> key.equals(functionCommand))
                .count();
        return matchCount == 0;
    }
}
