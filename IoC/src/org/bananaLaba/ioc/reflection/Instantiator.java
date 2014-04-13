package org.bananaLaba.ioc.reflection;

/**
 * A basic interface for objects that instantiate some kind of other objects reflectively.
 * @author Judzin
 *
 * @param <T> type of objects to instantiate
 */
public interface Instantiator<T> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Constructs new object using the given arguments.
     * @param arguments the arguments
     * @return the instance
     */
    T getInstance(final Object... arguments);

}
