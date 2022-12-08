package pairmatching.domain;

import pairmatching.Course;
import pairmatching.Level;
import pairmatching.Mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PairMatchingMachine {

    private final List<String> shuffledCrewNames;
    private final List<String> matchedPairs = new ArrayList<>();

    public PairMatchingMachine(List<String> shuffledCrewNames) {
        this.shuffledCrewNames = shuffledCrewNames;
    }

    public void generate() {
        if (isCrewNamesOdd()) {
            generatePairs();
            addLastCrew();
        }

        if (!isCrewNamesOdd()) {
            generatePairs();
        }
    }

    private void generatePairs() {
        List<Integer> pairMatchingIndexes = IntStream.rangeClosed(0, shuffledCrewNames.size())
                .filter(value -> value % 2 != 0)
                .boxed()
                .collect(Collectors.toList());
        for (Integer pairMatchingIndex : pairMatchingIndexes) {
            String matchedPair = shuffledCrewNames.get(pairMatchingIndex - 1) +
                    " : " + shuffledCrewNames.get(pairMatchingIndex);
            matchedPairs.add(matchedPair);
        }
    }

    private void addLastCrew() {
        int lastPairIndex = matchedPairs.size() - 1;
        String beforeLastPair = matchedPairs.get(lastPairIndex);
        int lastCrewIndex = shuffledCrewNames.size() - 1;
        String finalLastPair = beforeLastPair + shuffledCrewNames.get(lastCrewIndex);
        matchedPairs.remove(lastPairIndex);
        matchedPairs.add(finalLastPair);
    }

    private boolean isCrewNamesOdd() {
        return shuffledCrewNames.size() % 2 != 0;
    }

    public List<String> getMatchedPairs() {
        return Collections.unmodifiableList(matchedPairs);
    }
}
