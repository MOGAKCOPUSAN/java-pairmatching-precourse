package pairmatching.model;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LevelTest {

    @DisplayName("미션이름으로 레벨을 찾는다")
    @ParameterizedTest
    @CsvSource(value = {"자동차경주,LEVEL1","장바구니,LEVEL2","배포,LEVEL4"})
    void missionOf(String mission, Level level) {
        Level result = Level.missionOf(mission);

        assertThat(result).isEqualTo(level);
    }

}