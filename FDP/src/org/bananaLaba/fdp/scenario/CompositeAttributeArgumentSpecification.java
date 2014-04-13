package org.bananaLaba.fdp.scenario;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompositeAttributeArgumentSpecification extends ProjectionArgumentSpecification {

    private Map<String, String> propertyNameMap = new HashMap<>();

    public Map<String, String> getPropertyNameMap() {
        return new HashMap<>(this.propertyNameMap);
    }

    public void addProperty(final String originalName, final String aliasName) {
        this.propertyNameMap.put(originalName, aliasName);
    }

    public void addProperty(final String name) {
        this.propertyNameMap.put(name, name);
    }

    public void addProperties(final Collection<String> names) {
        for (final String propertyName : names) {
            this.propertyNameMap.put(propertyName, propertyName);
        }
    }

    public void addProperties(final Map<String, String> aliasMap) {
        this.propertyNameMap.putAll(aliasMap);
    }

    public void clearProperties() {
        this.propertyNameMap.clear();
    }

}
