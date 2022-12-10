package pairmatching.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pairmatching.domain.program.Course;
import pairmatching.domain.program.Level;

import static org.assertj.core.api.Assertions.assertThat;

class CrewTest {

    private Crew crew1;
    private Crew crew2;
    private Crew crew3;
    private Crew crew4;
    @BeforeEach
    void init() {
        crew1 = new Crew(Course.BACKEND, "백호");
        crew2 = new Crew(Course.BACKEND, "태웅");
        crew3 = new Crew(Course.BACKEND, "치수");
        crew4 = new Crew(Course.BACKEND, "태섭");
    }

    @Test
    void 크루_생성_기능() {
        assertThat(crew1).isNotNull();
        assertThat(crew2).isNotNull();
    }

    @Test
    void 추가하려는_크루가_레벨별_페어_매칭된_크루_목록에_이미_있는지_확인하는_기능() {
        crew1.putMatchedCrew(Level.LEVEL1, crew2);
        crew1.putMatchedCrew(Level.LEVEL1, crew3);

        assertThat(crew1.isAlreadyMatchingCrewByLevel(crew2, Level.LEVEL1)).isTrue();
        assertThat(crew1.isAlreadyMatchingCrewByLevel(crew3, Level.LEVEL1)).isTrue();
        assertThat(crew1.isAlreadyMatchingCrewByLevel(crew4, Level.LEVEL1)).isFalse();
        assertThat(crew1.isAlreadyMatchingCrewByLevel(crew2, Level.LEVEL4)).isFalse();
        assertThat(crew1.isAlreadyMatchingCrewByLevel(crew3, Level.LEVEL3)).isFalse();
    }
}