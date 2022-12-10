package pairmatching.domain.program;

import pairmatching.ErrorConstants;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level getLevel(String levelName) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(levelName))
                .findAny()
                .orElseThrow(() ->
                        new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "정확한 레벨을 입력해주세요."));
    }
}
