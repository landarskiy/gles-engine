package org.bananaLaba.fdp.mapping;

public abstract class BaseArgument<T> implements ValueSource<T> {

    private Class<?> typeHint;

    @Override
    public Class<?> getTypeHint() {
        return this.typeHint;
    }

    public void setTypeHint(final Class<?> typeHint) {
        this.typeHint = typeHint;
    }

}
