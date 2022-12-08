package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PairMatchingHistory {

    private List<PairMatchingResult> pairMatchingResults = new ArrayList<>();

    public void save(PairMatchingResult pairMatchingResult) {
        pairMatchingResults.add(pairMatchingResult);
    }

    public boolean isExists(Program program) {
        List<PairMatchingResult> matchedResults = pairMatchingResults.stream()
                .filter(pairMatchingResult -> pairMatchingResult.isEqualProgram(program))
                .collect(Collectors.toList());
        return !matchedResults.isEmpty();
    }
}
