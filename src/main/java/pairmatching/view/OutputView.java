package pairmatching.view;

import pairmatching.domain.Pair;

import java.util.Set;

public class OutputView {

    public static final String MATCHING_RESULT_MESSAGE = "페어 매칭 결과입니다.";
    public static final String DELIMITER = " : ";

    public void showMatchingResult(Set<Pair> pairs) {
        printMessage(MATCHING_RESULT_MESSAGE);
        pairs.stream()
                .forEach(pair -> printMessage(String.join(DELIMITER, pair.getCrews())));
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
