package org.bananaLaba.fdp;

import java.util.Map;

public interface ScenarioBuilder {

    BeanCallBuilder getBeanCallBuilder();

    void clearBeanCalls();

    void commit();

    void setId(final String id);

    void addStoreAttributeAction(final String attributeName, final Class<?> type, final String storeKey);

    void addStoreProjectionAction(final Class<?> beanType, final Map<String, String> attributeMapping,
            final String storeKey);

    void addStoreBeanAaction(final String beanName, final String storeKey);

}
