package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.FunctionCommand;

public class InputView {

    public String inputFunctionCommand() {
        String functionCommand = Console.readLine();
        FunctionCommand.validateFunctionCommand(functionCommand);
        return functionCommand;
    }
}
