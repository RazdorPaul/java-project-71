package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Тестирование DiffData DTO")
class DiffDataTest {

    /**
     * Переменная содержит тестовый объект.
     */
    private DiffData primitives;
    /**
     * Переменная содержит тестовый объект.
     */
    private DiffData arrays;
    /**
     * Переменная содержит тестовый объект.
     */
    private DiffData objects;
    /**
     * Переменная содержит тестовый объект.
     */
    private DiffData diff;

    /**
     * Содержит тестовый список.
     */
    private final List<Integer> testList = List.of(10, 20, 30);
    /**
     * Содержит тестовый массив.
     */
    private final int[] testArray = {10, 20, 30};
    /**
     * Содержит тестовый примитив.
     */
    private final int testNumber = 10;


    @BeforeEach
    void setUp() {
        primitives = new DiffData("key",
                testNumber,
                'h',
                "changed");
        arrays = new DiffData("key",
                testArray,
                new String[]{"ab", "cd"},
                "changed");
        objects = new DiffData("key",
                testList,
                Map.of("key1", "ab",
                        "key2", "cd"),
                "changed");
        diff = new DiffData("key",
                1,
                1,
                "unchanged");
    }

    @Test
    @DisplayName("Проверка работы конструктора и геттеров")
    void testConstructorAndGetters() {
        assertAll(
                () -> assertEquals("key", primitives.getKey()),
                () -> assertEquals(testNumber, primitives.getOldValue()),
                () -> assertEquals('h', primitives.getNewValue()),
                () -> assertEquals("changed", primitives.getStatus())
        );
        assertAll(
                () -> assertEquals("key", arrays.getKey()),
                () -> assertArrayEquals(testArray,
                        (int[]) arrays.getOldValue()),
                () -> assertArrayEquals(new String[]{"ab", "cd"},
                        (String[]) arrays.getNewValue()),
                () -> assertEquals("changed", arrays.getStatus())
        );
        assertAll(
                () -> assertEquals("key", objects.getKey()),
                () -> assertEquals(testList,
                        objects.getOldValue()),
                () -> assertEquals(Map.of("key1", "ab",
                                          "key2", "cd"),
                        objects.getNewValue()),
                () -> assertEquals("changed", arrays.getStatus())
        );
    }

    @Test
    @DisplayName("equals() тест для одинаковых объектов")
    void testEqualsSame() {
        DiffData same = new DiffData("key",
                1,
                1,
                "unchanged");
        assertEquals(diff, same);
    }

    @Test
    @DisplayName("equals() тест для разных объектов")
    void testEqualsDifferent() {
        DiffData different = new DiffData("host",
                "hexlet.io",
                "google.com",
                "changed");
        assertNotEquals(diff, different);
    }

    @Test
    @DisplayName("hashCode() равны")
    void testHashCode() {
        DiffData same = new DiffData("key",
                1,
                1,
                "unchanged");
        assertEquals(diff.hashCode(), same.hashCode());
    }
}
