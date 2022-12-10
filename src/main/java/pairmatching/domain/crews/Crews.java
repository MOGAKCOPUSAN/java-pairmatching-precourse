package pairmatching.domain.crews;

import pairmatching.domain.Crew;

import java.util.List;

public interface Crews {

    List<String> getNames();

    Crew findCrew(String crewName);

    void initializeMatchedCrewsByLevel();
}
