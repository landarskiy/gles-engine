package org.bananaLaba.fdp.simple;

import org.bananaLaba.ioc.BeanContainer;

public interface ValueStore extends BeanContainer {

    void put(final String key, final Object value);

}
