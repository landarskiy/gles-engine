package org.bananaLaba.ioc.injection;

/**
 * A base class for argument objects that have mutable index.
 * @author Judzin
 *
 * @param <T> the argument value type
 */
public abstract class BaseArgument<T> implements Argument<T> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The current argument index.
     */
    private int index;
    /**
     * The argument type hint.
     */
    private Class<?> typeHint;

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public void setIndex(final int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public Class<?> getTypeHint() {
        return this.typeHint;
    }

    public void setTypeHint(final Class<?> typeHint) {
        this.typeHint = typeHint;
    }

}
