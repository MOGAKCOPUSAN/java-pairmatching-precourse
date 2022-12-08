package pairmatching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewNameReader {
    public static List<String> getCrewNames(String filePath) {
        List<String> crews = new ArrayList<>();
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines()
                    .forEach(crews::add);
            return crews;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
