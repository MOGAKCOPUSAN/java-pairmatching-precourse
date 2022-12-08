package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Crews {

    private List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public List<String> getNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
