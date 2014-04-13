package org.bananaLaba.ioc.injection;

/**
 * A class representing argument objects with all their properties directly mutable.
 * @author Judzin
 *
 * @param <T> the argument value type
 */
public class SimpleArgument<T> extends BaseArgument<T> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The current argument value.
     */
    private T value;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates an argument with its value and type hint both set to null and its index set to 0.
     */
    public SimpleArgument() {
    }

    /**
     * Creates an argument with the given value and type hint positioned at 0.
     * @param typeHint the type hint
     * @param value the value
     */
    public SimpleArgument(final Class<?> typeHint, final T value) {
        this.value = value;
        this.setTypeHint(typeHint);
    }

    /**
     * Creates an argument at position 0 with the given value. If the value is not null, then it's type is used as the
     * argument type hint.
     * @param value the value
     */
    public SimpleArgument(final T value) {
        this(value == null ? null : value.getClass(), value);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

}
