package org.bananaLaba.shaderModel.template.simple;

import org.bananaLaba.shaderModel.ParameterMap;

public class ConstantValue<T> implements ParameterValue<T> {

    private T value;

    public ConstantValue() {
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public T get(final ParameterMap map) {
        return this.value;
    }

    public static<C> ConstantValue<C> create(final C value) {
        final ConstantValue<C> constant = new ConstantValue<>();
        constant.setValue(value);

        return constant;
    }

}
