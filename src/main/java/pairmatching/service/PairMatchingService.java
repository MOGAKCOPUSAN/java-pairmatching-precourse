package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Condition;
import pairmatching.domain.Pair;
import pairmatching.repository.PairMatchingRepository;

import java.util.*;

public class PairMatchingService {

    public static final String QUIT = "Q";
    public static final int PAIR_SIZE = 2;

    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public Set<Pair> match(Condition condition, List<String> crews) {
        if (isEvenSize(crews)) {
            Set<Pair> pairs = getEvenCrews(getShuffledCrews(crews));
            pairMatchingRepository.add(condition, pairs);
            return pairs;
        }
        Set<Pair> pairs = getOddCrews(getShuffledCrews(crews));
        pairMatchingRepository.add(condition, pairs);
        return pairs;
    }

    private boolean isEvenSize(List<String> crews) {
        return crews.size() % 2 == 0;
    }

    private Set<Pair> getEvenCrews(List<String> crews) {
        Set<Pair> pairs = new LinkedHashSet<>();
        for (int index = 0; index < crews.size(); index += PAIR_SIZE) {
            pairs.add(new Pair(crews.subList(index, index + PAIR_SIZE)));
        }
        return pairs;
    }

    private Set<Pair> getOddCrews(List<String> crews) {
        Set<Pair> pairs = new LinkedHashSet<>();
        for (int index = 0; index < crews.size(); index += PAIR_SIZE) {
            if (isLastIndexInOddCrews(crews, index)) {
                pairs.add(new Pair(crews.subList(index, index + PAIR_SIZE + 1)));
                break;
            }
            pairs.add(new Pair(crews.subList(index, index + PAIR_SIZE)));
        }
        return pairs;
    }

    private boolean isLastIndexInOddCrews(List<String> crews, int index) {
        return index == crews.size() - 3;
    }

    private List<String> getShuffledCrews(List<String> crews) {
        return Randoms.shuffle(crews);
    }

    public Set<Pair> search(Condition condition) {
        return pairMatchingRepository.findByCondition(condition);
    }

    public void clear() {
        pairMatchingRepository.clear();
    }

    public boolean hasMatchingResult(Condition condition) {
        return pairMatchingRepository.hasMatchingCondition(condition);
    }

    public boolean canContinue(String choice) {
        return !choice.equals(QUIT);
    }
}
