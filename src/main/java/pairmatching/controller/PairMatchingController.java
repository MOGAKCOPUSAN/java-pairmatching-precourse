package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.service.PairMatchingService;
import pairmatching.util.FileProcessor;
import pairmatching.util.Log;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;
import java.util.Set;

public class PairMatchingController {

    public static final String FILE_PATH = "src/main/resources/";
    public static final String BACKEND_FILE = "backend-crew.md";
    public static final String FRONTEND_FILE = "frontend-crew.md";
    public static final String PAIR_MATCHING = "1";
    public static final String PAIR_SEARCHING = "2";
    public static final String PAIR_INITIALIZE = "3";
    public static final String NO_REMATCHING = "아니오";

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private List<String> getCondition() {
        try {
            List<String> input = inputView.readCourseAndLevelAndMission();
            return input;
        } catch (IllegalArgumentException e) {
            Log.error(e.getMessage());
            return getCondition();
        }
    }

    private String getRematching() {
        try{
            return inputView.readRematching();
        } catch (IllegalArgumentException e) {
            Log.error(e.getMessage());
            return getRematching();
        }
    }

    private String getUserChoice() {
        try {
            return inputView.readChoice();
        } catch (IllegalArgumentException e) {
            Log.error(e.getMessage());
            return getUserChoice();
        }
    }
}