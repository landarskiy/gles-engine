package org.bananaLaba.ioc.map;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.ioc.BeanContainer;

public class BeanMap implements BeanContainer {

    private Map<String, Object> map = new HashMap<>();

    public void putBean(final String name, final Object bean) {
        this.map.put(name, bean);
    }

    public void putBeans(final Map<String, Object> map) {
        this.map.putAll(map);
    }

    public void clearBeans() {
        this.map.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(final String name, final Class<T> type) {
        final Object bean = this.map.get(name);
        if (bean != null) {
            return (T) bean;
        } else {
            throw new IllegalStateException("A bean with name \"" + name + "\" not found!");
        }
    }

    @Override
    public Class<?> getBeanType(final String name) {
        final Object bean = this.map.get(name);
        if (bean != null) {
            return bean.getClass();
        } else {
            throw new IllegalStateException("A bean with name \"" + name + "\" not found!");
        }
    }

    @Override
    public boolean hasBean(final String name) {
        final Object bean = this.map.get(name);
        return (bean != null);
    }

}
