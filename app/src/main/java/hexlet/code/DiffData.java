package hexlet.code;


import java.util.Objects;

public final class DiffData {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public DiffData(String key,
                    Object oldValue,
                    Object newValue,
                    String status) {
        this.key = key;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getKey() {
        return key;
    }

    public String getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DiffData that = (DiffData) obj;

        return Objects.equals(key, that.key) &&
                Objects.equals(oldValue, that.oldValue) &&
                Objects.equals(newValue, that.newValue) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, oldValue, newValue, status);
    }
}
