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
        var builder = new StringBuilder();
        for (DiffData node : diff) {
            formatNode(node, "", builder);
        }
        if (!builder.isEmpty()
                && builder.charAt(builder.length() - 1) == '\n') {
            builder.setLength(builder.length() - 1);
        }
        return builder.toString();
    }

    private void formatNode(final DiffData node,
                            final String path,
                            final StringBuilder builder) {
        var currentPath = path.isEmpty() ? node.getKey() : path
                                                         + "."
                                                         + node.getKey();
        var oldValue = node.getOldValue();
        var newValue = node.getNewValue();
        var status = node.getStatus();
        if (status.equals(NESTED)) {
            for (DiffData child : node.getChild()) {
                formatNode(child, currentPath, builder);
            }
        } else if (status.equals(REMOVED)) {
            builder.append("Property '").
                    append(currentPath).
                    append("' was removed\n");
        } else if (status.equals(ADDED)) {
            builder.append("Property '").
                    append(currentPath).
                    append("' was added with value: ").
                    append(formatValue(newValue)).
                    append("\n");
        } else if (status.equals(CHANGED)) {
            builder.append("Property '").
                    append(currentPath).
                    append("' was updated. From ").
                    append(formatValue(oldValue)).
                    append(" to ").
                    append(formatValue(newValue)).
                    append("\n");
        }
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
