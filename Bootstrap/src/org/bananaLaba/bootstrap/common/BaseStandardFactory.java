package org.bananaLaba.bootstrap.common;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseStandardFactory<S, R> implements StandardFactory<S, R> {

    private final Map<Class<? extends S>, Builder<? extends S, R>> builders = new HashMap<>();

    protected<T extends S> void bindBuilder(final Class<T> specificationType, final Builder<T, R> builder) {
        this.builders.put(specificationType, builder);
    }

    protected void clearBuilders() {
        this.builders.clear();
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public<T extends S> R prepare(final T specification) {
        if (specification == null) {
            throw new IllegalArgumentException("Expected a not-null specification!");
        }

        final Class<?> specificationType = specification.getClass();
        final Builder builder = this.builders.get(specificationType);
        if (builder == null) {
            throw new IllegalArgumentException("Unsupported specification \"" + specificationType.getName()
                    + "\"!");
        }

        return (R) builder.build(specification);
    }


}
