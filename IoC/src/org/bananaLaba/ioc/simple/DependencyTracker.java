package org.bananaLaba.ioc.simple;

import java.util.List;

/**
 * A basic interface for helper objects that to track bean dependencies and to find the right bean creation order.
 * @author Judzin
 *
 */
public interface DependencyTracker {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Gets a list of bean names in such order that state of the beans can be re-created correctly.
     * @param origin the name of the bean which is being created
     * @return a list of the bean names in the right order
     */
    List<String> getInstantiationChain(final String origin);

    /**
     * Ensures that the construction dependency link exists between the given beans.
     * @param source the name of the dependent bean
     * @param target the name of the dependency bean
     */
    void ensureConstructionDependency(final String source, final String target);

    /**
     * Ensures that the method dependency link exists between the given beans.
     * @param source the name of the dependent bean
     * @param target the name of the dependency bean
     */
    void ensureMethodDependency(final String source, final String target);

    /**
     * Ensures that the bean with the given name is registered on the tracker at least as independent one.
     * @param beanName the bean name
     */
    void ensureBeanExists(final String beanName);

}
