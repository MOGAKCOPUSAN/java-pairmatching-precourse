package pairmatching.domain.program;

import java.util.Objects;

public class Program {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public Program(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return course == program.course && level == program.level && mission == program.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
