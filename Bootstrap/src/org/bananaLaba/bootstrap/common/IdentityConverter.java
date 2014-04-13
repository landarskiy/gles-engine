package org.bananaLaba.bootstrap.common;

public class IdentityConverter<T> implements Converter<T, T> {

    private IdentityConverter() {
    }

    public static<X> Converter<X, X> prepare(final Class<X> type) {
        return new IdentityConverter<X>();
    }

    @Override
    public T convert(final T value) {
        return value;
    }

}
