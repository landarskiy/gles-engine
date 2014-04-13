package org.bananaLaba.ioc.reflection.instantiator;

import java.lang.reflect.Constructor;

import org.bananaLaba.ioc.reflection.Instantiator;
import org.bananaLaba.ioc.reflection.ReflectiveInvoker;

/**
 * An implementation of instantiator for a constructor case.
 * @author Judzin
 *
 * @param <T> type of objects to instantiate
 */
public class InstanceConstructor<T> extends ReflectiveInvoker implements Instantiator<T> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The constructor used to instantiate objects.
     */
    private Constructor<T> constructor;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates an instantiator with no constructor specified.
     */
    public InstanceConstructor() {
    }

    /**
     * Creates an instantiator based on the given constructor.
     * @param constructor the constructor
     */
    public InstanceConstructor(final Constructor<T> constructor) {
        this.setConstructor(constructor);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    public void setConstructor(final Constructor<T> constructor) {
        this.setHasVararg(constructor.isVarArgs());
        this.setArgumentCount(constructor.getParameterTypes().length);
        this.constructor = constructor;
    }

    public Constructor<T> getConstructor() {
        return this.constructor;
    }

    @Override
    public T getInstance(final Object... arguments) {
        try {
            return this.constructor.newInstance(this.prepareArgumentValues(arguments));
        } catch (final Exception e) {
            throw new RuntimeException("Instantiation failed!", e);
        }
    }

}
