package pairmatching.domain.pairmatching;

import pairmatching.domain.program.Level;
import pairmatching.domain.program.Program;

import java.util.Collections;
import java.util.List;

public class PairMatchingResult {

    private final List<String> matchedPairs;
    private final Program program;

    public PairMatchingResult(List<String> matchedPairs, Program program) {
        this.matchedPairs = matchedPairs;
        this.program = program;
    }

    public Level getLevel() {
        return program.getLevel();
    }

    public boolean isEqualProgram(Program program) {
        return this.program.equals(program);
    }

    public List<String> getMatchedPairs() {
        return Collections.unmodifiableList(matchedPairs);
    }
}
