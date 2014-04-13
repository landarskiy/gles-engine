package org.bananaLaba.ioc.simple.builder;

import java.util.Collection;

import org.bananaLaba.ioc.simple.beanDefinition.BeanDefinition;

/**
 * A basic interface for objects that provide bean definition registering on an IoC-container.
 * @author Judzin
 *
 */
public interface BeanRegistry {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Registers a bean definition.
     * @param definition the bean definition
     */
    void registerDefinition(final BeanDefinition definition);

    /**
     * Registers the given bean definitions.
     * @param definitions the bean definitions
     */
    void registerDefinitions(final Collection<BeanDefinition> definitions);

}
