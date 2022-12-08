package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingHistory {

    private List<PairMatchingResult> pairMatchingResults = new ArrayList<>();

    public void save(PairMatchingResult pairMatchingResult) {
        pairMatchingResults.add(pairMatchingResult);
    }
}
