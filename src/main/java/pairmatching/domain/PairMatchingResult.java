package pairmatching.domain;

import pairmatching.Course;

import java.util.List;

public class PairMatchingResult {

    private final List<String> matchedPairs;
    private final Course course;
    private final Mission mission;

    public PairMatchingResult(List<String> matchedPairs, Course course, Mission mission) {
        this.matchedPairs = matchedPairs;
        this.course = course;
        this.mission = mission;
    }
}
