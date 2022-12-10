package pairmatching;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RematchCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"예", "1", "a", "1a"})
    void 재매칭_여부_네_아니오가_아니면_예외_처리(String rematchCommand) {
        assertThatThrownBy(() -> RematchCommand.getRematchCommand(rematchCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.ERROR_PREFIX + "재매칭 여부를 제대로 입력해주세요.");
    }
}