package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.Level;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MissionTest {

    @Test
    void 레벨별_미션_이름_맵_반환_기능() {
        List<String> level1Mission = Arrays.asList("자동차경주", "로또", "숫자야구게임");
        List<String> level2Mission = Arrays.asList("장바구니", "결제", "지하철노선도");
        List<String> level3Mission = Collections.emptyList();
        List<String> level4Mission = Arrays.asList("성능개선", "배포");
        List<String> level5Mission = Collections.emptyList();

        Map<Level, List<String>> missionsByLevel = Mission.getMissionByLevel();

        assertThat(missionsByLevel.get(Level.LEVEL1)).isEqualTo(level1Mission);
        assertThat(missionsByLevel.get(Level.LEVEL2)).isEqualTo(level2Mission);
        assertThat(missionsByLevel.get(Level.LEVEL3)).isEqualTo(level3Mission);
        assertThat(missionsByLevel.get(Level.LEVEL4)).isEqualTo(level4Mission);
        assertThat(missionsByLevel.get(Level.LEVEL5)).isEqualTo(level5Mission);
    }
}