package hexlet.code.formatters;

import hexlet.code.DiffData;

import java.util.List;

public class PlainFormatter implements Formatter {

    /**
     * Константа содержит строку-заменитель составного объекта.
     */
    private static final String COMPLEX_VALUE = "[complex value]";

    /**
     * Класс реализует интерфейс Formatter.
     * @param diff - принимает список узлов - объектов DiffData.
     * @return String, содержит различия в формате plain
     */
    @Override
    public String diffToString(final List<DiffData> diff) {
        var result = new StringBuilder();
        for (var node : diff) {
            var status = node.getStatus();
            if (!status.equals("unchanged")) {
                result.append("Property '").
                        append(node.getKey()).
                        append("' was ");
                if (status.equals(REMOVED)) {
                    result.append("removed\n");
                }
                if (status.equals(ADDED)) {
                    result.append("added with value: ").
                            append(formatValue(node.getNewValue())).
                            append("\n");
                }
                if (status.equals(CHANGED)) {
                    result.append("updated. From ").
                            append(formatValue(node.getOldValue())).
                            append(" to ").
                            append(formatValue(node.getNewValue())).
                            append("\n");
                }
            }
        }
        if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    private String formatValue(final Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Object[]
                || value instanceof java.util.Collection
                || value instanceof java.util.Map) {
            return COMPLEX_VALUE;
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();

    }
}
