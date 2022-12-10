package pairmatching.controller;

import pairmatching.*;
import pairmatching.domain.program.Course;
import pairmatching.domain.program.Mission;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class PairMatchingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    private static final String GAME_STATUS_INIT = "init";
    private static final String GAME_STATUS_END = "end";

    private String gameStatus;

    public PairMatchingController() {
        inputView = new InputView();
        outputView = new OutputView();
        pairMatchingService = new PairMatchingService();
        gameStatus = GAME_STATUS_INIT;
    }

    public void run() {
        while (!gameStatus.equals(GAME_STATUS_END)) {
            pairMatchingService.initPairMatchTryCount();
            FunctionCommand functionCommand = inputFunctionCommand();
            choiceFunctionStep(functionCommand);
        }
    }

    private FunctionCommand inputFunctionCommand() {
        try {
            outputView.printFunctionChoiceMessage();
            outputView.printFunctionCommand();
            return inputView.inputFunctionCommand();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return inputFunctionCommand();
        }

    }

    private void choiceFunctionStep(FunctionCommand functionCommand) {
        if (functionCommand.equals(FunctionCommand.PAIR_MATCHING)) {
            printProgram();
            pairMatchingStart();
        }
        if (functionCommand.equals(FunctionCommand.PAIR_FIND)) {
            pairFindStart();
        }
        if (functionCommand.equals(FunctionCommand.PAIR_INITIALIZE)) {
            pairInitializeStart();
        }
        if (functionCommand.equals(FunctionCommand.END)) {
            gameStatus = GAME_STATUS_END;
        }
    }

    private void printProgram() {
        outputView.printBlankLine();
        outputView.printDelimiter();
        outputView.printProgrammingCourse(Course.getCourseNames());
        outputView.printMissionByLevel(Mission.getMissionByLevel());
        outputView.printDelimiter();
    }

    private void pairMatchingStart() {
        try {
            List<String> programInfos = inputProgram();
            pairMatchingService.generateProgram(programInfos);
            decidePairRematchOrMatchStep();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            pairMatchingStart();
        }
    }

    private void decidePairRematchOrMatchStep() {
        if (pairMatchingService.isResultAlreadyExist()) {
            pairRematchStep();
        }
        if (!pairMatchingService.isResultAlreadyExist()) {
            pairMatchingStep();
        }
    }

    private List<String> inputProgram() {
        outputView.printChoiceProgramMessage();
        return inputView.inputChoiceProgram();
    }

    private void pairRematchStep() {
        RematchCommand rematchCommand = inputRematchCommand();
        if (rematchCommand.equals(RematchCommand.YES)) {
            pairRematchStart();
        }
        if (rematchCommand.equals(RematchCommand.NO)) {
            outputView.printBlankLine();
            pairMatchingStart();
        }
    }

    private RematchCommand inputRematchCommand() {
        try {
            outputView.printPairRematchingMessage();
            RematchCommand rematchCommand = inputView.inputRematchCommand();
            return rematchCommand;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return inputRematchCommand();
        }

    }

    private void pairRematchStart() {
        pairMatchingService.deleteExistResult();
        pairMatchingStep();
    }

    private void pairMatchingStep() {
        try {
            pairMatchingService.pairMatch();
            pairMatchResultStep();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            pairMatchingStep();
        } catch (IllegalStateException tryOverException) {
            outputView.printErrorMessage(tryOverException.getMessage());
        }
    }

    private void pairMatchResultStep() {
        pairMatchingService.generatePairMatchingResult();
        List<String> matchedPairs = pairMatchingService.getMatchedPairs();
        outputView.printPairMatchingResultMessage();
        outputView.printPairMatchingResult(matchedPairs);
        pairMatchingService.savePairMatchingResult();
    }

    private void pairInitializeStart() {
        pairMatchingService.historyInitialize();
        outputView.printPairInitializeSuccessMessage();
    }

    private void pairFindStart() {
        try {
            printProgram();
            List<String> programInfos = inputProgram();
            pairMatchingService.generateProgram(programInfos);
            outputView.printPairMatchingResultMessage();
            outputView.printPairMatchingResult(pairMatchingService.findHistory());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            inputFunctionCommand();
        }
    }
}
