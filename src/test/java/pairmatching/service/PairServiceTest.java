package pairmatching.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.model.Pair;

class PairServiceTest {

    PairService pairService;

    @BeforeEach
    void before() {
        pairService = new PairService();
    }

    @DisplayName("크루의 수가 홀수일 때 마지막 페어는 3명이다")
    @Test
    void oddCrewTest() {
        List<Pair> pairs = pairService.matchCrew(
                Arrays.asList(new Crew(Course.BACKEND, "kim"), new Crew(Course.BACKEND, "han"),
                        new Crew(Course.BACKEND, "park")), "자동차경주");

        assertThat(pairs.size()).isEqualTo(1);
    }

    @DisplayName("크루의 수가 짝수일 때 마지막 페어는 2명이다")
    @Test
    void evenCrewTest() {
        List<Pair> pairs = pairService.matchCrew(
                Arrays.asList(new Crew(Course.BACKEND, "kim"), new Crew(Course.BACKEND, "han"),
                        new Crew(Course.BACKEND, "park"), new Crew(Course.BACKEND, "lee")), "자동차경주");

        assertThat(pairs.size()).isEqualTo(2);
    }

}