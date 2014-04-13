package org.bananaLaba.fdp.mapping;

import org.bananaLaba.fdp.XMLProcessorContext;
import org.bananaLaba.fdp.scenario.ContextReferenceType;
import org.bananaLaba.ioc.BeanContainer;

public class ContextReference extends XMLProcessorArgument<Object> {

    private BeanContainer container;
    private String beanName;
    private ContextReferenceType referenceType;

    public ContextReference(final ContextReferenceType type) {
        this.setReferenceType(type);
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(final String beanName) {
        this.beanName = beanName;
    }


    public ContextReferenceType getReferenceType() {
        return this.referenceType;
    }

    public void setReferenceType(final ContextReferenceType referenceType) {
        if (referenceType == null) {
            throw new IllegalArgumentException("Expected a not-null reference type!");
        }

        this.referenceType = referenceType;
    }

    @Override
    public Object getValue() {
        return this.container.getBean(this.beanName, this.getTypeHint());
    }

    @Override
    public boolean isAvailable() {
        return this.container.hasBean(this.beanName);
    }

    @Override
    public void bind(final XMLProcessorContext context) {
        if (this.referenceType == ContextReferenceType.BEAN) {
            this.container = context.getBeanContainer();
        } else {
            this.container = context.getTransientStore();
        }
    }

}
