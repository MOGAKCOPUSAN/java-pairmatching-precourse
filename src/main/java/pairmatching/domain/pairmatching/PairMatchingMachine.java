package pairmatching.domain.pairmatching;

import pairmatching.domain.program.Level;
import pairmatching.domain.Crew;
import pairmatching.domain.ShuffleMachine;
import pairmatching.domain.crews.Crews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PairMatchingMachine {

    private List<String> shuffledCrewNames;
    private final List<String> matchedPairs = new ArrayList<>();
    private final Crews crews;
    private final Level level;

    public PairMatchingMachine(Crews crews, Level level) {
        this.crews = crews;
        this.level = level;
    }

    public void generate() {
        shuffleCrewNames();
        if (isCrewNamesOdd()) {
            generatePairs();
            addLastCrew();
        }
        if (!isCrewNamesOdd()) {
            generatePairs();
        }
    }

    private void shuffleCrewNames() {
        shuffledCrewNames = ShuffleMachine.getShuffleResult(crews.getNames());
    }

    private void generatePairs() {
        List<Integer> pairMatchingIndexes = getPairMatchingIndexes();
        for (Integer pairMatchingIndex : pairMatchingIndexes) {
            addMatchedPairs(pairMatchingIndex);
        }
    }

    private List<Integer> getPairMatchingIndexes() {
        List<Integer> pairMatchingIndexes = IntStream.rangeClosed(0, shuffledCrewNames.size() - 1)
                .filter(value -> value % 2 != 0)
                .boxed()
                .collect(Collectors.toList());
        return pairMatchingIndexes;
    }

    private void addMatchedPairs(Integer pairMatchingIndex) {
        String matchedPair = shuffledCrewNames.get(pairMatchingIndex - 1) +
                " : " + shuffledCrewNames.get(pairMatchingIndex);
        matchedPairs.add(matchedPair);
    }

    private void addLastCrew() {
        int lastPairIndex = matchedPairs.size() - 1;
        String beforeLastPair = matchedPairs.get(lastPairIndex);
        int lastCrewIndex = shuffledCrewNames.size() - 1;
        String finalLastPair = beforeLastPair + " : " + shuffledCrewNames.get(lastCrewIndex);
        matchedPairs.remove(lastPairIndex);
        matchedPairs.add(finalLastPair);
    }


    public void confirmMatchedCrew() {
        if (isCrewNamesOdd()) {
            putMatchedCrew();
            putLastMatchedCrew();
        }
        if (!isCrewNamesOdd()) {
            putMatchedCrew();
        }
    }

    private void putMatchedCrew() {
        List<Integer> pairMatchingIndexes = getPairMatchingIndexes();
        for (Integer pairMatchingIndex : pairMatchingIndexes) {
            String crew1Name = shuffledCrewNames.get(pairMatchingIndex - 1);
            String crew2Name = shuffledCrewNames.get(pairMatchingIndex);
            confirmEachMatchedCrew(crew1Name, crew2Name);
        }
    }


    private void confirmEachMatchedCrew(String crew1Name, String crew2Name) {
        Crew crew1 = crews.findCrew(crew1Name);
        Crew crew2 = crews.findCrew(crew2Name);
        crew1.putMatchedCrew(level, crew2);
        crew2.putMatchedCrew(level, crew1);
    }

    private void putLastMatchedCrew() {
        String[] splitCrews = matchedPairs.get(matchedPairs.size() - 1).split(" : ");
        String crew1Name = splitCrews[0];
        String crew2Name = splitCrews[1];
        String crew3Name = splitCrews[2];
        confirmMatchedLastCrew(crew1Name, crew2Name, crew3Name);
    }

    private void confirmMatchedLastCrew(String crew1Name, String crew2Name, String crew3Name) {
        Crew crew1 = crews.findCrew(crew1Name);
        Crew crew2 = crews.findCrew(crew2Name);
        Crew crew3 = crews.findCrew(crew3Name);
        crew3.putMatchedCrew(level, crew1);
        crew3.putMatchedCrew(level, crew2);
        crew1.putMatchedCrew(level, crew3);
        crew2.putMatchedCrew(level, crew3);
    }


    private boolean isCrewNamesOdd() {
        return shuffledCrewNames.size() % 2 != 0;
    }

    public List<String> getMatchedPairs() {
        return Collections.unmodifiableList(matchedPairs);
    }
}
