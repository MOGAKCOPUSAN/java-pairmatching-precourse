package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pairmatching.Course;
import pairmatching.Level;
import pairmatching.Mission;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PairMatchingHistoryTest {

    private PairMatchingResult pairMatchingResult;
    private PairMatchingHistory pairMatchingHistory;

    @BeforeEach
    void init() {
        List<String> matchedPairs = Arrays.asList("가나 : 다라", "마바 : 사아", "자차: 카타");
        Program program = new Program(Course.BACKEND, Level.LEVEL1, Mission.RACING_CAR);
        pairMatchingResult = new PairMatchingResult(matchedPairs, program);
        pairMatchingHistory = new PairMatchingHistory();
        pairMatchingHistory.save(pairMatchingResult);
    }

    @Test
    void 페어_매칭_결과_이미_있는지_확인_기능() {
        Program existProgram = new Program(Course.BACKEND, Level.LEVEL1, Mission.RACING_CAR);
        Program notExistProgram = new Program(Course.BACKEND, Level.LEVEL4, Mission.DISTRIBUTION);
        boolean expectedExists = pairMatchingHistory.isExists(existProgram);
        boolean expectedNotExists = pairMatchingHistory.isExists(notExistProgram);

        assertThat(expectedExists).isTrue();
        assertThat(expectedNotExists).isFalse();
    }

    @Test
    void 페어_매칭_결과_조회_기능() {
        List<String> matchedPairs = Arrays.asList("가나 : 다라", "마바 : 사아", "자차: 카타");
        Program program = new Program(Course.BACKEND, Level.LEVEL1, Mission.RACING_CAR);
        List<String> findMatchedPair = pairMatchingHistory.findMatchedPair(program);

        assertThat(findMatchedPair).isEqualTo(matchedPairs);
    }
}