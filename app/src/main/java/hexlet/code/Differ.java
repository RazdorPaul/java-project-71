package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    public String generate(String file1, String file2) throws JsonProcessingException {
        //Формируем мапы из строк, полученных в результате чтения входных файлов.
        var map1 = getMap(file1);
        var map2 = getMap(file2);
        //Создаем множество для хранения сортированного множества ключей
        var allKeys = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        var result = new StringBuilder("{\n");

        for (String key : allKeys) {
            var value1 = map1.get(key);
            var value2 = map2.get(key);

            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (value1.equals(value2)) {
                result.append("    ").append(key).append(": ").append(value1).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }

        return result.append("}").toString();
    }
}
