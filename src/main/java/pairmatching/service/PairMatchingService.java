package pairmatching.service;

import pairmatching.*;
import pairmatching.domain.*;
import pairmatching.domain.crews.BackendCrews;
import pairmatching.domain.crews.Crews;
import pairmatching.domain.crews.FrontendCrews;
import pairmatching.domain.pairmatching.PairMatchingDuplicateValidator;
import pairmatching.domain.pairmatching.PairMatchingHistory;
import pairmatching.domain.pairmatching.PairMatchingMachine;
import pairmatching.domain.pairmatching.PairMatchingResult;
import pairmatching.domain.program.Course;
import pairmatching.domain.program.Level;
import pairmatching.domain.program.Mission;
import pairmatching.domain.program.Program;

import java.util.List;
import java.util.stream.Collectors;

public class PairMatchingService {

    private final PairMatchingHistory pairMatchingHistory;
    private BackendCrews backendCrews;
    private FrontendCrews frontendCrews;
    private Program program;
    private Course course;
    private Level level;
    private PairMatchingMachine pairMatchingMachine;
    private PairMatchingResult pairMatchingResult;

    private static final int PROGRAM_COURSE_INDEX = 0;
    private static final int PROGRAM_LEVEL_INDEX = 1;
    private static final int PROGRAM_MISSION_INDEX = 2;
    private static final long FIRST_PAIR_MATCHING_OF_LEVEL_COUNT = 0L;
    private static final int MAX_PAIR_MATCHING_TRY_COUNT = 3;

    private int pairMatchTryCount;

    public PairMatchingService() {
        initPairMatchTryCount();
        pairMatchingHistory = new PairMatchingHistory();
        generateCrews();
    }

    public void initPairMatchTryCount() {
        pairMatchTryCount = 0;
    }

    private void generateCrews() {
        List<String> backendCrewNames = CrewFileReader.backendCrewNameFileRead();
        backendCrews = generatedBackendCrewsByCrewNames(backendCrewNames);

        List<String> frontendCrewNames = CrewFileReader.frontendCrewNameFileRead();
        frontendCrews = generatedFrontendCrewsByCrewNames(frontendCrewNames);
    }

    public void generateProgram(List<String> programInfos) {
        course = Course.getCourse(programInfos.get(PROGRAM_COURSE_INDEX));
        level = Level.getLevel(programInfos.get(PROGRAM_LEVEL_INDEX));
        Mission mission = Mission.getMission(programInfos.get(PROGRAM_MISSION_INDEX));
        Mission.validateMission(level, mission);
        program =  new Program(course, level, mission);
    }

    private BackendCrews generatedBackendCrewsByCrewNames(List<String> crewNames) {
        List<Crew> generatedCrewsByCrewNames = crewNames.stream()
                .map(crewName -> new Crew(Course.BACKEND, crewName))
                .collect(Collectors.toList());
        return new BackendCrews(generatedCrewsByCrewNames);
    }

    private FrontendCrews generatedFrontendCrewsByCrewNames(List<String> crewNames) {
        List<Crew> generatedCrewsByCrewNames = crewNames.stream()
                .map(crewName -> new Crew(Course.BACKEND, crewName))
                .collect(Collectors.toList());
        return new FrontendCrews(generatedCrewsByCrewNames);
    }


    public boolean isResultAlreadyExist() {
        return pairMatchingHistory.isExists(program);
    }

    public void deleteExistResult() {
        pairMatchingHistory.delete(program);
    }

    public void pairMatch() {
        pairMatchTryCount++;
        validateOverTryCount();
        List<String> matchedPairs = generateMatchedPairs();
        if (course.equals(Course.BACKEND)) {
            validatePairMatchingDuplicate(matchedPairs, backendCrews);
        }
        if (course.equals(Course.FRONTEND)) {
            validatePairMatchingDuplicate(matchedPairs, frontendCrews);
        }
        confirmCrew();
    }

    private void validateOverTryCount() {
        if (pairMatchTryCount > MAX_PAIR_MATCHING_TRY_COUNT) {
            throw new IllegalStateException(ErrorConstants.ERROR_PREFIX + "3회 시도까지 매칭이 되지 않았습니다.");
        }
    }

    private List<String> generateMatchedPairs() {
        if (course.equals(Course.BACKEND)) {
            pairMatchingMachine = new PairMatchingMachine(backendCrews, level);
        }
        if (course.equals(Course.FRONTEND)) {
            pairMatchingMachine = new PairMatchingMachine(frontendCrews, level);
        }
        pairMatchingMachine.generate();
        return pairMatchingMachine.getMatchedPairs();
    }

    private void validatePairMatchingDuplicate(List<String> matchedPairs, Crews crews) {
        if (isNotFirstPairMatchingOfLevel()) {
            PairMatchingDuplicateValidator pairMatchingDuplicateValidator =
                    new PairMatchingDuplicateValidator(matchedPairs, crews, level);
            pairMatchingDuplicateValidator.validate();
        }
    }

    private void confirmCrew() {
        pairMatchingMachine.confirmMatchedCrew();
    }

    private boolean isNotFirstPairMatchingOfLevel() {
        return !(pairMatchingHistory.countByLevel(level) == FIRST_PAIR_MATCHING_OF_LEVEL_COUNT);
    }

    public void generatePairMatchingResult() {
        pairMatchingResult = new PairMatchingResult(pairMatchingMachine.getMatchedPairs(), program);
    }

    public List<String> getMatchedPairs() {
        return pairMatchingResult.getMatchedPairs();
    }

    public void savePairMatchingResult() {
        pairMatchingHistory.save(pairMatchingResult);
    }

    public void historyInitialize() {
        pairMatchingHistory.initalize();
    }

    public List<String> findHistory() {
        return pairMatchingHistory.findMatchedPair(program);
    }
}
