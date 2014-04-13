package org.bananaLaba.ioc.simple;

import java.util.ArrayList;
import java.util.Collection;

import org.bananaLaba.ioc.injection.Argument;
import org.bananaLaba.ioc.simple.beanDefinition.BeanInstantiationData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanPropertyData;
import org.bananaLaba.ioc.simple.beanDefinition.InjectionData;

/**
 * An utility class with some useful methods for working with bean formal definitions.
 * @author Judzin
 *
 */
public final class DefinitionUtils {

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Just to make it impossible to get the class instances.
     */
    private DefinitionUtils() {
        throw new RuntimeException();
    }

    // ========================================================================
    // Static methods
    // ========================================================================
    /**
     * Retrieves a collection of names of beans that are necessary to instantiate a bean represented with the given
     * definition.
     * @param data the instantiation data from the bean definition
     * @return a collection of bean names
     */
    public static Collection<String> getConstructionDependencies(final BeanInstantiationData data) {
        final ArrayList<String> dependencies = new ArrayList<>();
        final String factoryBeanName = data.getFactoryBeanName();
        if (factoryBeanName != null) {
            dependencies.add(factoryBeanName);
        }

        final Collection<Argument<String>> references = data.getInjection().getReferences();
        for (final Argument<String> reference : references) {
            dependencies.add(reference.getValue());
        }
        return dependencies;
    }

    /**
     * Retrieves a collection of names of beans that are necessary to re-create state of a bean represented with the
     * given definition.
     * @param data the property data from the bean definition
     * @return a collection of bean names
     */
    public static Collection<String> getMethodDependencies(final BeanPropertyData data) {
        final ArrayList<String> dependencies = new ArrayList<>();
        for (final InjectionData injectionData : data.getInjections()) {
            final Collection<Argument<String>> references = injectionData.getArguments().getReferences();
            for (final Argument<String> reference : references) {
                dependencies.add(reference.getValue());
            }
        }
        return dependencies;
    }

}
