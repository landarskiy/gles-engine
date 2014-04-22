package org.bananaLaba.fdp.simple;

public interface MethodCall<T> {

    Object perform(final T bean, final Object... values);

}
