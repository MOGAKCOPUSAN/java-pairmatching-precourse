package pairmatching.domain;

import pairmatching.domain.program.Course;
import pairmatching.domain.program.Level;

import java.util.*;
import java.util.stream.Collectors;

public class Crew {

    private Course course;
    private String name;
    private final Map<Level, List<Crew>> matchedCrewsByLevel = new HashMap<>();

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        initMatchedCrewsByLevel();
    }

    private void initMatchedCrewsByLevel() {
        matchedCrewsByLevel.put(Level.LEVEL1, new ArrayList<>(Collections.emptyList()));
        matchedCrewsByLevel.put(Level.LEVEL2, new ArrayList<>(Collections.emptyList()));
        matchedCrewsByLevel.put(Level.LEVEL3, new ArrayList<>(Collections.emptyList()));
        matchedCrewsByLevel.put(Level.LEVEL4, new ArrayList<>(Collections.emptyList()));
        matchedCrewsByLevel.put(Level.LEVEL5, new ArrayList<>(Collections.emptyList()));
    }

    public String getName() {
        return name;
    }

    public void putMatchedCrew(Level level, Crew crewToAdd) {
        List<Crew> matchedCrews = matchedCrewsByLevel.get(level);
        matchedCrews.add(crewToAdd);
        matchedCrewsByLevel.put(level, matchedCrews);
    }

    public void initializeMatchedCrewsByLevel() {
        matchedCrewsByLevel.clear();
    }

    public boolean isAlreadyMatchingCrewByLevel(Crew crew, Level level) {
        List<Crew> matchedCrews = matchedCrewsByLevel.get(level);
        return matchedCrews.contains(crew);
    }
}
