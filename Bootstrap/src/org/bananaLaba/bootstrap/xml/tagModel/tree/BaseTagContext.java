package org.bananaLaba.bootstrap.xml.tagModel.tree;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseTagContext implements TagContext {

    private Map<String, Object> parentAttributes = new HashMap<>();
    private Map<String, Object> propagatedAttributes = new HashMap<>();

    public BaseTagContext(final Map<String, Object> parentAttributes) {
        if (parentAttributes != null) {
            this.parentAttributes = parentAttributes;
        } else {
            this.parentAttributes = new HashMap<>();
        }
    }

    public Map<String, Object> getPropagatedAttributes() {
        return this.propagatedAttributes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public<T> T getPropagatedAttribute(final String name, final Class<T> type) {
        return (T) this.parentAttributes.get(name);
    }

    @Override
    public void propagateAttributeDown(final String name, final Object value) {
        this.propagatedAttributes.put(name, value);
    }

    @Override
    public abstract TagController getChildController(final String uri, final String name);

}
