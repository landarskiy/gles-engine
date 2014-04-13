package org.bananaLaba.bootstrap.common;

public interface Converter<X, Y> {

    Y convert(final X value);

}
