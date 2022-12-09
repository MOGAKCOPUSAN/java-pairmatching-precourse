package pairmatching.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import pairmatching.model.Course;
import pairmatching.model.Pairs;

public class PairRepository {
    private final Map<PairKey, Pairs> database = new HashMap<>();

    public void save(Course course, String mission, Pairs pairs) {
        database.put(PairKey.of(course, mission), pairs);
    }

    static class PairKey{
        private final Course course;
        private final String mission;

        public PairKey(Course course, String mission) {
            this.course = course;
            this.mission = mission;
        }

        public static PairKey of(Course course, String mission) {
            return new PairKey(course, mission);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            PairKey pairKey = (PairKey) o;
            return course == pairKey.course && Objects.equals(mission, pairKey.mission);
        }

        @Override
        public int hashCode() {
            return Objects.hash(course, mission);
        }
    }
}
