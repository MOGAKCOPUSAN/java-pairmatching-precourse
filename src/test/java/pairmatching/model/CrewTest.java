package pairmatching.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CrewTest {

    @DisplayName("레벨을 기준으로 매칭된 적이 있으면 true , 아니면 false를 반환한다.")
    @Test
    void matchedTest() {
        Crew crew = new Crew(Course.BACKEND, "kim");
        List<Crew> crews = Arrays.asList(new Crew(Course.BACKEND, "han"), new Crew(Course.BACKEND, "lee"));
        crew.isMatchedCrew(crews, Level.LEVEL1);

        assertThat(crew.isMatchedCrew(crews, Level.LEVEL1)).isTrue();
    }

}