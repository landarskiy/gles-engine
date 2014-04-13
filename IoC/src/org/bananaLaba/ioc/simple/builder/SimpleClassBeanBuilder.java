package org.bananaLaba.ioc.simple.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bananaLaba.ioc.bean.ClassBeanBuilder;
import org.bananaLaba.ioc.injection.ArgumentGroup;
import org.bananaLaba.ioc.simple.beanDefinition.BeanDefinition;
import org.bananaLaba.ioc.simple.beanDefinition.BeanMetaData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanPropertyData;
import org.bananaLaba.ioc.simple.beanDefinition.InjectionData;

public class SimpleClassBeanBuilder implements ClassBeanBuilder {

    private BeanRegistry registry;

    private String beanName;
    private Class<?> beanType;

    private Collection<InjectionData> methodInjections = new ArrayList<>();
    private Map<String, ArgumentGroup> propertyInjections = new HashMap<>();

    public SimpleClassBeanBuilder(final BeanRegistry registry) {
        this.registry = registry;
    }

    public SimpleClassBeanBuilder(final SimpleClassBeanBuilder builder) {
        this(builder.registry);

        this.beanType = builder.beanType;

        this.methodInjections.addAll(builder.methodInjections);
        this.propertyInjections.putAll(builder.propertyInjections);
    }

    public BeanRegistry getRegistry() {
        return this.registry;
    }

    public void setRegistry(final BeanRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void setType(final Class<?> type) {
        this.beanType = type;
    }

    public Class<?> getType() {
        return this.beanType;
    }

    @Override
    public void setName(final String name) {
        this.beanName = name;
    }

    @Override
    public void clearMethodInjections() {
        this.methodInjections.clear();
    }

    @Override
    public void addMethodInjections(final Map<String, ArgumentGroup> injections) {
        final Set<Entry<String, ArgumentGroup>> injectionEntries = injections.entrySet();
        for (final Entry<String, ArgumentGroup> entry : injectionEntries) {
            this.addMethodInjection(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void addMethodInjection(final String methodName, final ArgumentGroup arguments) {
        if ((methodName == null) || (methodName.isEmpty())) {
            throw new IllegalArgumentException("Expected a non-empty method name for a method injection!");
        }

        if (arguments.getPrimitives().isEmpty() && arguments.getReferences().isEmpty()) {
            throw new IllegalArgumentException("Exprected non-empty argument group for a method injection!");
        }

        if (this.propertyInjections.containsKey(methodName)) {
            throw new IllegalStateException("The injection through the method \"" + methodName
                    + "\" overlaps with already registered property injection!");
        }

        this.methodInjections.add(new InjectionData(methodName, arguments));
    }

    @Override
    public void setPropertyInjections(final Map<String, ArgumentGroup> injections) {
        final Set<Entry<String, ArgumentGroup>> injectionEntries = injections.entrySet();
        for (final Entry<String, ArgumentGroup> entry : injectionEntries) {
            this.setPropertyInjection(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setPropertyInjection(final String name, final ArgumentGroup arguments) {
        if ((name == null) || (name.isEmpty())) {
            throw new IllegalArgumentException("Expected a non-empty property name!");
        }

        final int injectionArgumentCount = arguments.getPrimitives().size() + arguments.getReferences().size();
        if (injectionArgumentCount  != 1) {
            throw new IllegalArgumentException("Exprected exactly one argument for a property injection!");
        }

        final StringBuilder setterNameBuilder = new StringBuilder("set");
        setterNameBuilder.append(name.substring(0, 1).toUpperCase()).append(name.substring(1));

//        final String setterName = setterNameBuilder.toString();
//        if (this.methodInjections.containsKey(setterName)) {
//            throw new IllegalStateException("The property \"" + name
//                    + "\" injection overlaps with already registered method injection!");
//        }

        this.propertyInjections.put(setterNameBuilder.toString(), arguments);
    }

    @Override
    public void clearPropertyInjections() {
        this.propertyInjections.clear();
    }

    @Override
    public void commit() {
        this.registry.registerDefinition(this.assembleDefinition());
    }

    protected BeanDefinition assembleDefinition() {
        final BeanDefinition definition = new BeanDefinition();

        final BeanMetaData metaData = new BeanMetaData();
        metaData.setName(this.beanName);
        metaData.setType(this.beanType);
        definition.setMetaData(metaData);

        final BeanPropertyData propertyData = new BeanPropertyData();
        for (final InjectionData injection : this.methodInjections) {
            propertyData.addInjection(injection);
        }

        final Set<Entry<String, ArgumentGroup>> injectionEntries = this.propertyInjections.entrySet();
        for (final Entry<String, ArgumentGroup> entry : injectionEntries) {
            propertyData.addInjection(entry.getKey(), entry.getValue());
        }

        definition.setPropertyData(propertyData);

        return definition;
    }

}
