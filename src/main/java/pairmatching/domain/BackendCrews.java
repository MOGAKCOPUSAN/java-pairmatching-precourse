package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BackendCrews {

    private List<Crew> crews;

    public BackendCrews(List<Crew> backendCrews) {
        this.crews = backendCrews;
    }

    public List<String> getNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
