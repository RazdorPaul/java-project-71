package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    private final String firstFile;
    private final String secondFile;

    public Differ(String filepath1, String filepath2) throws IOException {
        firstFile = getStringFile(getAbsolute(filepath1));
        secondFile = getStringFile(getAbsolute(filepath2));
    }

    private Path getAbsolute(String file) {
        Path path;
        if (file.startsWith("~")) {
            path = Paths.get(System.getProperty("user.home"), file.substring(1));
        } else {
            path = Paths.get(file);
        }
        if (!path.isAbsolute()) {
            path = path.toAbsolutePath();
        }
        return path.normalize();
    }

    private String getStringFile(Path path) throws IOException {
        return Files.readString(path).strip();
    }

    public Map<String, Object> getMap(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() {
        });
    }

    public String getFirstFile() {
        return firstFile;
    }

    public String getSecondFile() {
        return secondFile;
    }
    public void generate() throws JsonProcessingException {
        var map1 = getMap(firstFile);
        var map2 = getMap(secondFile);
    }
}
