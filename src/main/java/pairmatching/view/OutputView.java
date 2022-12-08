package pairmatching.view;

import pairmatching.Course;
import pairmatching.Level;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String FUNCTION_CHOICE_MESSAGE = "기능을 선택하세요.";
    private static final String PAIR_MATCHING_COMMAND_MESSAGE = "1. 페어 매칭";
    private static final String PAIR_FIND_COMMAND_MESSAGE = "2. 페어 조회";
    private static final String PAIR_INITIALIZE_COMMAND_MESSAGE = "3. 페어 초기화";
    private static final String END_COMMAND_MESSAGE = "Q. 종료";
    private static final String PRINT_FUNCTION_DELIMITER = "#############################################";
    private static final String COURSE_JOIN_DELIMITER = " | ";
    private static final String CHOICE_PROGRAM_MESSAGE = "과정, 레벨, 미션을 선택하세요.";
    private static final String PAIR_MATCHING_RESULT_MESSAGE = "페어 매칭 결과입니다.";
    private static final String PAIR_REMATCHING_MESSAGE = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?";
    private static final String YES_OR_NO_COMMAND_MESSAGE = "네 | 아니오";
    private static final String PAIR_INITIALIZE_SUCCESS_MESSAGE = "초기화 되었습니다.";
    private static final String MISSION_MESSAGE = "미션:";
    private static final String MISSION_JOIN_DELIMITER = " | ";

    public void printFunctionChoiceMessage() {
        printMessage(FUNCTION_CHOICE_MESSAGE);
    }

    public void printFunctionCommand() {
        printMessage(PAIR_MATCHING_COMMAND_MESSAGE);
        printMessage(PAIR_FIND_COMMAND_MESSAGE);
        printMessage(PAIR_INITIALIZE_COMMAND_MESSAGE);
        printMessage(END_COMMAND_MESSAGE);
    }

    public void printDelimiter() {
        printMessage(PRINT_FUNCTION_DELIMITER);
    }

    public void printProgrammingCourse(List<String> courses) {
        printMessage(String.join(COURSE_JOIN_DELIMITER, courses));
    }

    public void printMissionByLevel(Map<Level, List<String>> missionByLevel) {
        printMessage(MISSION_MESSAGE);
        printMessage("- 레벨1:" + String.join(MISSION_JOIN_DELIMITER, missionByLevel.get(Level.LEVEL1)));
        printMessage("- 레벨2:" + String.join(MISSION_JOIN_DELIMITER, missionByLevel.get(Level.LEVEL2)));
        printMessage("- 레벨3:" + String.join(MISSION_JOIN_DELIMITER, missionByLevel.get(Level.LEVEL3)));
        printMessage("- 레벨4:" + String.join(MISSION_JOIN_DELIMITER, missionByLevel.get(Level.LEVEL4)));
        printMessage("- 레벨5:" + String.join(MISSION_JOIN_DELIMITER, missionByLevel.get(Level.LEVEL5)));
    }

    public void printChoiceProgramMessage() {
        printMessage(CHOICE_PROGRAM_MESSAGE);
    }

    public void printPairMatchingResultMessage() {
        printMessage(PAIR_MATCHING_RESULT_MESSAGE);
    }

    public void printPairRematchingMessage() {
        printMessage(PAIR_REMATCHING_MESSAGE);
        printMessage(YES_OR_NO_COMMAND_MESSAGE);
    }

    public void printPairInitializeSuccessMessage() {
        printMessage(PAIR_INITIALIZE_SUCCESS_MESSAGE);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
