package pairmatching.domain;

import java.util.List;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Condition condition = (Condition) o;
        return getCourse().getName().equals(condition.getCourse().getName())
                && getLevel().getName().equals(condition.getLevel().getName())
                && Objects.equals(getMission(), condition.getMission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse().getName(), getLevel().getName(), getMission());
    }
}
