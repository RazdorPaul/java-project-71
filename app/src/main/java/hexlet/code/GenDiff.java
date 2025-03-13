package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.FileUtils;

public class GenDiff {
    private Path path1;
    private Path path2;
    private Map<String, Object> fileMap1;
    private Map<String, Object> fileMap2;


    GenDiff(String filename1, String filename2) {
        path1 = FileUtils.getPath(filename1);
        path2 = FileUtils.getPath(filename2);
        fileMap1 = convertToMap(path1);
        fileMap2 = convertToMap(path2);
    }

    private Map<String, Object> convertToMap(Path path) {
        String file = FileUtils.readFile(path);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = null;
        try {
            result = mapper.readValue(file, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            System.out.println("Невозможно конвертировать строку в карту значений!");
        }
        return result;
    }

    public Map<String, Object> getMap1() {
        return fileMap1;
    }

    public Map<String, Object> getMap2() {
        return fileMap2;
    }
}