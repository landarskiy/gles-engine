package org.bananaLaba.ioc.simple;

public interface BeanCacheListener {

    void onSingletonCached(final String name);

    void onSingletonDetected(final String name);

}
