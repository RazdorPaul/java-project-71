package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class Parser {

    private Parser() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     *
     * @param file - на вход принимается абсолютный путь к файлу
     * @return возвращается Map считанная из входного файла
     * @throws IOException
     */
    public static Map<String, Object> getMap(final Path file)
            throws IOException {
        ObjectMapper mapper;
        var readFile = getStringFile(file);
        if (getExtention(file).equals("json")) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }
        return mapper.readValue(readFile, new TypeReference<>() {
        });
    }

    private static String getStringFile(final Path path) throws IOException {
        return Files.readString(path).strip();
    }

    private static String getExtention(final Path path) {
        String filename = path.getFileName().toString();
        String extension = "";
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            extension = filename.substring(lastDot + 1);
        }
        return extension;
    }
}
