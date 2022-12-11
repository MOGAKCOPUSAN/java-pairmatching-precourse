package pairmatching.model;

import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private List<Crew> crews;
    private Level level;

    public Pair(List<Crew> crews, String mission) {
        this.level = Level.missionOf(mission);
        validateMatched(crews);
        this.crews = crews;
    }

    private void validateMatched(List<Crew> crews) {
        for (Crew crew : crews) {
            if (crew.isMatchedCrew(crews, level)) {
                throw new IllegalArgumentException("이미 매칭된 크루");
            }
        }
    }

    public List<String> getCrewNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
