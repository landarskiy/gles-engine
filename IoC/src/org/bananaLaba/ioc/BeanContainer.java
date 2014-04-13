package org.bananaLaba.ioc;

/**
 * A basic interface for an IoC-container.
 * @author Judzin
 *
 */
public interface BeanContainer {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Gets an instance of bean with the given name.
     * @param name the bean name
     * @param type the desired bean type
     * @return a managed instance of the bean or null if the bean was not properly registered earlier
     */
    <T> T getBean(final String name, final Class<T> type);

    /**
     * Gets the actual class of the given bean.
     * @param name the bean name
     * @return the actual class of the bean
     */
    Class<?> getBeanType(final String name);

    /**
     * Checks if the container has a bean with the given name registered.
     * @param name the bean name
     * @return true if such bean is registered and false otherwise
     */
    boolean hasBean(final String name);

}
