package pairmatching;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CrewNameReaderTest {

    @DisplayName("파일에서 크루의 이름을 저장한다.")
    @ParameterizedTest
    @CsvSource({"src/main/resources/backend-crew.md", "src/main/resources/frontend-crew.md"})
    void backendTest(String filePath) {
        List<String> crewNames = CrewNameReader.getCrewNames(filePath);

        assertThat(crewNames.size()).isNotZero();
    }
}