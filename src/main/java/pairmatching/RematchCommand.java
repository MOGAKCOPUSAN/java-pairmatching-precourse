package pairmatching;

import java.util.Arrays;

public enum RematchCommand {
    YES("네"),
    NO("아니오");

    private String key;

    RematchCommand(String key) {
        this.key = key;
    }

    public static RematchCommand getRematchCommand(String rematchCommand) {
        return Arrays.stream(values())
                .filter(value -> value.key.equals(rematchCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorConstants.ERROR_PREFIX
                        + "재매칭 여부를 제대로 입력해주세요."));
    }
}
