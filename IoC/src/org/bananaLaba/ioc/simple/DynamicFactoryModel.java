package org.bananaLaba.ioc.simple;

import org.bananaLaba.ioc.reflection.ReferenceResolver;

/**
 * An implementation of factory model which re-creates the factory beans for each call.
 * @author Judzin
 *
 */
public class DynamicFactoryModel extends FactoryModel {

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates a dynamic factory model for the given factory bean.
     * @param factoryBeanName the factory bean name.
     */
    public DynamicFactoryModel(final String factoryBeanName) {
        super(factoryBeanName);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    protected void prepareInstantiation(final ReferenceResolver<Object> referenceResolver) {
        this.getInstantiator().setFactoryObject(referenceResolver.resolve(this.getFactoryBeanName()));
    }

    @Override
    protected void finishInstantiation() {
        this.getInstantiator().setFactoryObject(null);
    }

}
