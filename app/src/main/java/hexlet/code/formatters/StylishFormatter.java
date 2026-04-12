package hexlet.code.formatters;

import hexlet.code.DiffData;

import java.util.List;

public class StylishFormatter implements Formatter {

    /**
     * Содержит текушее oldValue узла DiffData.
     */
    private String oldValue;
    /**
     * Содержит текушее newValue узла DiffData.
     */
    private String newValue;
    /**
     * Содержит текущий status узла DiffData.
     */
    private String status;
    /**
     * Содержит текущий key узла DiffData.
     */
    private String key;
    /**
     * Содержит строку с уже проверенными узлами.
     */
    private StringBuilder result = new StringBuilder();

    /**
     *
     * @param diffData - принимает список узлов DiffData.
     * @return String - приведенный к формату stylish список различий.
     */
    public String diffToString(final List<DiffData> diffData) {
        result.append("{\n");
        for (var node : diffData) {
            setValues(node);
            result.append("  ");
            if (status.equals(ADDED)) {
                result.append("+ ").append(key).append(": ").
                        append(newValue).
                        append("\n");
            } else if (node.getStatus().equals(REMOVED)) {
                result.append("- ").append(key).append(": ").
                        append(oldValue).
                        append("\n");
            } else if (status.equals(CHANGED)) {
                result.append("- ").append(key).append(": ").
                        append(oldValue).
                        append("\n");
                result.append("  + ").append(key).append(": ").
                        append(newValue).
                        append("\n");
            } else {
                result.append("  ").append(key).append(": ").
                        append(oldValue).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }

    private void setValues(final DiffData node) {
        if (node.getOldValue() != null) {
            oldValue = node.getOldValue().toString();
        } else {
            oldValue = "null";
        }
        if (node.getNewValue() != null) {
            newValue = node.getNewValue().toString();
        } else {
            newValue = "null";
        }
        status = node.getStatus();
        key = node.getKey();
    }
}
