package hexlet.code;


import java.util.Objects;

public final class DiffData {
    /**
     * Содержит ключ, для которого строится различие.
     */
    private final String key;
    /**
     * Содержит значение ключа из первого файла.
     */
    private final Object oldValue;
    /**
     * Содержит значение ключа из второго файла.
     */
    private final Object newValue;
    /**
     * Содержит статус различий для этого ключа.
     */
    private final String status;

    /**
     * Конструктор для создания узла - объекта DiffData.
     * @param keyNode - содержит ключ, для которого строится различие
     * @param oldValueNode - содержит значение ключа из первого файла
     * @param newValueNode - содержит значение ключа из второго файла
     * @param statusNode - содержит статус различий для этого ключа
     */
    public DiffData(final String keyNode,
                    final Object oldValueNode,
                    final Object newValueNode,
                    final String statusNode) {
        key = keyNode;
        status = statusNode;
        oldValue = oldValueNode;
        newValue = newValueNode;
    }

    /**
     * Геттер для поля класса key.
     * @return возвращает String, содержащий key узла.
     */
    public String getKey() {
        return key;
    }

    /**
     * Геттер для поля класса status.
     * @return возвращает Object, содержащий status узла.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Геттер для поля класса oldValue.
     * @return возвращает Object, содержащий oldValue.
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Геттер для поля класса newValue.
     * @return возвращает Object, содержащий newValue.
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Метод сравнивает два объекта типа DiffData.
     * @param obj - хранит передаваемый для сравнения объект.
     * @return
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DiffData that = (DiffData) obj;

        return Objects.equals(key, that.key)
                && Objects.equals(oldValue, that.oldValue)
                && Objects.equals(newValue, that.newValue)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, oldValue, newValue, status);
    }
}
