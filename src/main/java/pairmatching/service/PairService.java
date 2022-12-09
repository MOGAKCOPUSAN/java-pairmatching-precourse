package pairmatching.service;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.model.Pairs;

public class PairService {
    private static final int PAIR_SIZE = 2;

    public Pairs matchCrew(List<String> crews, Course course, String mission) {
        List<Crew> shuffleCrewName = getCrews(crews, course);

        List<Pair> pairs = new ArrayList<>();

        for (int index = 0; index < shuffleCrewName.size(); index += PAIR_SIZE) {
            int min = Math.min(index + PAIR_SIZE, shuffleCrewName.size());
            if (isLastOdd(shuffleCrewName, index)) {
                pairs.add(new Pair(shuffleCrewName.subList(shuffleCrewName.size() - 3, min + 1), mission));
                break;
            }
            pairs.add(new Pair(shuffleCrewName.subList(index, min), mission));
        }
        return new Pairs(mission, pairs);
    }

    private List<Crew> getCrews(List<String> crews, Course course) {
        return Randoms.shuffle(crews).stream()
                .map(crewName -> new Crew(course, crewName))
                .collect(Collectors.toList());
    }

    private boolean isLastOdd(List<Crew> crews, int index) {
        return crews.size() - index == 3;
    }

    public Pairs getPairs(Course course, String mission) {
        return null;
    }
}


