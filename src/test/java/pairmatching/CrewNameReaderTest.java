package pairmatching;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pairmatching.model.Course;

class CrewNameReaderTest {

    @DisplayName("파일에서 크루의 이름을 저장한다.")
    @ParameterizedTest
    @CsvSource({"FRONTEND", "BACKEND"})
    void backendTest(Course course) {
        List<String> crewNames = CrewNameReader.getCrewNames(course);

        assertThat(crewNames.size()).isNotZero();
    }
}