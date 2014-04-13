package org.bananaLaba.ioc.simple;

import org.bananaLaba.ioc.reflection.ArgumentManager;
import org.bananaLaba.ioc.reflection.Instantiator;
import org.bananaLaba.ioc.reflection.ReferenceResolver;

/**
 * A class which instances are responsible for one of the bean entities instantiation within an IoC-container.
 * @author Judzin
 *
 * @param <T> type of instantiator used to create bean instances
 */
public class InstantiationModel<T extends Instantiator<?>> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A manager object for instantiation arguments.
     */
    private ArgumentManager argumentManager;
    /**
     * The instantiator object.
     */
    private T instantiator;

    // ========================================================================
    // Methods
    // ========================================================================
    public ArgumentManager getArgumentManager() {
        return this.argumentManager;
    }

    public void setArgumentManager(final ArgumentManager argumentManager) {
        this.argumentManager = argumentManager;
    }

    public T getInstantiator() {
        return this.instantiator;
    }

    public void setInstantiator(final T instantiator) {
        this.instantiator = instantiator;
    }

    public Object getInstance(final ReferenceResolver<Object> referenceResolver) {
        return this.instantiator.getInstance(this.argumentManager.getArgumentValues(referenceResolver));
    }

}
