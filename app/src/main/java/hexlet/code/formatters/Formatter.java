package hexlet.code.formatters;

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
     * @param diff - принимает список узлов - объектов DiffData.
     * @return - приведенный в строку список различий
     */
    String diffToString(List<DiffData> diff);
}
