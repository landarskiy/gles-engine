package org.bananaLaba.ioc.reflection.instantiator;

import org.bananaLaba.ioc.reflection.Instantiator;
import org.bananaLaba.ioc.reflection.MethodInvoker;

/**
 * An implementation of instantiator for the case of factory.
 * @author Judzin
 *
 * @param <T> type of objects to instantiate
 */
public class InstanceFactory<T> extends MethodInvoker implements Instantiator<T> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The factory object.
     */
    private Object factory;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates an instantiator with no factory object specified.
     */
    public InstanceFactory() {
    }

    // ========================================================================
    // Methods
    // ========================================================================
    public void setFactoryObject(final Object object) {
        this.factory = object;
    }

    public Object getFactoryObject() {
        return this.factory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getInstance(final Object... arguments) {
        return (T) this.invoke(this.factory, arguments);
    }

}
