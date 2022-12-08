package pairmatching.controller;

import pairmatching.Course;
import pairmatching.CrewFileReader;
import pairmatching.FunctionCommand;
import pairmatching.Level;
import pairmatching.domain.*;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PairMatchingController {

    private final InputView inputView;
    private final OutputView outputView;
    private BackendCrews backendCrews;
    private FrontendCrews frontendCrews;
    private PairMatchingMachine pairMatchingMachine;
    private PairMatchingResult pairMatchingResult;
    private final PairMatchingHistory pairMatchingHistory;

    private static final int PROGRAM_COURSE_INDEX = 0;
    private static final int PROGRAM_LEVEL_INDEX = 1;
    private static final int PROGRAM_MISSION_INDEX = 2;

    private String gameStatus = "start";
    private FunctionCommand functionCommand;

    public PairMatchingController() throws IOException {
        inputView = new InputView();
        outputView = new OutputView();
        pairMatchingHistory = new PairMatchingHistory();
        generateCrewsStep();
    }

    public void run() {
        while (gameStatus.equals("end")) {
            inputFunctionCommand();
            choiceFunctionStep();
        }
    }

    private void generateCrewsStep() throws IOException {
        generateBackendCrews();
        generateFrontendCrews();
    }

    private void generateBackendCrews() throws IOException {
        List<String> backendCrewNames = CrewFileReader.backendCrewNameFileRead();
        List<Crew> generatedBackendCrews = backendCrewNames.stream()
                .map(crewName -> new Crew(Course.BACKEND, crewName))
                .collect(Collectors.toList());
        backendCrews = new BackendCrews(generatedBackendCrews);
    }

    private void generateFrontendCrews() throws IOException {
        List<String> frontendCrewNames = CrewFileReader.frontendCrewNameFileRead();
        List<Crew> generatedFrontendCrews = frontendCrewNames.stream()
                .map(crewName -> new Crew(Course.FRONTEND, crewName))
                .collect(Collectors.toList());
        frontendCrews = new FrontendCrews(generatedFrontendCrews);
    }
    
    private void inputFunctionCommand() {
        outputView.printFunctionCommand();
        functionCommand = inputView.inputFunctionCommand();
    }

    private void choiceFunctionStep() {
        if (functionCommand.equals(FunctionCommand.PAIR_MATCHING)) {
            printProgram();
            List<String> program = inputView.inputChoiceProgram();
            Course course = Course.getCourse(program.get(PROGRAM_COURSE_INDEX));
            Level level = Level.getLevel(program.get(PROGRAM_LEVEL_INDEX));
            Mission mission = Mission.getMission(program.get(PROGRAM_MISSION_INDEX));
            pairMatchingStep(course, mission);
        }
        if (functionCommand.equals(FunctionCommand.PAIR_FIND)) {
            printProgram();
            List<String> program = inputView.inputChoiceProgram();
            Course course = Course.getCourse(program.get(PROGRAM_COURSE_INDEX));
            Level level = Level.getLevel(program.get(PROGRAM_LEVEL_INDEX));
            Mission mission = Mission.getMission(program.get(PROGRAM_MISSION_INDEX));

        }
    }

    private void printProgram() {
        outputView.printDelimiter();
        outputView.printMissionByLevel(Mission.getMissionByLevel());
        outputView.printDelimiter();
        outputView.printChoiceProgramMessage();
    }

    private void pairMatchingStep(Course course, Mission mission) {
        if (course.equals(Course.BACKEND)) {
            List<String> shuffledCrewNames = ShuffleMachine.getShuffleResult(backendCrews.getNames());
            pairMatchingMachine = new PairMatchingMachine(shuffledCrewNames, course, mission);
            pairMatchingMachine.generate();
            List<String> matchedPairs = pairMatchingMachine.getMatchedPairs();
            pairMatchResultStep(matchedPairs, course, mission);
        }

    }

    private void pairMatchResultStep(List<String> matchedPairs, Course course, Mission mission) {
        pairMatchingResult = new PairMatchingResult(matchedPairs, course, mission);
        outputView.printPairMatchingResultMessage();
        outputView.printPairMatchingResult(matchedPairs);
        savePairMatchingResult();
    }

    private void savePairMatchingResult() {
        pairMatchingHistory.save(pairMatchingResult);
    }
}
