package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.DiffData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter implements Formatter {

    /**
     *
     * @param diff - принимает список узлов - объектов DiffData.
     * @return возвращает построенную json-строку.
     * @throws JsonProcessingException исключение библиотеки Jackson
     */
    @Override
    public String diffToString(final List<DiffData> diff)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().
                enable(SerializationFeature.INDENT_OUTPUT).
                configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        String result = mapper.writeValueAsString(listToMap(diff));
        return result + "\n";
    }

    private static Map<String, Object> listToMap(final List<DiffData> diff) {
        var nodes = new LinkedHashMap<String, Object>();
        for (var node: diff) {
            nodes.put(node.getKey(), getFinalValue(node));
        }
        return nodes;
    }

    private static Object getFinalValue(final DiffData node) {
        if (node.getStatus().equals(Formatter.REMOVED)) {
            return null;
        } else if (node.getStatus().equals(Formatter.NESTED)) {
            return listToMap(node.getChild());
        } else {
            return node.getNewValue();
        }
    }
}
