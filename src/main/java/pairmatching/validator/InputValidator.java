package pairmatching.validator;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

    public static final List<String> choices = Arrays.asList("1", "2", "3", "Q");
    public static final List<String> rematchings = Arrays.asList("네", "아니오");

    public static void checkChoice(String choice) {
        if (!choices.contains(choice)) {
            throw new IllegalArgumentException("입력을 잘못하였습니다. 확인해주세요");
        }
    }

    public static void checkRematching(String rematching) {
        if (!rematchings.contains(rematching)) {
            throw new IllegalArgumentException("입력을 잘못하였습니다. 확인해주세요");
        }
    }
}
