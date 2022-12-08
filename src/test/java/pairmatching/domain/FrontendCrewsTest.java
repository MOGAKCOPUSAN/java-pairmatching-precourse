package pairmatching.domain;

import org.junit.jupiter.api.Test;
import pairmatching.Course;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrontendCrewsTest {

    @Test
    void 프론트엔드_크루_생성_기능() {
        List<Crew> generatedFrontendCrews = Arrays.asList(
                new Crew(Course.FRONTEND, "희선"),
                new Crew(Course.FRONTEND, "호수"),
                new Crew(Course.BACKEND, "종혁")
        );
        FrontendCrews frontendCrews = new FrontendCrews(generatedFrontendCrews);

        assertThat(frontendCrews).isNotNull();
    }
}