package hexlet.code;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.FileUtils;

public class GenDiff {
    private final Path path1;
    private final Path path2;
    private final Map<String, Object> fileMap1;
    private final Map<String, Object> fileMap2;
    private final Map<String, Object> differs;


    GenDiff(String filename1, String filename2) {
        path1 = FileUtils.getPath(filename1);
        path2 = FileUtils.getPath(filename2);
        fileMap1 = convertToMap(path1);
        fileMap2 = convertToMap(path2);
        differs = createMapDiffers();
    }

    private Map<String, Object> convertToMap(Path path) {
        String file = FileUtils.readFile(path);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = null;
        try {
            result = mapper.readValue(file, new TypeReference<>() { });
        } catch (JsonProcessingException e) {
            System.out.println("Невозможно конвертировать строку в карту значений!");
        }
        return result;
    }

    private Map<String, Object> createMapDiffers() {
        LinkedHashMap<String, Object> diff = new LinkedHashMap<>();
        TreeSet<String> keys = new TreeSet<>(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());
        for (String key : keys) {
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (fileMap1.get(key).equals(fileMap2.get(key))) {
                    diff.put("  " + key, fileMap1.get(key));
                } else {
                    var key1 = "- " + key;
                    var key2 = "+ " + key;
                    diff.put(key1, fileMap1.get(key));
                    diff.put(key2, fileMap2.get(key));
                }
            }
            if (!fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                diff.put("+ " + key, fileMap2.get(key));
            }
            if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
                diff.put("- " + key, fileMap1.get(key));
            }
        }
        return diff;
    }

    public String generate() {
        String result = differs.keySet().
                        stream().
                        map(key -> key + ": " + differs.get(key) + "\n").
                        collect(Collectors.joining());
        return result;
    }
}
