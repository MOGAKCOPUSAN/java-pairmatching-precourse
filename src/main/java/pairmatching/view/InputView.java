package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.util.Parser;
import pairmatching.validator.InputValidator;

import java.util.List;

public class InputView {

    public static final String INPUT_CHOICE_MESSAGE = "기능을 선택하세요.";
    public static final String FIRST_CHOICE_MESSAGE = "1. 페어 매칭";
    public static final String SECOND_CHOICE_MESSAGE = "2. 페어 조회";
    public static final String THIRD_CHOICE_MESSAGE = "3. 페어 초기화";
    public static final String QUIT_CHOICE_MESSAGE = "Q. 종료";

    public static final String LINE_SEPARATOR_MESSAGE = "#############################################";
    public static final String COURSE_CHOICE_MESSAGE = "과정: ";
    public static final String MISSION_CHOICE_MESSAGE = "미션: ";
    public static final String DELIMITER = " | ";
    public static final String MISSION_HEADER_MESSAGE = " -";
    public static final String COLON_MESSAGE = ": ";
    public static final String COURSE_LEVEL_MISSION_INPUT_MESSAGE = "과정, 레벨, 미션을 선택하세요.";
    public static final String COURSE_LEVEL_MISSION_EXAMPLE_MESSAGE = "ex) 백엔드, 레벨1, 자동차경주";

    public String readChoice() {
        printChoiceInputMessage();

        String choice = Console.readLine();
        InputValidator.checkChoice(choice);
        return choice;
    }

    private void printChoiceInputMessage() {
        printMessage(INPUT_CHOICE_MESSAGE);
        printMessage(FIRST_CHOICE_MESSAGE);
        printMessage(SECOND_CHOICE_MESSAGE);
        printMessage(THIRD_CHOICE_MESSAGE);
        printMessage(QUIT_CHOICE_MESSAGE);
    }

    public List<String> readCourseAndLevelAndMission() {
        printCourseAndMissionInputMessage();
        String courseAndLevelAndMission = Console.readLine();
        return Parser.getCourseAndLevelAndMission(courseAndLevelAndMission);
    }

    private void printCourseAndMissionInputMessage() {
        printMessage(LINE_SEPARATOR_MESSAGE);
        printMessage(COURSE_CHOICE_MESSAGE + String.join(DELIMITER, Course.getAllCourseName()));
        printMessage(MISSION_CHOICE_MESSAGE);
        Level.getAllLevels()
                .forEach(level -> printMessage(MISSION_HEADER_MESSAGE + level.getName() + COLON_MESSAGE
                        + String.join(DELIMITER, level.getMissions())));
        printMessage(LINE_SEPARATOR_MESSAGE);
        printMessage(COURSE_LEVEL_MISSION_INPUT_MESSAGE);
        printMessage(COURSE_LEVEL_MISSION_EXAMPLE_MESSAGE);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
