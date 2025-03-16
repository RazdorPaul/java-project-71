package hexlet.code.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String readFile(Path path) {
        String strings = null;
        try {
            strings =  Files.readString(path);
        } catch (IOException e) {
            System.out.println("File " + path + " does not exist!");
        }
        return strings;
    }

    public static Path getPath(String filename) {
        if (Paths.get(filename).isAbsolute()) {
            return Paths.get(filename);
        }
        String workPath = System.getProperty("user.dir");
        System.out.println(workPath);
        filename = workPath + "/" + filename;
        return Paths.get(filename).normalize();
    }
}
