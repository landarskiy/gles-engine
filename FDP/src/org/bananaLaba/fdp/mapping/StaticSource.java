package org.bananaLaba.fdp.mapping;

import org.bananaLaba.fdp.XMLProcessorContext;

public class StaticSource<T> extends XMLProcessorArgument<T> {

    private T value;

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public void bind(final XMLProcessorContext context) {
    }

}
