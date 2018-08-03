package util.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放查询结果
 */
public class Result {

    private Map<String, Object> attrs = new HashMap<>();

    public Long getLong(String columns) {
        Object value = attrs.get(columns);
        return null != value ? (Long) value : null;
    }

    public String getString(String columns) {
        Object value = attrs.get(columns);
        return null != value ? value.toString() : null;
    }

    public Integer getInteger(String columns) {
        Object value = attrs.get(columns);
        return null != value ? (Integer) value : null;
    }

    public Object get(String key) {
        return attrs.get(key);
    }

    public void remove(String key) {
        attrs.remove(key);
    }

    public void put(String key , Object value) {
        attrs.put(key , value);
    }
}
