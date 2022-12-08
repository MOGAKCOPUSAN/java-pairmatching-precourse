package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShuffleMachineTest {

    @Test
    void 랜덤_셔플_기능() {
        String[] rawCrewNames = {"성훈", "종필", "도현", "병우", "민지", "재희", "새정"};
        List<String> crewNames = Arrays.asList(rawCrewNames);
        List<String> shuffleResult = ShuffleMachine.getShuffleResult(crewNames);

        assertThat(shuffleResult).isNotSameAs(crewNames);
        assertThat(shuffleResult).contains(rawCrewNames);
    }
}