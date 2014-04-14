package org.bananaLaba.fdp;

import java.util.Map;

public interface BeanCallBuilder {

    void addSimpleArgument(final String attributeName, final Class<?> type);

    void addSimpleArgument(final String attributeName, final Class<?> type, final Class<?> typeHint);

    void addProjectionArgument(final Class<?> beanType, final Map<String, String> attributeMapping);

    void addProjectionArgument(final Class<?> beanType, final Class<?> typeHint,
            final Map<String, String> attributeMapping);

    void addBeanArgument(final String beanName, final Class<?> typeHint);

    void addBeanArgument(final String beanName);

    void addStaticArgument(final Object value, final Class<?> typeHint);

    void addClassConstantArgument(final Class<?> sourceType, final String name, final Class<?> typeHint);

    void addStoreArgument(final String key, final Class<?> typeHint);

    void addStoreArgument(final String key);

    void setBeanName(final String name);

    void setMethodName(final String name);

    void clearArguments();

    void setSkippable(final boolean skippable);

    void commit();

}
