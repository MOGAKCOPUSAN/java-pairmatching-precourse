package pairmatching;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course getCourse(String courseName) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(courseName))
                .findAny()
                .orElseThrow(() ->
                        new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "정확한 코스 이름을 입력해주세요."));
    }
}
