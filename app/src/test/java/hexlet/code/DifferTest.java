package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private String readJson(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    private String getPath(String name) throws IOException {
        return "src/" +
                "main/" +
                "test/" +
                "resources/" +
                "fixtures/" +
                name;
    }

    @Test
    public void testDifferSame() throws IOException {
        var file1 = getPath("simple1.json");
        var file2 = getPath("simple2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate(readJson(file1), readJson(file2));
        var expected = "{\n" +
                "  \"status: success\",\n" +
                "  \"message\": \"Hello World\",\n" +
                "  \"code\": 200,\n" +
                "  \"id\": 101,\n" +
                "  \"name\": \"Paul\",\n" +
                "  \"color\": \"green\"\n" +
                "}";
        assertEquals(actual, expected);
    }

}