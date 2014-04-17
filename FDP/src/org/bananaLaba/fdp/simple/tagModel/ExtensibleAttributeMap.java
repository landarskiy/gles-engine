package org.bananaLaba.fdp.simple.tagModel;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;

public class ExtensibleAttributeMap implements AttributeMap {

    private AttributeMap originalMap;
    private Map<String, String> extensionMap = new HashMap<>();

    public ExtensibleAttributeMap() {
    }

    public void setOriginalMap(final AttributeMap originalMap) {
        this.originalMap = originalMap;
    }

    public void setAttribute(final String name, final String value) {
        if ((this.originalMap != null) && (this.originalMap.isPresent(name))) {
            // TODO: throw a custom exception here.
            throw new IllegalStateException("Attempt to hide an existing attribute \"" + name + "\"!");
        }

        this.extensionMap.put(name, value);
    }

    @Override
    public String getAttribute(final String name) {
        String value = this.extensionMap.get(name);
        if (value == null) {
            if (this.originalMap == null) {
                // TODO: throw a custom exception here.
                throw new IllegalStateException("An attribute \"" + name + "\" is absent!");
            } else {
                return this.originalMap.getAttribute(name);
            }
        } else {
            return value;
        }
    }

    @Override
    public boolean isPresent(final String name) {
        return this.extensionMap.containsKey(name) || this.originalMap.isPresent(name);
    }

    public void clearExtensionAttributes() {
        this.extensionMap.clear();
    }

}
