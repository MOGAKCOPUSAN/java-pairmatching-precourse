package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.FunctionCommand;

import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String BLANK = " ";
    private static final String PROGRAM_SPLIT_DELIMITER = ",";

    public FunctionCommand inputFunctionCommand() {
        String functionCommand = Console.readLine();
        return FunctionCommand.getCommand(functionCommand);
    }

    public List<String> inputChoiceProgram() {
        String program = Console.readLine();
        String removeBlankProgram = program.replace(BLANK, "");
        return Arrays.asList(removeBlankProgram.split(PROGRAM_SPLIT_DELIMITER));
    }
}
