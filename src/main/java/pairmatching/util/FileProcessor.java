package pairmatching.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static List<String> read(String path, String filename) {
        File file = new File(path + filename);

        BufferedReader bufferedReader = getBufferedReader(file);

        List<String> results = getResults(bufferedReader);
        return results;
    }

    private static List<String> getResults(BufferedReader bufferedReader) {
        List<String> results = null;
        try {
            results = readFile(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    private static BufferedReader getBufferedReader(File file) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    private static List<String> readFile(BufferedReader bufferedReader) throws IOException {
        List<String> contents = new ArrayList<>();

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            contents.add(line);
        }
        return contents;
    }
}
