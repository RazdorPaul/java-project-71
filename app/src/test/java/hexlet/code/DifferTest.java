package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class DifferTest {

    private  String expected;

    private String getPath(final String name) throws IOException {
        return "src/"
                + "test/"
                + "resources/"
                + "fixtures/"
                + name;
    }

    @BeforeEach
    public void init() {
        expected = """
            {
                aaa_very_early_key: should be first
              + added_key_completely_new: brand new key
                boolean_false: false
                boolean_true: true
              - CASE_INSENSITIVE_test: upper case
              + CASE_INSENSITIVE_test: LOWER CASE CHANGED
              - case_sensitive_Key: mixed case
              + case_sensitive_Key: MIXED CASE CHANGED
              - changed_boolean: true
              + changed_boolean: false
              - changed_number: 100
              + changed_number: 200
              - changed_string: old value
              + changed_string: new value
                null_value: null
                number_float: 3.14159
                number_int: 42
              - only_in_first: this is unique to first
              + only_in_second: this is unique to second
                same_boolean: false
                same_null: null
                same_number: 999
                same_string: identical
                string: Hello World
                zzz_very_late_key: should be last
            }""";

    }

    /**
     * Тест проверяет работу метода на двух json c удалением ключей.
     * При выводе ожидается лексикографически отсортированный список ключей.
     */
    @Test
    @DisplayName("измененные ключи помечаются -/+ в  двух строках")
    public void testDifferComplexJson() throws IOException {
        var file1 = getPath("complex1.json");
        var file2 = getPath("complex2.json");
        var diff = new Differ(file1, file2);
        var actual = diff.generate();
        assertEquals(expected, actual);
    }

    /**
     * Тест проверяет работу метода на двух yaml c удалением ключей.
     * При выводе ожидается лексикографически отсортированный список ключей.
     */
    @Test
    @DisplayName("измененные ключи помечаются -/+ в  двух строках")
    public void testDifferComplexYaml() throws IOException {
        var file1 = getPath("complex1.yaml");
        var file2 = getPath("complex2.yaml");
        var diff = new Differ(file1, file2);
        var actual = diff.generate();
        assertEquals(expected, actual);
    }
}
