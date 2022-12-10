package pairmatching.controller;

import java.util.List;
import pairmatching.model.Command;
import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.model.Pairs;
import pairmatching.service.CrewService;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private int tryCount = 0;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CrewService crewService = new CrewService();
    private final PairService pairService = new PairService();

    public void run() {
        while (true) {
            Command command = getCommand();
            if (command == Command.MATCHING || command == Command.INITIALIZE) {
                matchPair();
            }
            if (command == Command.FIND) {
                findPair();
            }
            if (command == Command.QUIT) {
                break;
            }
        }
    }

    private Command getCommand() {
        try {
            String inputCommand = inputView.readCommand();
            return Command.of(inputCommand);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getCommand();
        }
    }

    private void findPair() {
        try {
            List<String> options = readOptions();
            Pairs match = pairService.getPairs(Course.of(options.get(0)), options.get(2));
            outputView.printPair(match);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            findPair();
        }
    }

    private List<String> readOptions() {
        try {
            List<String> options = inputView.readOption();
            validateOptions(options);
            return options;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readOptions();
        }
    }

    private void validateOptions(List<String> options) {
        Course.of(options.get(0));
        Level.of(options.get(1));
        Level.missionOf(options.get(2));
    }

    private void matchPair() {
        try {
            List<String> options = readOptions();
            Course course = Course.of(options.get(0));
            List<String> crews = crewService.findCrewsByCourse(course);
            Pairs match = match(crews, course, options.get(2));
            outputView.printPair(match);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            matchPair();
        }
    }

    private Pairs match(List<String> crews, Course course, String mission) {
        try {
            if (isOver()) {
                throw new IllegalArgumentException("3번 실패했습니다");
            }
            return pairService.matchCrew(crews, course, mission);
        } catch (IllegalArgumentException e) {
            tryCount++;
            return match(crews, course, mission);
        }
    }

    private boolean isOver() {
        return tryCount == 3;
    }
}
