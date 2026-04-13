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
     * Переменная для хранения плоской эталонной строки.
     */
    private String expectedStylish;
    /**
     * Переменная для хранения эталонной строки с вложенностью.
     */
    private String expectedPlain;

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
        expectedStylish = """
            {
                aaa_first_key: should be first
              + added_key_completely_new: brand new key
                array_mixed: [1, two, true, null]
                array_numbers: [1, 2, 3, 4, 5]
              - array_of_objects: [{id=1, name=first}]
              + array_of_objects: [{id=1, name=first}, {id=2, name=second}]
                array_strings: [apple, banana, cherry]
                boolean: true
              - changed_array: [1, 2, 3]
              + changed_array: [1, 2, 3, 4, 5]
              - changed_boolean: true
              + changed_boolean: false
              - changed_number: 100
              + changed_number: 200
              - changed_object: {key=old, keep=same}
              + changed_object: {key=new, keep=same, extra=added field}
              - changed_string: old value
              + changed_string: new value
              - nested_object: {level1={level2={level3=deep value}}}
              + nested_object: {level1={level2={level3=deep value changed}}}
                null_value: null
                number: 42
              - only_in_first: this is unique to first
              + only_in_second: this is unique to second
                same_array: [10, 20, 30]
                same_boolean: false
                same_number: 999
                same_object: {a=1, b=2}
                same_string: identical
                simple_object: {name=John, age=30, city=New York}
                string: Hello World
                zzz_last_key: should be last
            }""";
        var path = getPath("out.txt");
        expectedPlain = Files.readString(Paths.get(path)).
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
}
