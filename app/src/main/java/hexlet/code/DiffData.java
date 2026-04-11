package hexlet.code;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public final class DiffData {
    private String key;
    private Object oldValue;
    private Object newValue;
    private String status;

}
