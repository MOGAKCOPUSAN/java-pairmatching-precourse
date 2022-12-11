package pairmatching.domain;

import java.util.List;

public class Condition {

    private final Course course;
    private final Level level;
    private final String mission;

    public Condition(List<String> conditions) {
        this.course = Course.getCourse(conditions.get(0));
        this.level = Level.getLevel(conditions.get(1), conditions.get(2));
        this.mission = conditions.get(2);
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}
