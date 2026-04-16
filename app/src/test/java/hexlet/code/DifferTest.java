package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {

    /**
     * Переменная для хранения эталонной строки stylish.
     */
    private String expectedStylish;
    /**
     * Переменная для хранения эталонной строки plain.
     */
    private String expectedPlain;
    /**
     * Переменная для хранения эталонной строки json.
     */
    private String expectedJson;

    private String getPath(final String name) throws IOException {
        return "src/"
                + "test/"
                + "resources/"
                + "fixtures/"
                + name;
    }

    /**
     * Инициализация ожидаемой тестовой строки.
     */
    @BeforeEach
    @SuppressWarnings("checkstyle:LineLength")
    public void init() throws IOException {
        var path = getPath("stylish.txt");
        expectedStylish = Files.readString(Paths.get(path)).
                replace("\r\n", "\n").
                replace("\r", "\n");
        path = getPath("plain.txt");
        expectedPlain = Files.readString(Paths.get(path)).
                replace("\r\n", "\n").
                replace("\r", "\n");
        path = getPath("json.txt");
        expectedJson = Files.readString(Paths.get(path)).
                replace("\r\n", "\n").
                replace("\r", "\n");
    }

    /**
     * Тест проверяет работу метода на двух json c объектами.
     * При выводе ожидается список различий в формате stylish.
     */
    @Test
    @DisplayName("измененные ключи помечаются -/+ в  двух строках")
    public void testDifferComplexStylishJson() throws IOException {
        var file1 = getPath("complex_nested1.json");
        var file2 = getPath("complex_nested2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("stylish");
        assertEquals(expectedStylish, actual);
    }

    /**
     * Тест проверяет работу метода на двух yaml с объектами.
     * При выводе ожидается список различий в формате stylish.
     */
    @Test
    @DisplayName("измененные ключи помечаются -/+ в  двух строках")
    public void testDifferComplexStylishYaml() throws IOException {
        var file1 = getPath("complex_nested1.yaml");
        var file2 = getPath("complex_nested2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("stylish");
        assertEquals(expectedStylish, actual);
    }

    /**
     * Тест проверяет работу метода на двух yml с объектами.
     * При выводе ожидается список различий в формате stylish.
     */
    @Test
    @DisplayName("измененные ключи помечаются -/+ в  двух строках")
    public void testDifferComplexStylishYml() throws IOException {
        var file1 = getPath("complex_nested1.yaml");
        var file2 = getPath("complex_nested2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("stylish");
        assertEquals(expectedStylish, actual);
    }

    /**
     * Тест проверяет работу метода на двух json c объектами.
     * При выводе ожидается список различий в формате plain.
     */
    @Test
    @DisplayName("неизмененные ключи не выводятся")
    public void testDifferComplexPlainJson() throws IOException {
        var file1 = getPath("complex_nested1.json");
        var file2 = getPath("complex_nested2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("plain");
        assertEquals(expectedPlain, actual);
    }

    /**
     * Тест проверяет работу метода на двух yaml с объектами.
     * При выводе ожидается список различий в формате plain.
     */
    @Test
    @DisplayName("неизмененные ключи не выводятся")
    public void testDifferComplexPlainYaml() throws IOException {
        var file1 = getPath("complex_nested1.yaml");
        var file2 = getPath("complex_nested2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("plain");
        assertEquals(expectedPlain, actual);
    }

    /**
     * Тест проверяет работу метода на двух yml с объектами.
     * При выводе ожидается список различий в формате plain.
     */
    @Test
    @DisplayName("неизмененные ключи не выводятся")
    public void testDifferComplexPlainYml() throws IOException {
        var file1 = getPath("complex_nested1.yaml");
        var file2 = getPath("complex_nested2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("plain");
        assertEquals(expectedPlain, actual);
    }

    /**
     * Тест проверяет работу метода на двух json c объектами.
     * При выводе ожидается список различий в формате json.
     */
    @Test
    @DisplayName("различия выводятся в json формате")
    public void testDifferComplexJsonJson() throws IOException {
        var file1 = getPath("complex_nested1.json");
        var file2 = getPath("complex_nested2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("json");
        assertEquals(expectedJson, actual);
    }

    /**
     * Тест проверяет работу метода на двух yaml с объектами.
     * При выводе ожидается список различий в формате json.
     */
    @Test
    @DisplayName("различия выводятся в json формате")
    public void testDifferComplexJsonYaml() throws IOException {
        var file1 = getPath("complex_nested1.yaml");
        var file2 = getPath("complex_nested2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("json");
        assertEquals(expectedJson, actual);
    }

    /**
     * Тест проверяет работу метода на двух yml с объектами.
     * При выводе ожидается список различий в формате json.
     */
    @Test
    @DisplayName("различия выводятся в json формате")
    public void testDifferComplexJsonYml() throws IOException {
        var file1 = getPath("complex_nested1.yaml");
        var file2 = getPath("complex_nested2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate("json");
        assertEquals(expectedJson, actual);
    }
}
