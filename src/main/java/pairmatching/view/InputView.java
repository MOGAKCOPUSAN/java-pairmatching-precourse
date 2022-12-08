package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.FunctionCommand;

public class InputView {

    public FunctionCommand inputFunctionCommand() {
        String functionCommand = Console.readLine();
        return FunctionCommand.getCommand(functionCommand);
    }
}
