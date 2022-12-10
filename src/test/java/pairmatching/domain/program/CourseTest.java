package pairmatching.domain.program;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pairmatching.ErrorConstants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @ParameterizedTest
    @ValueSource(strings = {"백앤드", "프론트앤드", "백", "프론트", "back", "front"})
    void 없는_과정을_입력하면_예외_처리(String courseName) {
        assertThatThrownBy(() -> Course.getCourse(courseName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.ERROR_PREFIX + "정확한 코스 이름을 입력해주세요.");
    }

}