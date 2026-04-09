package hexlet.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class DifferTest {

    private String getPath(final String name) throws IOException {
        return "src/"
                + "test/"
                + "resources/"
                + "fixtures/"
                + name;
    }

    /**
     * Тест проверяет работу метода на двух одинаковых json.
     * При выводе ожидается лексикографически отсортированный список ключей.
     */
    @Test
    public void testDifferSame() throws IOException {
        var file1 = getPath("simple1.json");
        var file2 = getPath("simple2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate();
        var expected = """
                {
                    code: 200
                    color: green
                    id: 101
                    message: Hello World
                    name: Paul
                    status: success
                }""";
        assertEquals(expected, actual);
    }

    /**
     * Тест проверяет работу метода на двух json c добавлением ключей.
     * При выводе ожидается лексикографически отсортированный список ключей.
     */
    @Test
    @DisplayName("Удаленные ключи помечаются -")
    public void testDifferDeleteKey() throws IOException {
        var file1 = getPath("deletekey1.json");
        var file2 = getPath("deletekey2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate();
        var expected = """
                {
                    code: 200
                    color: green
                    id: 101
                  - message: Hello World
                  - name: Paul
                    status: success
                }""";
        assertEquals(expected, actual);
    }

    /**
     * Тест проверяет работу метода на двух json c удалением ключей.
     * При выводе ожидается лексикографически отсортированный список ключей.
     */
    @Test
    @DisplayName("добавленные ключи помечаются +")
    public void testDifferAddKey() throws IOException {
        var file1 = getPath("addkey1.json");
        var file2 = getPath("addkey2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate();
        var expected = """
                {
                  + code: 200
                    color: green
                    id: 101
                  + message: Hello World
                  + name: Paul
                    status: success
                }""";
        assertEquals(expected, actual);
    }

    /**
     * Тест проверяет работу метода на двух json c удалением ключей.
     * При выводе ожидается лексикографически отсортированный список ключей.
     */

    @Test
    @DisplayName("измененные ключи помечаются -/+ в  двух строках")
    public void testDifferComplex() throws IOException {
        String str = "~/java/java-project-71/app/src/test/resources/fixtures/";
        var path1 = str + "complex1.json";
        var path2 = str + "complex2.json";
        var diff = new Differ(path1, path2);
        var actual = diff.generate();
        var expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(expected, actual);
    }
}
