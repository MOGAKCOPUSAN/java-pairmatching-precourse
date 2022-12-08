package pairmatching;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CrewFileReader {

    public static List<String> backendCrewNameFileRead() {
        URL backendCrewsFileUrl = CrewFileReader.class.getClassLoader().getResource("backend-crew.md");
        Path backendCrewsFilePath = new File(backendCrewsFileUrl.getPath()).toPath();
        try {
            return Files.readAllLines(backendCrewsFilePath);
        } catch (IOException e) {
            throw new RuntimeException(ErrorConstants.ERROR_PREFIX + "백엔드 크루의 파일을 읽어올 수 없습니다.");
        }
    }

    public static List<String> frontendCrewNameFileRead() {
        URL frontendCrewsFileUrl = CrewFileReader.class.getClassLoader().getResource("frontend-crew.md");
        Path backendCrewsFilePath = new File(frontendCrewsFileUrl.getPath()).toPath();
        try {
            return Files.readAllLines(backendCrewsFilePath);
        } catch (IOException e) {
            throw new RuntimeException(ErrorConstants.ERROR_PREFIX + "프론트엔드 크루의 파일을 읽어올 수 없습니다.");
        }
    }
}
