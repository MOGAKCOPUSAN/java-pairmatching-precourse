package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.Course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CrewTest {

    @Test
    void 크루_생성_기능() {
        Crew crew1 = new Crew(Course.BACKEND, "성훈");
        Crew crew2 = new Crew(Course.FRONTEND, "희선");

        assertThat(crew1).isNotNull();
        assertThat(crew2).isNotNull();
    }
}