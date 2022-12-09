package pairmatching.domain;

import pairmatching.ErrorConstants;

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

    public List<String> findMatchedPair(Program program) {
        return pairMatchingResults.stream()
                .filter(pairMatchingResult -> pairMatchingResult.isEqualProgram(program))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "매칭 이력이 없습니다."))
                .getMatchedPairs();
    }

    public void delete(Program program) {
        pairMatchingResults.remove(findPairMatchResultByProgram(program));
    }

    private PairMatchingResult findPairMatchResultByProgram(Program program) {
        return pairMatchingResults.stream()
                .filter(pairMatchingResult -> pairMatchingResult.isEqualProgram(program))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorConstants.ERROR_PREFIX
                        + "찾는 프로그램별 매칭 결과가 없습니다."));
    }

    public void initalize() {
        pairMatchingResults.clear();
    }
}
