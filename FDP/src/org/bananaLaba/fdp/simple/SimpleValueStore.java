package org.bananaLaba.fdp.simple;

import java.util.HashMap;
import java.util.Map;

public class SimpleValueStore implements ValueStore {

    private Map<String, Object> valueMap;

    public SimpleValueStore() {
        this(new HashMap<String, Object>());
    }

    public SimpleValueStore(final Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(final String name, final Class<T> type) {
        if (this.valueMap.containsKey(name)) {
            return (T) this.valueMap.get(name);
        } else {
            // TODO: throw a custom exception here.
            throw new RuntimeException("Stored variable \"" + name + "\" doesn't exist in the current scope!");
        }
    }

    @Override
    public Class<?> getBeanType(final String name) {
        if (this.valueMap.containsKey(name)) {
            return this.valueMap.get(name).getClass();
        } else {
            // TODO: throw a custom exception here.
            throw new RuntimeException("Stored variable \"" + name + "\" doesn't exist in the current scope!");
        }
    }

    @Override
    public boolean hasBean(final String name) {
        return this.valueMap.containsKey(name);
    }

    @Override
    public void put(final String key, final Object value) {
        this.valueMap.put(key, value);
    }

    public void clear() {
        this.valueMap.clear();
    }

}
