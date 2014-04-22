package org.bananaLaba.fdp.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bananaLaba.fdp.BeanCallBuilder;
import org.bananaLaba.fdp.scenario.ArgumentSpecification;
import org.bananaLaba.fdp.scenario.AttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.CallActionSpecification;
import org.bananaLaba.fdp.scenario.ClassConstantSpecification;
import org.bananaLaba.fdp.scenario.CompositeAttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.ContextReferenceType;
import org.bananaLaba.fdp.scenario.ReferenceArgumentSpecification;
import org.bananaLaba.fdp.scenario.StaticArgumentSpecification;

public abstract class SimpleBeanCallBuilder implements BeanCallBuilder {

    private List<ArgumentSpecification> argumentSpecifications = new ArrayList<>();

    private String beanName;
    private String methodName;
    private String resultKey;
    private ContextReferenceType source;
    private boolean skippable;

    @Override
    public void addSimpleArgument(final String attributeName, final Class<?> type, final Class<?> typeHint) {
        final AttributeArgumentSpecification specification = new AttributeArgumentSpecification();
        specification.setTargetType(type);
        specification.setTypeHint(typeHint);
        specification.setAttributeName(attributeName);

        this.argumentSpecifications.add(specification);
    }

    @Override
    public void setResultKey(final String key) {
        this.resultKey = key;
    }

    @Override
    public void addSimpleArgument(final String attributeName, final Class<?> type) {
        this.addSimpleArgument(attributeName, type, type);
    }

    @Override
    public void addProjectionArgument(final Class<?> beanType, final Map<String, String> attributeMapping) {
        this.addProjectionArgument(beanType, beanType, attributeMapping);
    }

    @Override
    public void addProjectionArgument(final Class<?> beanType, final Class<?> typeHint,
            final Map<String, String> attributeMapping) {
        final CompositeAttributeArgumentSpecification specification = new CompositeAttributeArgumentSpecification();
        specification.setTargetType(beanType);
        specification.setTypeHint(typeHint);
        specification.addProperties(attributeMapping);

        this.argumentSpecifications.add(specification);
    }

    private void addReferenceArgument(final String key, final Class<?> typeHint, final ContextReferenceType type) {
        final ReferenceArgumentSpecification specification = new ReferenceArgumentSpecification();
        specification.setKey(key);
        specification.setTypeHint(typeHint);
        specification.setSource(type);

        this.argumentSpecifications.add(specification);
    }

    @Override
    public void addBeanArgument(final String beanName, final Class<?> typeHint) {
        this.addReferenceArgument(beanName, typeHint, ContextReferenceType.BEAN);
    }

    @Override
    public void addBeanArgument(final String beanName) {
        this.addBeanArgument(beanName, null);
    }

    @Override
    public void addStaticArgument(final Object value, final Class<?> typeHint) {
        final StaticArgumentSpecification specification = new StaticArgumentSpecification();
        if ((value == null) && (typeHint == null)) {
            throw new IllegalArgumentException(
                    "Static arguments with both value and type hint set to null are not allowed!");
        }

        specification.setTypeHint(typeHint == null ? value.getClass() : typeHint);
        specification.setValue(value);

        this.argumentSpecifications.add(specification);
    }

    @Override
    public void addClassConstantArgument(final Class<?> sourceType, final String name, final Class<?> typeHint) {
        final ClassConstantSpecification specification = new ClassConstantSpecification();
        if (typeHint == null) {
            throw new IllegalArgumentException("Class constants must have type hint!");
        }

        specification.setTypeHint(typeHint);
        specification.setConstantName(name);
        specification.setSourceType(sourceType);

        this.argumentSpecifications.add(specification);
    }

    @Override
    public void addStoreArgument(final String key, final Class<?> typeHint) {
        this.addReferenceArgument(key, typeHint, ContextReferenceType.STORE);
    }

    @Override
    public void addStoreArgument(final String key) {
        this.addStoreArgument(key, null);
    }

    @Override
    public void setBean(final String name, final ContextReferenceType source) {
        this.beanName = name;
        this.source = source;
    }

    @Override
    public void setMethodName(final String name) {
        this.methodName = name;
    }

    @Override
    public void clearArguments() {
        this.argumentSpecifications.clear();
    }

    @Override
    public void setSkippable(final boolean skippable) {
        this.skippable = skippable;
    }

    @Override
    public void commit() {
        final CallActionSpecification callSpecification = new CallActionSpecification();
        callSpecification.setSkippable(this.skippable);
        callSpecification.setTargetId(this.beanName);
        callSpecification.setReferenceType(this.source);
        callSpecification.setMethodName(this.methodName);
        callSpecification.addArguments(new ArrayList<>(this.argumentSpecifications));
        callSpecification.setResultKey(this.resultKey);

        this.commitInternal(callSpecification);
    }

    protected abstract void commitInternal(final CallActionSpecification specification);

}
