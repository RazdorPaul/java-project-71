package hexlet.code.formatters;

import hexlet.code.DiffData;

import java.util.List;

public interface Formatter {

    String ADDED = "added";
    String REMOVED = "removed";
    String CHANGED = "changed";
    String UNCHANGED = "unchanged";

    public String diffToString(List<DiffData> diff);
}