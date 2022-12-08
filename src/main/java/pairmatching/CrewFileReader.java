package pairmatching;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CrewFileReader {

    public static List<String> backendCrewNameFileRead() throws IOException {
        URL backendCrewsFileUrl = CrewFileReader.class.getClassLoader().getResource("backend-crew.md");
        Path backendCrewsFilePath = new File(backendCrewsFileUrl.getPath()).toPath();
        return Files.readAllLines(backendCrewsFilePath);
    }

    public static List<String> frontendCrewNameFileRead() throws IOException {
        URL frontendCrewsFileUrl = CrewFileReader.class.getClassLoader().getResource("frontend-crew.md");
        Path backendCrewsFilePath = new File(frontendCrewsFileUrl.getPath()).toPath();
        return Files.readAllLines(backendCrewsFilePath);
    }
}
