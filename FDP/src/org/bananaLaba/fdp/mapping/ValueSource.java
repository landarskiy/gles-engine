package org.bananaLaba.fdp.mapping;

public interface ValueSource<T> {

    Class<?> getTypeHint();

    T getValue();

    boolean isAvailable();

}
