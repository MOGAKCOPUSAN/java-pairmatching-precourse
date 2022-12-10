package pairmatching.domain.crews;

import pairmatching.ErrorConstants;
import pairmatching.domain.Crew;
import pairmatching.domain.crews.Crews;

import java.util.List;
import java.util.stream.Collectors;

public class FrontendCrews implements Crews {

    private final List<Crew> crews;

    public FrontendCrews(List<Crew> frontendCrews) {
        this.crews = frontendCrews;
    }

    @Override
    public List<String> getNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Crew findCrew(String crewName) {
        return crews.stream()
                .filter(crew -> crew.getName().equals(crewName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        ErrorConstants.ERROR_PREFIX + "해당 크루 이름을 가진 크루가 없습니다."));
    }

    @Override
    public void initializeMatchedCrewsByLevel() {
        crews.forEach(Crew::initializeMatchedCrewsByLevel);
    }
}
