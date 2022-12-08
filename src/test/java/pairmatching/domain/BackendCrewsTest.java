package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.Course;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BackendCrewsTest {

    @Test
    void 백엔드_크루_생성_기능() {
        List<Crew> generatedBackendCrews = Arrays.asList(
                new Crew(Course.BACKEND, "성훈"),
                new Crew(Course.BACKEND, "동기"),
                new Crew(Course.BACKEND, "종필")
        );
        BackendCrews backendCrews = new BackendCrews(generatedBackendCrews);

        assertThat(backendCrews).isNotNull();
    }
}