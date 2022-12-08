package pairmatching.domain;

import pairmatching.Course;
import pairmatching.Level;
import pairmatching.Mission;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PairMatchingResult {

    private final List<String> matchedPairs;
    private final Program program;

    public PairMatchingResult(List<String> matchedPairs, Program program) {
        this.matchedPairs = matchedPairs;
        this.program = program;
    }

    public boolean isEqualProgram(Program program) {
        return this.program.equals(program);
    }

    public List<String> getMatchedPairs() {
        return Collections.unmodifiableList(matchedPairs);
    }
}
