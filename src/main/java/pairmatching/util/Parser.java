package pairmatching.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public static final String DELIMITER = ",";

    public static List<String> getCourseAndLevelAndMission(String input) {
        return Arrays.stream(input.trim()
                        .split(DELIMITER))
                .collect(Collectors.toList());
    }
}
