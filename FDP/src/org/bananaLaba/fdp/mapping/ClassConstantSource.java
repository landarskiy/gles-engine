package org.bananaLaba.fdp.mapping;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.fdp.XMLProcessorContext;

public class ClassConstantSource extends XMLProcessorArgument<Object> {

    private String attributeName;
    private Class<?> sourceType;
    private AttributeMap attributeMap;

    @Override
    public void bind(final XMLProcessorContext context) {
        this.attributeMap = context.getAttributeMap();
    }

    @Override
    public Object getValue() {
        final String constantName = this.attributeMap.getAttribute(this.attributeName);
        return ConversionUtils.getConsant(constantName, this.sourceType);
    }

    @Override
    public boolean isAvailable() {
        return this.attributeMap.isPresent(this.attributeName);
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(final String attributeName) {
        this.attributeName = attributeName;
    }

    public Class<?> getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(final Class<?> sourceType) {
        this.sourceType = sourceType;
    }

}
