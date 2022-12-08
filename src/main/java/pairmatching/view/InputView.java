package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.stream.Collectors;
import pairmatching.model.Course;
import pairmatching.model.Level;

public class InputView {
    private static final String SELECT_COMMAND_MESSAGE = "기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료";
    private static final String LINE = "#############################################\n";
    private static final String COURSE = "과정: ";
    private static final String DELIMITER = " | ";
    private static final String EXAMPLE_MESSAGE = "과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주";

    public String readCommand() {
        System.out.println(SELECT_COMMAND_MESSAGE);
        return Console.readLine();
    }

    public String readOption() {
        printCourse();
        printMissions();
        System.out.println(LINE + EXAMPLE_MESSAGE);
        return Console.readLine();
    }

    private void printCourse() {
        String courseNames = Arrays.stream(Course.values())
                .map(Course::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println(LINE + COURSE + courseNames);
    }

    private void printMissions() {
        System.out.println("\n미션:");
        for (Level level : Level.values()) {
            System.out.println(level + ": " + String.join(DELIMITER, level.getMission()));
        }
    }
}
