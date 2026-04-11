package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     * @return строка, содержащая различия в файлах
     */
    public String generate()
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
        //создаем билдер для сборки результирующей строки
        var result = new StringBuilder("{\n");
        //обходим множество ключей
        for (String key : allKeys) {
            //создаем переменные для хранения значений, где ключом
            //выступает элемент созданного множества, а в переменные
            //записываются значения этого ключа из обеих карт
            var value1 = map1.get(key);
            var value2 = map2.get(key);
            //дальше собираем строку из пар обеих мап
            // по правилам в спецификациях задачи.
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(value1).
                        append("\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(value2)
                        .append("\n");
            } else if (Objects.equals(value1, value2)) {
                result.append("    ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
            } else {
                result.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                result.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(value2)
                        .append("\n");
            }
        }

        return result.append("}").toString();
    }
}
