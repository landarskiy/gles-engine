package org.bananaLaba.ioc.bean;

/**
 * An enumeration of available bean scopes.
 * @author Judzin
 *
 */
public enum BeanScope {

    // ========================================================================
    // Values
    // ========================================================================
    /**
     * A scope for beans that are instantiated only once within an IoC-container configuration.
     */
    SINGLETON,
    /**
     * A scope for beans that are instantiated every time the're referred within an IoC-container configuration.
     */
    PROTOTYPE

}
