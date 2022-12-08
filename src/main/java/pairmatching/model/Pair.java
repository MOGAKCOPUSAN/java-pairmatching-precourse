package pairmatching.model;

import java.util.List;

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
                throw new IllegalArgumentException()
                        ;
            }
        }
    }
}
