package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование DiffData DTO")
class DiffDataTest {

    private DiffData primitives;
    private DiffData arrays;
    private DiffData objects;
    private DiffData diff;


    @BeforeEach
    void setUp() {
        primitives = new DiffData("key", 60 , 'h', "changed");
        arrays = new DiffData("key",
                new int[]{1,2,3},
                new String[]{"ab", "cd"},
                "changed");
        objects = new DiffData("key",
                List.of(1, 2, 3),
                Map.of("key1", "ab",
                        "key2", "cd"),
                "changed");
        diff = new DiffData("key", 1, 1, "uncanged");
    }

    @Test
    @DisplayName("Проверка работы конструктора и геттеров")
    void testConstructorAndGetters() {
        assertAll(
                () -> assertEquals("key", primitives.getKey()),
                () -> assertEquals(60, primitives.getOldValue()),
                () -> assertEquals('h', primitives.getNewValue()),
                () -> assertEquals("changed", primitives.getStatus())
        );
        assertAll(
                () -> assertEquals("key", arrays.getKey()),
                () -> assertArrayEquals(new int[]{1,2,3},
                        (int[]) arrays.getOldValue()),
                () -> assertArrayEquals(new String[]{"ab", "cd"},
                        (String[]) arrays.getNewValue()),
                () -> assertEquals("changed", arrays.getStatus())
        );
        assertAll(
                () -> assertEquals("key", objects.getKey()),
                () -> assertEquals(List.of(1, 2, 3),
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
        DiffData same = new DiffData("key", 1, 1, "uncanged");
        assertEquals(diff, same);
    }

    @Test
    @DisplayName("equals() тест для разных объектов")
    void testEqualsDifferent() {
        DiffData different = new DiffData("host", "hexlet.io", "google.com", "changed");
        assertNotEquals(diff, different);
    }

    @Test
    @DisplayName("hashCode() равны")
    void testHashCode() {
        DiffData same = new DiffData("key", 1, 1, "uncanged");
        assertEquals(diff.hashCode(), same.hashCode());
    }
}