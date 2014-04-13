package org.bananaLaba.ioc.reflection;

/**
 * A basic interface for objects that makes some kind of injections on other objects.
 * @author Judzin
 *
 * @param <T> type of objects that are targets for injections
 */
public interface Injector<T> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Applies injection to the given object.
     * @param bean the target object
     * @param values the injection arguments
     */
    void apply(final T bean, final Object... values);

}
