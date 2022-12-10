package pairmatching.controller;

import java.util.List;
import java.util.NoSuchElementException;
import pairmatching.model.Command;
import pairmatching.model.Course;
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
        String inputCommand = inputView.readCommand();
        Command command = Command.of(inputCommand);
        do {
            if (command == Command.MATCHING || command == Command.INITIALIZE) {
                matchPair();
            }
            if (command == Command.FIND) {
                findPair();
            }
        } while (command == Command.QUIT);

    }

    private void findPair() {
        try {
            List<String> options = inputView.readOption();
            Pairs match = pairService.getPairs(Course.of(options.get(0)), options.get(2));
            outputView.printPair(match);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            findPair();
        }
    }

    private void matchPair() {
        try {
            List<String> options = inputView.readOption();
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
                throw new NoSuchElementException("3번 실패했습니다");
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
