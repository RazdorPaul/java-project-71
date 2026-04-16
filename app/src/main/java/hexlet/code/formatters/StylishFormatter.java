package hexlet.code.formatters;

import hexlet.code.DiffData;

import java.util.List;

public class StylishFormatter implements Formatter {

    /**
     * Константа содержит базовую ширину отсупа.
     */
    private static final int INDENT_SIZE = 4;

    /**
     * Константа содержит ширину сдвига для вывода знака изменения.
     */
    private static final int SIGN_OFFSET = 2;

    /**
     * Метод построения форматированной строки из объекта различий.
     * @param data - принимает список изменений - объектов DiffData.
     * @return возвращает результат - строку в формате stylish
     */
    @Override
    public String diffToString(final List<DiffData> data) {

        var builder = new StringBuilder("{\n");
        var levelNest = 1;
        for (var node : data) {
            formatNode(node, builder, levelNest);
        }
        builder.append("}");
        return builder.toString();
    }

    private void formatNode(final DiffData node,
                            final StringBuilder builder,
                            final int levelNest) {
        var status = node.getStatus();
        var key = node.getKey();
        var oldValue = node.getOldValue();
        var newValue = node.getNewValue();
        var unchangedPrefix = " ".repeat(levelNest * INDENT_SIZE);
        var addedPrefix = " ".
                repeat(levelNest * INDENT_SIZE - SIGN_OFFSET) + "+ ";
        var removedPrefix = " ".
                repeat(levelNest * INDENT_SIZE - SIGN_OFFSET) + "- ";
        if (!status.equals(Formatter.NESTED)) {
            if (status.equals(Formatter.REMOVED)) {
                builder.append(removedPrefix).
                        append(nodeToString(key, oldValue)).
                        append("\n");
            } else if (status.equals(Formatter.ADDED)) {
                builder.append(addedPrefix).
                        append(nodeToString(key, newValue)).
                        append("\n");
            } else if (status.equals(Formatter.CHANGED)) {
                builder.append(removedPrefix).
                        append(nodeToString(key, oldValue)).
                        append("\n").
                        append(addedPrefix).
                        append(nodeToString(key, newValue)).
                        append("\n");
            } else {
                builder.append(unchangedPrefix).
                        append(nodeToString(key, oldValue)).
                        append("\n");
            }
        } else {
            builder.append(unchangedPrefix).append(key).append(": {\n");
            for (var child : node.getChild()) {
                formatNode(child, builder, levelNest + 1);
            }
            builder.append(unchangedPrefix).append("}\n");
        }
    }

    private String nodeToString(final String key, final Object value) {
        if (value == null) {
            return key + ": null";
        }
        return key + ": " + value.toString();
    }
}
