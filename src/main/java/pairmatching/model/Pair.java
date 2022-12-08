package pairmatching.model;

import java.util.List;

public class Pair {
    private String mission;
    private List<Crew> crews;

    public Pair(String mission, List<Crew> crews) {
        this.mission = mission;
        this.crews = crews;
    }

    @Override
    public String toString() {
        return "Pair{" +
                ", mission='" + mission + '\'' +
                ", crews=" + crews +
                '}';
    }
}
