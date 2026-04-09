package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getMap(Path file) throws IOException {
        ObjectMapper mapper = null;
        var readFile = getStringFile(file);
        if (getExtention(file).equals("yaml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        }
        if ((getExtention(file).equals("json"))) {
            mapper = new ObjectMapper();
        }
        return mapper.readValue(readFile, new TypeReference<>() {
        });
    }

    private static String getStringFile(final Path path) throws IOException {
        return Files.readString(path).strip();
    }

    private static String getExtention(Path path) {
        String filename = path.getFileName().toString();
        String extension = "";
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            extension = filename.substring(lastDot + 1);
        }
        return extension;
    }
}