package pairmatching.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static List<String> read(String path, String filename) throws IOException {
        File file = new File(path + filename);

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> results = new ArrayList<>();

        String line = "";
        while( (line = bufferedReader.readLine()) != null ){
            results.add(line);
        }
        return results;
    }
}
