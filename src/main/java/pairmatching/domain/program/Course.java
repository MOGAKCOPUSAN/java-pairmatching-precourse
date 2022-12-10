package pairmatching.domain.program;

import pairmatching.ErrorConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<String> getCourseNames() {
        List<String> courseNames = new ArrayList<>();
        Arrays.stream(values())
                .forEach(course -> courseNames.add(course.name));
        return courseNames;
    }
}
