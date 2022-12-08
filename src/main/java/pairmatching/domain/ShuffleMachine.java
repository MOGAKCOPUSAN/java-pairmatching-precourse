package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class ShuffleMachine {

    public static List<String> getShuffleResult(List<String> somethingToShuffle) {
        return Randoms.shuffle(somethingToShuffle);
    }
}
