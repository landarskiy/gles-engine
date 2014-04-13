package org.bananaLaba.bootstrap.common;

public interface StandardFactory<S, R> {

    <Input extends S> R prepare(final Input specification);

}
