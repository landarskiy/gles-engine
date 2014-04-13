package org.bananaLaba.fdp.mapping;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.common.Converter;
import org.bananaLaba.fdp.XMLProcessorContext;

public class AttributeSource<T> extends XMLProcessorArgument<T> {

    private String attributeName;
    private AttributeMap attributeMap;
    private Converter<String, T> converter;

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(final String attributeName) {
        this.attributeName = attributeName;
    }

    public Converter<String, T> getConverter() {
        return this.converter;
    }

    public void setConverter(final Converter<String, T> converter) {
        this.converter = converter;
    }

    @Override
    public T getValue() {
        final String rawValue = this.attributeMap.getAttribute(this.attributeName);
        return this.converter.convert(rawValue);
    }

    @Override
    public boolean isAvailable() {
        return this.attributeMap.isPresent(this.attributeName);
    }

    @Override
    public void bind(final XMLProcessorContext context) {
        this.attributeMap = context.getAttributeMap();
    }

}
