package org.bananaLaba.ioc.injection;

/**
 * A basic interface for objects that are part of argument specification for some kind of communication with an
 * invokable entity.
 * @author Judzin
 *
 * @param <T> type of the value associated with the argument
 */
// TODO: may be it's not reasonable to identify an argument only with it's position? If so, then consider adding the
// second generic parameter for the identifier.
public interface Argument<T> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Gets the argument position within its enclosing group.
     * @return the index
     */
    int getIndex();

    /**
     * Sets the argument position within its enclosing group.
     * @return the index
     */
    void setIndex(final int index);

    /**
     * Gets a value currently associated with the argument.
     * @return
     */
    T getValue();

    /**
     * Gets a type to which the current argument value should be casted before usage.
     * @return the type
     */
    Class<?> getTypeHint();

}
