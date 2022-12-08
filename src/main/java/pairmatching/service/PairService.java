package pairmatching.service;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.model.Crew;
import pairmatching.model.Pair;

public class PairService {
    private final CrewService crewService = new CrewService();

    public List<Pair> matchCrew(List<Crew> crews, String mission) {
        List<Crew> shuffle = Randoms.shuffle(crews);
        List<Pair> pairs = new ArrayList<>();

        int limit = 2;
        for (int index = 0; index < shuffle.size(); index += limit) {
            int min = Math.min(index + limit, shuffle.size());
            if (isLastOdd(shuffle, index)) {
                pairs.add(new Pair(mission, shuffle.subList(shuffle.size() - 3, min + 1)));
                break;
            }
            pairs.add(new Pair(mission, shuffle.subList(index, min)));
        }
        return pairs;
    }

    private boolean isLastOdd(List<Crew> crews, int index) {
        return crews.size() - index == 3;
    }
}


