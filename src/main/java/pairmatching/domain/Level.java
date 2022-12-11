package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Level {

    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", Arrays.asList()),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Arrays.asList());

    private String name;
    private List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    // 추가 기능 구현
    public static List<Level> getAllLevels() {
        return Arrays.stream(values())
                .collect(Collectors.toList());
    }

    public static Level getLevel(String inputLevel, String inputMission) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(inputLevel))
                .filter(level -> level.missions.contains(inputMission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 레벨과 미션이 없습니다."));
    }

    public String getName() {
        return name;
    }

    public List<String> getMissions() {
        return missions;
    }
}