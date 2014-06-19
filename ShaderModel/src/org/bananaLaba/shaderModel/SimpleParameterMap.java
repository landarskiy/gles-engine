package org.bananaLaba.shaderModel;

import java.util.HashMap;
import java.util.Map;

public class SimpleParameterMap implements ParameterMap {

    private Map<String, Object> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getParameterValue(final String id, final Class<T> type) {
        if (this.map.containsKey(id)) {
            return (T) this.map.get(id);
        } else {
            throw new IllegalArgumentException("No such parameter with id \"" + id + "\"!");
        }
    }

    @Override
    public boolean isParameterPresent(final String id) {
        return this.map.containsKey(id);
    }

    public void clearParameters() {
        this.map.clear();
    }

    public void setParameter(final String id, final Object value) {
        this.map.put(id, value);
    }

    public void setParameters(final Map<String, Object> parameters) {
        this.map.putAll(parameters);
    }

    public void removeParameter(final String id) {
        this.map.remove(id);
    }

}
