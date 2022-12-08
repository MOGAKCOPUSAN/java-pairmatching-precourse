package pairmatching.view;

public class OutputView {

    private static final String FUNCTION_CHOICE_MESSAGE = "기능을 선택하세요.";
    private static final String PAIR_MATCHING_COMMAND_MESSAGE = "1. 페어 매칭";
    private static final String PAIR_FIND_COMMAND_MESSAGE = "2. 페어 조회";
    private static final String PAIR_INITIALIZE_COMMAND_MESSAGE = "3. 페어 초기화";
    private static final String END_COMMAND_MESSAGE = "Q. 종료";
    private static final String FUNCTION_DELIMITER = "#############################################";

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
        printMessage(FUNCTION_DELIMITER);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
