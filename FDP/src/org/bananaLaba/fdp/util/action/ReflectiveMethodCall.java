package org.bananaLaba.fdp.util.action;

import java.lang.reflect.Method;

import org.bananaLaba.fdp.simple.MethodCall;
import org.bananaLaba.ioc.reflection.MethodInvoker;

public class ReflectiveMethodCall<T> extends MethodInvoker implements MethodCall<T> {

 // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates a method call with no method specified.
     */
    public ReflectiveMethodCall() {
    }

    /**
     * Creates a method call based on the given method.
     * @param method the method object
     */
    public ReflectiveMethodCall(final Method method) {
        super(method);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public Object perform(final T bean, final Object... values) {
        try {
            return this.invoke(bean, values);
        } catch (final Exception e) {
            throw new RuntimeException("Method call failed!", e);
        }
    }

}
