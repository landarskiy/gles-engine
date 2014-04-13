package org.bananaLaba.fdp.mapping;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;

public class SimpleAttributeMap implements AttributeMap {

    private Map<String, String> valueMap = new HashMap<>();

    @Override
    public String getAttribute(final String name) {
        return this.valueMap.get(name);
    }

    public void setAttribute(final String name, final String value) {
        this.valueMap.put(name, value);
    }

    public void clearAttributes() {
        this.valueMap.clear();
    }

    @Override
    public boolean isPresent(final String name) {
        return this.valueMap.containsKey(name);
    }

}
