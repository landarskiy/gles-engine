package org.bananaLaba.ioc.reflection;

/**
 * A basic interface for objects that resolve named references into some entities.
 * @author Judzin
 *
 * @param <T> type of entities that can be resolved using this resolver
 */
public interface ReferenceResolver<T> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Resolves the given reference.
     * @param name the reference name
     * @return the referred entity or null if the entity doesn't exist in scope of the resolver
     */
    T resolve(final String name);

}
