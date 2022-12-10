package pairmatching.domain.pairmatching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pairmatching.domain.Crew;
import pairmatching.domain.crews.BackendCrews;
import pairmatching.domain.crews.Crews;
import pairmatching.domain.crews.FrontendCrews;
import pairmatching.domain.program.Course;
import pairmatching.domain.program.Level;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PairMatchingMachineTest {

    private BackendCrews backendCrews;
    private FrontendCrews frontendCrews;

    @BeforeEach
    void init() {
        backendCrews = new BackendCrews(Arrays.asList(
                new Crew(Course.BACKEND, "백호"),
                new Crew(Course.BACKEND, "태웅"),
                new Crew(Course.BACKEND, "치수"),
                new Crew(Course.BACKEND, "태섭")));

        frontendCrews = new FrontendCrews(Arrays.asList(
                new Crew(Course.FRONTEND, "보노"),
                new Crew(Course.FRONTEND, "시저"),
                new Crew(Course.FRONTEND, "쉐리"),
                new Crew(Course.FRONTEND, "신디"),
                new Crew(Course.FRONTEND, "다비")
        ));
    }

    @Test
    void 총_인원_짝수일_때_페어_매칭_기능() {
        PairMatchingMachine pairMatchingMachine = new PairMatchingMachine(backendCrews, Level.LEVEL1);
        pairMatchingMachine.generate();
        List<String> afterMatchingResult = pairMatchingMachine.getMatchedPairs();
        String[] firstPair = afterMatchingResult.get(0).split(":");
        String[] lastPair = afterMatchingResult.get(1).split(":");

        assertThat(afterMatchingResult.size()).isNotEqualTo(0);
        assertThat(afterMatchingResult.get(0)).contains(":");
        assertThat(firstPair.length).isEqualTo(2);
        assertThat(lastPair.length).isEqualTo(2);
    }

    @Test
    void 총_인원_홀수일_때_페어_매칭_기능() {
        PairMatchingMachine pairMatchingMachine = new PairMatchingMachine(frontendCrews, Level.LEVEL1);
        pairMatchingMachine.generate();
        List<String> afterMatchingResult = pairMatchingMachine.getMatchedPairs();
        String[] firstPair = afterMatchingResult.get(0).split(":");
        String[] lastPair = afterMatchingResult.get(1).split(":");

        assertThat(afterMatchingResult.size()).isNotEqualTo(0);
        assertThat(afterMatchingResult.get(0)).contains(":");
        assertThat(firstPair.length).isEqualTo(2);
        assertThat(lastPair.length).isEqualTo(3);
    }
}