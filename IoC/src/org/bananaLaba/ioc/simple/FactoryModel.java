package org.bananaLaba.ioc.simple;

import org.bananaLaba.ioc.reflection.ReferenceResolver;
import org.bananaLaba.ioc.reflection.instantiator.InstanceFactory;

/**
 * An abstract extension class of the basic instantiation model that is oriented for bean creation through the other
 * beans - factories.
 * @author Judzin
 *
 */
public abstract class FactoryModel extends InstantiationModel<InstanceFactory<?>> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The factory bean name.
     */
    private String factoryBeanName;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates a factory model for the given factory bean.
     * @param factoryBeanName the factory bean name
     */
    public FactoryModel(final String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public Object getInstance(final ReferenceResolver<Object> referenceResolver) {
        this.prepareInstantiation(referenceResolver);
        final Object bean = super.getInstance(referenceResolver);
        this.finishInstantiation();

        return bean;
    }

    public void setFactoryBeanName(final String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    /**
     * An inner method that is called before trying to instantiate a bean. Can be used to retrieve unresolved factory
     * bean, for example.
     * @param referenceResolver the resolver for references in the enclosing IoC-container bean space
     */
    protected abstract void prepareInstantiation(final ReferenceResolver<Object> referenceResolver);

    /**
     * An inner method that is called after successful bean instantiation. Can be used to release some specific
     * resources associated with the factory bean, for example.
     */
    protected abstract void finishInstantiation();

}
