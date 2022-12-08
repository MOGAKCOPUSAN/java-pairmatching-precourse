package pairmatching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FunctionCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"4", "5", "R", "QQ"})
    void 기능_커맨드가_1이나_2나_3이나_Q가_아니면_예외_처리(String functionCommand) {
        assertThatThrownBy(() -> FunctionCommand.validateFunctionCommand(functionCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.ERROR_PREFIX + "기능 커맨드를 제대로 입력해주세요.");
    }
}