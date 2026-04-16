package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.DiffData;

import java.util.List;

public interface Formatter {

    /**
     * Константа содержит строковое представление статуса added.
     */
    String ADDED = "added";
    /**
     * Константа содержит строковое представление статуса removed.
     */
    String REMOVED = "removed";
    /**
     * Константа содержит строковое представление статуса changed.
     */
    String CHANGED = "changed";
    /**
     * Константа содержит строковое представление статуса unchanged.
     */
    String UNCHANGED = "unchanged";
    /**
     * Константа содержит строковое представление статуса nested.
     */
    String NESTED = "nested";

    /**
     * @param diff - принимает список узлов - объектов DiffData.
     * @return - приведенный в строку список различий
     */
    String diffToString(List<DiffData> diff) throws JsonProcessingException;

    /**
     * Метод выбора реализации в зависимости от переданного формата.
     * @param format - принимает формат вывода
     * @return возвращает реализацию Formatter
     */
    static Formatter of(String format) {
        switch (format) {
            case "plain":
                return new PlainFormatter();
            case "json":
                return new JsonFormatter();
            default:
                return new StylishFormatter();
        }
    }
}
