package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
    }

    private static Path getAbsolute(final String file) {
        Path path;
        if (file.startsWith("~")) {
            path = Paths.get(System.getProperty("user.home"),
                    file.substring(1));
        } else {
            path = Paths.get(file);
        }
        if (!path.isAbsolute()) {
            path = path.toAbsolutePath();
        }
        return path.normalize();
    }

    /**
     * Метод генерации строки, содержащей различия в переданных файлах.
     * @param file1 - имя первого переданного файла
     * @param file2 - имя второго переданного файла
     * @param format -срока содержит имя желаемого формата для выходной строки
     * @return строка, содержащая различия в файлах
     */
    public static String generate(final String file1,
                                  final String file2,
                                  final String format)
                                    throws IOException {
        //Формируем мапы из строк, полученных
        // в результате чтения входных файлов.
        var map1 = Parser.getMap(getAbsolute(file1));
        var map2 = Parser.getMap(getAbsolute(file2));
        var diff = buildDiff(map1, map2);
        Formatter formatter = Formatter.of(format);
        return formatter.diffToString(diff);
    }

    /**
     * Метод генерации строки, содержащей различия в переданных файлах.
     * @param file1 - имя первого переданного файла
     * @param file2 - имя второго переданного файла
     * @return строка, содержащая различия в файлах
     */
    public static String generate(final String file1, final String file2)
            throws IOException {
        return generate(file1, file2, "stylish");
    }

    private static List<DiffData> buildDiff(final Map<String, Object> map1,
                                           final Map<String, Object> map2) {
        var diff = new ArrayList<DiffData>();
        //Создаем множество для хранения сортированного множества ключей
        var allKeys = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        //добавляем ключи из карт
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        //обходим множество ключей
        for (String key : allKeys) {
            var oldValue = map1.get(key);
            var newValue = map2.get(key);
            var status = "";
            List<DiffData> child = null;
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                status = Formatter.ADDED;
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                status = Formatter.REMOVED;
            } else if (Objects.deepEquals(oldValue, newValue)) {
                status = Formatter.UNCHANGED;
            } else {
                if (isMap(oldValue) && isMap(newValue)) {
                    child = buildDiff((Map) oldValue, (Map) newValue);
                    status = Formatter.NESTED;
                } else {
                    status = Formatter.CHANGED;
                }
            }
            diff.add(new DiffData(key, oldValue, newValue, status, child));
        }
        return diff;
    }

    private static boolean isMap(final Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Map;
    }
}
