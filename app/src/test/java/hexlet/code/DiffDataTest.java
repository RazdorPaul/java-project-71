package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование DiffData DTO")
class DiffDataTest {

    private DiffData diffData;

    @BeforeEach
    void setUp() {
        diffData = new DiffData("timeout", 50, 20, "changed");
    }

    @Test
    @DisplayName("Конструктор и геттеры работают корректно")
    void testConstructorAndGetters() {
        assertAll(
                () -> assertEquals("timeout", diffData.getKey()),
                () -> assertEquals(50, diffData.getOldValue()),
                () -> assertEquals(20, diffData.getNewValue()),
                () -> assertEquals("changed", diffData.getStatus())
        );
    }

    @Test
    @DisplayName("equals() работает для одинаковых объектов")
    void testEqualsSame() {
        DiffData same = new DiffData("timeout", 50, 20, "changed");
        assertEquals(diffData, same);
    }

    @Test
    @DisplayName("equals() работает для разных объектов")
    void testEqualsDifferent() {
        DiffData different = new DiffData("host", "hexlet.io", "google.com", "changed");
        assertNotEquals(diffData, different);
    }

    @Test
    @DisplayName("hashCode() консистентен")
    void testHashCodeConsistency() {
        DiffData same = new DiffData("timeout", 50, 20, "changed");
        assertEquals(diffData.hashCode(), same.hashCode());
    }

    @Test
    @DisplayName("Работа с null значениями")
    void testNullValues() {
        DiffData nullData = new DiffData(null, null, null, null);

        assertAll(
                () -> assertNull(nullData.getKey()),
                () -> assertNull(nullData.getOldValue()),
                () -> assertNull(nullData.getNewValue()),
                () -> assertNull(nullData.getStatus())
        );
    }

    @Test
    @DisplayName("Объект не равен null")
    void testNotEqualsNull() {
        assertNotEquals(null, diffData);
    }

    @Test
    @DisplayName("Объект не равен другому типу")
    void testNotEqualsDifferentType() {
        assertNotEquals("string", diffData);
    }
}