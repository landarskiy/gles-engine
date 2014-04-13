package org.bananaLaba.ioc.reflection;

import java.lang.reflect.Method;

/**
 * A base class for reflective method invoker objects.
 * @author Judzin
 *
 */
public abstract class MethodInvoker extends ReflectiveInvoker {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The method to invoke.
     */
    private Method method;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates a method invoker with no method specified.
     */
    public MethodInvoker() {
    }

    /**
     * Creates a method invoker for the given method.
     * @param method the method
     */
    public MethodInvoker(final Method method) {
        this.setMethod(method);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    public void setMethod(final Method method) {
        this.setHasVararg(method.isVarArgs());
        this.setArgumentCount(method.getParameterTypes().length);
        this.method = method;
    }

    public Method getMethod() {
        return this.method;
    }

    /**
     * Invokes the method associated with the invoker. The passed arguments are prepared for such special cases like
     * variable arguments methods automatically.
     * @param target the object on which to invoke the method
     * @param arguments the arguments to pass to the method
     * @return the method invocation result
     */
    protected Object invoke(final Object target, final Object... arguments) {
        try {
            return this.method.invoke(target, this.prepareArgumentValues(arguments));
        } catch (final Exception e) {
            throw new RuntimeException("Reflective invocation failed!", e);
        }
    }

}
