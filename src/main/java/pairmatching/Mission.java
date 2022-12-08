package pairmatching.domain;

import pairmatching.ErrorConstants;
import pairmatching.Level;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Mission {
    RACING_CAR(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL_GAME(Level.LEVEL1, "숫자야구게임"),
    SHOPPING_BASKET(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_MAP(Level.LEVEL2, "지하철노선도"),
    PERFORMANCE_IMPROVEMENTS(Level.LEVEL4, "성능개선"),
    DISTRIBUTION(Level.LEVEL4, "배포");

    private Level level;
    private String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public static Map<Level, List<String>> getMissionByLevel() {
        Map<Level, List<String>> missionByLevelMap = new EnumMap<Level, List<String>>(Level.class);
        for (Level level : Level.values()) {
            List<String> missionByLevel = Arrays.stream(values())
                    .filter(mission -> mission.level == level)
                    .map(value -> value.name)
                    .collect(Collectors.toList());
            missionByLevelMap.put(level, missionByLevel);
        }
        return missionByLevelMap;
    }

    public static Mission getMission(String missionName) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(missionName))
                .findAny()
                .orElseThrow(() ->
                        new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "정확한 미션 이름을 입력해주세요."));
    }
}
