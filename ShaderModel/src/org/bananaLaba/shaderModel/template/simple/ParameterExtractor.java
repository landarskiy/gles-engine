package org.bananaLaba.shaderModel.template.simple;

import org.bananaLaba.shaderModel.ParameterMap;

public class ParameterExtractor<T> implements ParameterValue<T> {

    private String parameterId;
    private Class<T> parameterType;

    private ParameterExtractor(final String parameterId, final Class<T> parameterType) {
        if (parameterType == null) {
            throw new IllegalArgumentException("Expetced a not-null parameter type!");
        }

        this.parameterType = parameterType;
        this.parameterId = parameterId;
    }

    public String getParameterId() {
        return this.parameterId;
    }

    public void setParameterId(final String parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public T get(final ParameterMap map) {
        return map.getParameterValue(this.parameterId, this.parameterType);
    }

    public static<C> ParameterExtractor<C> create(final String parameterId, final Class<C> parameterType) {
        return new ParameterExtractor<>(parameterId, parameterType);
    }

}
