package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Gendiff {
    private Path filepath1;
    private Path filepath2;
    private String format;
    private String firstFile;
    private String secondFile;
    private JsonFactory factory;

    public Gendiff(String filepath1, String filepath2, String format) throws IOException {
        this.filepath1 = getAbsolute(filepath1);
        this.filepath2 = getAbsolute(filepath2);
        this.format = format;
        firstFile = getStringFile(this.filepath1);
        secondFile = getStringFile(this.filepath2);
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
        return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
    }

    public String getfirstFile() {
        return firstFile;
    }

    public String getSecondFile() {
        return secondFile;
    }
}
