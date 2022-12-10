package pairmatching.domain.program;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pairmatching.ErrorConstants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @ParameterizedTest
    @ValueSource(strings = {"레벨0", "레벨6", "렙1"})
    void 없는_레벨을_입력하면_예외_처리(String levelName) {
        assertThatThrownBy(() -> Level.getLevel(levelName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.ERROR_PREFIX + "정확한 레벨을 입력해주세요.");
    }
}