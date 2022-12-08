package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    // 추가 기능 구현
    public static List<String> getAllCourseName() {
        return Arrays.stream(values())
                .map(Course::getName)
                .collect(Collectors.toList());
    }

    public static Course getCourse(String inputName) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(inputName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 코스가 없습니다."));
    }

    public String getName() {
        return name;
    }
}