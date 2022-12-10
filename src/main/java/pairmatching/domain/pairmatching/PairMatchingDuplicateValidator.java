package pairmatching.domain.pairmatching;

import pairmatching.ErrorConstants;
import pairmatching.domain.program.Level;
import pairmatching.domain.Crew;
import pairmatching.domain.crews.Crews;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PairMatchingDuplicateValidator {

    private final List<String> matchedPairs;
    private final Crews crews;
    private final Level level;

    private static final int GENERAL_CREWS_PAIR_SIZE = 2;
    private static final int LAST_ODD_CREWS_PAIR_SIZE = 3;
    private static final int CREW1_OF_PAIR_INDEX = 0;
    private static final int CREW2_OF_PAIR_INDEX = 1;
    private static final int CREW3_OF_PAIR_INDEX = 2;

    public PairMatchingDuplicateValidator(List<String> matchedPairs, Crews crews, Level level) {
        this.matchedPairs = matchedPairs;
        this.crews = crews;
        this.level = level;
    }

    public void validate() {
        checkDuplicate();
    }

    private void checkDuplicate() {
        for (String matchedPair : matchedPairs) {
            List<String> crewNamesOfPair = Arrays.stream(matchedPair.split(" : "))
                    .collect(Collectors.toList());
            if (crewNamesOfPair.size() == GENERAL_CREWS_PAIR_SIZE) {
                checkNotLastCrewsDuplicate(crewNamesOfPair);
            }
            if (crewNamesOfPair.size() == LAST_ODD_CREWS_PAIR_SIZE) {
                checkLastCrewsDuplicate(crewNamesOfPair);
            }
        }
    }

    private void checkNotLastCrewsDuplicate(List<String> crewNamesOfPair) {
        String crew1Name = crewNamesOfPair.get(CREW1_OF_PAIR_INDEX);
        String crew2Name = crewNamesOfPair.get(CREW2_OF_PAIR_INDEX);
        Crew crew1 = crews.findCrew(crew1Name);
        Crew crew2 = crews.findCrew(crew2Name);
        if (crew1.isAlreadyMatchingCrewByLevel(crew2, level)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX +
                    "같은 레벨에서 이미 매칭된 크루가 있어 페어 매칭을 재실행합니다.");
        }
    }

    private void checkLastCrewsDuplicate(List<String> crewNamesOfPair) {
        String crew1Name = crewNamesOfPair.get(CREW1_OF_PAIR_INDEX);
        String crew2Name = crewNamesOfPair.get(CREW2_OF_PAIR_INDEX);
        String crew3Name = crewNamesOfPair.get(CREW3_OF_PAIR_INDEX);
        Crew crew1 = crews.findCrew(crew1Name);
        Crew crew2 = crews.findCrew(crew2Name);
        Crew crew3 = crews.findCrew(crew3Name);
        if (crew1.isAlreadyMatchingCrewByLevel(crew2, level) ||
                crew1.isAlreadyMatchingCrewByLevel(crew3, level) ||
        crew2.isAlreadyMatchingCrewByLevel(crew3, level)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX +
                    "같은 레벨에서 이미 매칭된 크루가 있어 페어 매칭을 재실행합니다.");
        }
    }
}
