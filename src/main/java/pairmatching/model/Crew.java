package pairmatching.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Crew {
    private Course course;
    private String name;
    private Map<Level, List<Crew>> matchedCrew = new HashMap<>();

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public boolean isMatchedCrew(List<Crew> crew, Level level) {
        List<Crew> matchCrews = matchedCrew.getOrDefault(level, new ArrayList<>());
        crew.remove(this);
        if (matchCrews.containsAll(crew)) {
            return true;
        }
        matchCrews.addAll(crew);
        matchedCrew.put(level, matchCrews);
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return course == crew.course && Objects.equals(name, crew.name) && Objects.equals(matchedCrew,
                crew.matchedCrew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, name, matchedCrew);
    }

    @Override
    public String toString() {
        return "Crew{" +
                "course=" + course +
                ", name='" + name + '\'' +
                '}';
    }
}