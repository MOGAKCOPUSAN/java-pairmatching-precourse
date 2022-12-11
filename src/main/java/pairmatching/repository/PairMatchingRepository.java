package pairmatching.repository;

import pairmatching.domain.Condition;
import pairmatching.domain.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PairMatchingRepository {

    private final Map<Condition, Set<Pair>> matchingResults = new HashMap<>();

    public void add(Condition condition, Set<Pair> pairs) {
        if (!matchingResults.containsKey(condition)) {
            matchingResults.put(condition, pairs);
        }
    }

    public Set<Pair> findByCondition(Condition condition) {
        if (!matchingResults.containsKey(condition)) {
            throw new IllegalArgumentException("해당 페어 매칭 결과가 존재하지 않습니다.");
        }

        return matchingResults.get(condition);
    }

    public void clear() {
        matchingResults.clear();
    }

    public boolean hasMatchingCondition(Condition condition) {
        return matchingResults.containsKey(condition);
    }
}
