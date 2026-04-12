package hexlet.code.formatters;

import hexlet.code.DiffData;

import java.util.List;

public class StylishFormatter implements Formatter {

    private String oldValue;
    private String newValue;
    private String status;
    private String key;
    private StringBuilder result = new StringBuilder();

    public String diffToString(List<DiffData> diffData) {
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
            } else if (status.equals(CHANGED)){
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

    private void setValues(DiffData node) {
        if (node.getOldValue() != null){
            oldValue = node.getOldValue().toString();
        } else {
            oldValue = "null";
        }
        if (node.getNewValue() != null){
            newValue = node.getNewValue().toString();
        } else {
            newValue = "null";
        }
        status = node.getStatus();
        key = node.getKey();
    }
}

