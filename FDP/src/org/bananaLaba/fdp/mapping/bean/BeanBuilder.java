package org.bananaLaba.fdp.mapping.bean;

import java.util.Map;

public interface BeanBuilder<T> {

    void setProperty(final String name, final Object value);

    void setProperties(final Map<String, Object> valueMap);

    void clearProperties();

    T build();

}
