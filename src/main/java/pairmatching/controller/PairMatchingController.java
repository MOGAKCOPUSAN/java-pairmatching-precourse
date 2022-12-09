package pairmatching.controller;

import java.util.Arrays;
import java.util.List;
import pairmatching.model.Command;
import pairmatching.model.Course;
import pairmatching.model.Pairs;
import pairmatching.service.CrewService;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private String regex = ", ";
    private int tryCount = 0;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CrewService crewService = new CrewService();
    private final PairService pairService = new PairService();

    public void run() {
        String inputCommand = inputView.readCommand();
        Command command = Command.of(inputCommand);
        if (command == Command.MATCHING || command == Command.INITIALIZE) {
            matchPair();
        }
        if (command == Command.READ) {
            readPair();
        }
        if (command == Command.QUIT) {
            return;
        }
    }

    private void readPair() {
        try {
            List<String> options = getOptions();
            Pairs match = pairService.getPairs(Course.of(options.get(0)), options.get(2));
            outputView.printPair(match);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void matchPair() {
        try {
            List<String> options = getOptions();
            Course of = Course.of(options.get(0));
            List<String> crews = crewService.findCrewsByCourse(of);
            Pairs match = match(crews, of, options.get(2));
            outputView.printPair(match);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private List<String> getOptions() {
        String option = inputView.readOption();
        return Arrays.asList(option.split(regex));
    }

    public Pairs match(List<String> crews, Course course, String mission) {
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
