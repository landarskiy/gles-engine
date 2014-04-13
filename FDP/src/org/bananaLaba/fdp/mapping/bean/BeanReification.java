package org.bananaLaba.fdp.mapping.bean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bananaLaba.ioc.simple.util.ReflectionUtils;

public class BeanReification<T> implements BeanBuilder<T> {

    private Class<T> type;
    private Map<String, PropertyReification> propertyMap = new HashMap<>();
    private Map<String, Object> valueMap = new HashMap<>();

    public BeanReification(final Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Expected not null bean type!");
        } else if (type == Class.class) {
            throw new IllegalArgumentException("A standard bean cannot be of meta-class!");
        }

        this.type = type;
    }

    @Override
    public void setProperty(final String name, final Object value) {
        this.ensurePropertyLoaded(name);
        this.valueMap.put(name, value);
    }

    private void ensurePropertyLoaded(final String name) {
        if (!this.propertyMap.containsKey(name)) {
            final PropertyReification reification =
                    new PropertyReification(ReflectionUtils.getPropertySetter(name, this.type));
            this.propertyMap.put(name, reification);
        }
    }

    @Override
    public void setProperties(final Map<String, Object> valueMap) {
        final Set<Entry<String, Object>> mapEntries = valueMap.entrySet();
        for (final Entry<String, Object> mapEntry : mapEntries) {
            this.setProperty(mapEntry.getKey(), mapEntry.getValue());
        }
    }

    @Override
    public void clearProperties() {
        this.valueMap.clear();
    }

    @Override
    public T build() {
        T bean = null;
        try {
            bean = this.type.newInstance();
            final Set<Entry<String, Object>> valueEntries = this.valueMap.entrySet();
            for (final Entry<String, Object> valueEntry : valueEntries) {
                this.propertyMap.get(valueEntry.getKey()).getSetter().invoke(bean, valueEntry.getValue());
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return bean;
    }

    public Class<?> getPropertyType(final String name) {
        this.ensurePropertyLoaded(name);
        return this.propertyMap.get(name).getType();
    }

    private static class PropertyReification {

        private Method setter;
        private Class<?> type;

        public PropertyReification(final Method setter) {
            this.setter = setter;
            this.type = setter.getParameterTypes()[0];
        }

        public Method getSetter() {
            return this.setter;
        }

        public Class<?> getType() {
            return this.type;
        }

    }

}
