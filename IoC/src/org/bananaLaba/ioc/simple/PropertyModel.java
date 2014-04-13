package org.bananaLaba.ioc.simple;

import java.util.ArrayList;
import java.util.Collection;

import org.bananaLaba.ioc.reflection.ArgumentManager;
import org.bananaLaba.ioc.reflection.Injector;
import org.bananaLaba.ioc.reflection.ReferenceResolver;

/**
 * A class which instances are responsible for one of the bean entity state re-creation after its instantiation within
 * an IoC-container.
 * @author Judzin
 *
 */
public class PropertyModel {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A collection of property injections that re-create the bean state after its instantiation.
     */
    private Collection<InjectionHelper<Object>> injections = new ArrayList<>();

    // ========================================================================
    // Methods
    // ========================================================================
    public void addInjection(final Injector<Object> injector, final ArgumentManager argumentManager) {
        this.injections.add(new InjectionHelper<>(injector, argumentManager));
    }

    public void clearInjections() {
        this.injections.clear();
    }

    /**
     * Applies the property model to the given bean instance.
     * @param bean the bean instance
     * @param referenceResovler an resolver object for references to other beans that are used for injections
     */
    public void apply(final Object bean, final ReferenceResolver<Object> referenceResovler) {
        for (final InjectionHelper<Object> injection : this.injections) {
            injection.getInjector().apply(bean, injection.getArgumentManager().getArgumentValues(referenceResovler));
        }
    }

    // ========================================================================
    // Inner types
    // ========================================================================
    /**
     * An inner helper class which instances unite an injection technique with its argument manager.
     * @author Judzin
     *
     * @param <T> type of beans to which the injection is applicable
     */
    private static class InjectionHelper<T> {

        // ========================================================================
        // Fields
        // ========================================================================
        /**
         * An injector object that implements the injection technique.
         */
        private Injector<T> injector;
        /**
         * The injection argument manager.
         */
        private ArgumentManager argumentManager;

        // ========================================================================
        // Constructors
        // ========================================================================
        /**
         * Creates an injection helper from the given injector and argument manager.
         * @param injector the injector object implementing the injection technique
         * @param argumentManager the injection argument manager
         */
        public InjectionHelper(final Injector<T> injector, final ArgumentManager argumentManager) {
            this.injector = injector;
            this.argumentManager = argumentManager;
        }

        // ========================================================================
        // Methods
        // ========================================================================
        public Injector<T> getInjector() {
            return this.injector;
        }

        public ArgumentManager getArgumentManager() {
            return this.argumentManager;
        }

    }

}
