package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {
    /**
     * Поле для хранения строки.
     * Строка читается из первого входного файла
     */
    private final Path pathFirstFile;
    /**
     * Поле для хранения строки.
     * Строка читается из второго входного файла.
     */
    private final Path pathSecondFile;

    /**
     * Конструктор класса Differ.
     * @param filepath1 - имя первого файла.
     * @param filepath2 - имя второго файла.
     * Возможно исключение IOException.
     */
    public Differ(final String filepath1, final String filepath2) {
        pathFirstFile = getAbsolute(filepath1);
        pathSecondFile = getAbsolute(filepath2);
    }

    private Path getAbsolute(final String file) {
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
     * @param format -срока содержит имя желаемого формата для выходной строки
     * @return строка, содержащая различия в файлах
     */
    public String generate(final String format)
            throws IOException {
        //Формируем мапы из строк, полученных
        // в результате чтения входных файлов.
        var map1 = Parser.getMap(pathFirstFile);
        var map2 = Parser.getMap(pathSecondFile);
        //Создаем множество для хранения сортированного множества ключей
        var allKeys = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        //добавляем ключи из карт
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        ArrayList<DiffData> diff = new ArrayList<>();
        //обходим множество ключей
        for (String key : allKeys) {
            var value1 = map1.get(key);
            var value2 = map2.get(key);
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                diff.add(new DiffData(key, value1, value2, "removed"));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                diff.add(new DiffData(key, value1, value2, "added"));
            } else if (Objects.equals(value1, value2)) {
                diff.add(new DiffData(key, value1, value2, "unchanged"));
            } else {
                diff.add(new DiffData(key, value1, value2, "changed"));
            }
        }
        Formatter formatter = Formatter.of(format);
        return formatter.diffToString(diff);
    }
}
