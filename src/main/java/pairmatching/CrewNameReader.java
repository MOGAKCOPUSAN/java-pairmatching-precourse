package pairmatching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.model.Course;

public class CrewNameReader {
    private static final String BACKEND_CREW_FILE_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_FILE_PATH = "src/main/resources/frontend-crew.md";

    public static List<String> getCrewNames(Course course) {
        List<String> crews = new ArrayList<>();
        File file = getFileByCourse(course);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines()
                    .forEach(crews::add);
            return crews;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private static File getFileByCourse(Course course) {
        if (course == Course.BACKEND) {
            return new File(BACKEND_CREW_FILE_PATH);
        }
        return new File(FRONTEND_CREW_FILE_PATH);
    }
}
