package org.bananaLaba.ioc.simple.injectors;

import java.lang.reflect.Method;

import org.bananaLaba.ioc.reflection.Injector;
import org.bananaLaba.ioc.reflection.MethodInvoker;

/**
 * An implementation of injector based on a reflective method invocation.
 * @author Judzin
 *
 * @param <T> type of objects to apply injections to
 */
public class MethodInjector<T> extends MethodInvoker implements Injector<T> {

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates a method injector with no method specified.
     */
    public MethodInjector() {
    }

    /**
     * Creates a method injector based on the given method.
     * @param method the method object
     */
    public MethodInjector(final Method method) {
        super(method);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public void apply(final T bean, final Object... values) {
        try {
            this.invoke(bean, values);
        } catch (final Exception e) {
            throw new RuntimeException("Method injection failed!", e);
        }
    }

}
