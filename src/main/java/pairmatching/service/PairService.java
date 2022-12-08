package pairmatching.service;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.model.Pairs;

public class PairService {
    private static final int PAIR_SIZE = 2;
    private int tryCount = 0;

    public Pairs match(List<Crew> crews, String mission) {
        try {
            if (isOver()) {
                throw new IllegalArgumentException("3번 실패했습니다");
            }
            return matchCrew(crews, mission);
        } catch (IllegalArgumentException e) {
            tryCount++;
            return match(crews, mission);
        }
    }

    private boolean isOver() {
        return tryCount == 3;
    }

    private Pairs matchCrew(List<Crew> crews, String mission) {
        List<Crew> shuffle = Randoms.shuffle(crews);
        List<Pair> pairs = new ArrayList<>();

        for (int index = 0; index < shuffle.size(); index += PAIR_SIZE) {
            int min = Math.min(index + PAIR_SIZE, shuffle.size());
            if (isLastOdd(shuffle, index)) {
                pairs.add(new Pair(shuffle.subList(shuffle.size() - 3, min + 1), mission));
                break;
            }
            pairs.add(new Pair(shuffle.subList(index, min), mission));
        }
        return new Pairs(mission, pairs);
    }

    private boolean isLastOdd(List<Crew> crews, int index) {
        return crews.size() - index == 3;
    }

    public Pairs getPairs(Course course, String mission) {
        return null;
    }
}


