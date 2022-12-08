package pairmatching.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pairmatching.model.Course;
import pairmatching.model.Crew;

class CrewServiceTest {

    CrewService crewService;

    @BeforeEach
    void before() {
        crewService = new CrewService();
    }

    @ParameterizedTest
    @CsvSource({"FRONTEND", "BACKEND"})
    void crewTest(Course course) {
        List<Crew> crewsByCourse = crewService.findCrewsByCourse(course);

        assertThat(crewsByCourse.size()).isNotZero();
    }
}