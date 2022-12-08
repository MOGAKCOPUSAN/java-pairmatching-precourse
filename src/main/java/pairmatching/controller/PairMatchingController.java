package pairmatching.controller;

import java.util.Arrays;
import java.util.List;
import pairmatching.model.Command;
import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.model.Pairs;
import pairmatching.service.CrewService;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    String regex = ", ";
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
        List<String> options = getOptions();
        Pairs match = pairService.getPairs(Course.of(options.get(0)), options.get(2));
        outputView.printPair(match);
    }

    private void matchPair() {
        List<String> options = getOptions();
        List<Crew> crews = crewService.findCrewsByCourse(Course.of(options.get(0)));
        Pairs match = pairService.match(crews, options.get(2));
        outputView.printPair(match);
    }

    private List<String> getOptions() {
        String option = inputView.readOption();
        return Arrays.asList(option.split(regex));
    }

}
