package org.bananaLaba.shaderModel.template.condition;

import org.bananaLaba.shaderModel.ParameterMap;

public class EnumCheckCondition<T extends Enum<T>> extends SingleTemplateCondition {

    private T literal;
    private Class<?> enumType;

    private EnumCheckCondition(final T literal) {
        if (literal == null) {
            throw new IllegalArgumentException("Expected a not-null enumeration literal!");
        }

        this.enumType = literal.getClass();
        this.literal = literal;
    }

    @Override
    public boolean check(final ParameterMap parameters) {
        return this.literal == parameters.getParameterValue(this.getParameterId(), this.enumType);
    }

    public static<E extends Enum<E>> EnumCheckCondition<E> create(final E literal) {
        return new EnumCheckCondition<E>(literal);
    }

}
