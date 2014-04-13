package org.bananaLaba.fdp.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.common.Converter;
import org.bananaLaba.fdp.XMLProcessorContext;
import org.bananaLaba.fdp.mapping.bean.BeanBuilder;

public class DataBeanSource<T> extends XMLProcessorArgument<T> {

    private AttributeMap attributeMap;
    private Map<String, String> aliasMap = new HashMap<>();
    private Map<String, Converter<String, Object>> converterMap = new HashMap<>();
    private BeanBuilder<T> beanBuilder;

    public BeanBuilder<T> getBeanBuilder() {
        return this.beanBuilder;
    }

    public void setBeanBuilder(final BeanBuilder<T> beanBuilder) {
        this.beanBuilder = beanBuilder;
    }

    public void addAttribute(final String originalName, final String aliasName,
            final Converter<String, Object> converter) {
        this.aliasMap.put(originalName, aliasName);
        this.converterMap.put(originalName, converter);
    }

    public void addAttribute(final String name, final Converter<String, Object> converter) {
        this.aliasMap.put(name, name);
        this.converterMap.put(name, converter);
    }

    public void clearAttributes() {
        this.aliasMap.clear();
        this.converterMap.clear();
    }

    @Override
    public T getValue() {
        this.beanBuilder.clearProperties();

        final Set<Entry<String, String>> aliasEntries = this.aliasMap.entrySet();
        for (final Entry<String, String> aliasEntry : aliasEntries) {
            final String attributeName = aliasEntry.getKey();
            final Object propertyValue = this.converterMap.get(attributeName)
                    .convert(this.attributeMap.getAttribute(attributeName));
            this.beanBuilder.setProperty(aliasEntry.getValue(), propertyValue);
        }

        return this.beanBuilder.build();
    }

    @Override
    public boolean isAvailable() {
        final Set<String> attributeNames = this.aliasMap.keySet();
        for (final String attributeName : attributeNames) {
            if (!this.attributeMap.isPresent(attributeName)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void bind(final XMLProcessorContext context) {
        this.attributeMap = context.getAttributeMap();
    }

}
