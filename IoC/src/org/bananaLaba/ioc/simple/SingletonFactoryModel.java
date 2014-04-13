package org.bananaLaba.ioc.simple;

import org.bananaLaba.ioc.reflection.ReferenceResolver;

/**
 * An implementation of factory model which uses a singleton factory bean.
 * @author Judzin
 *
 */
public class SingletonFactoryModel extends FactoryModel {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A flag that specifies if the factory singleton bean has been initialized.
     */
    private boolean initialized;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates a singleton factory model for the given factory bean.
     * @param factoryBeanName the factory bean name
     */
    public SingletonFactoryModel(final String factoryBeanName) {
        super(factoryBeanName);
    }

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    protected void prepareInstantiation(final ReferenceResolver<Object> referenceResolver) {
        if (!this.initialized) {
            this.getInstantiator().setFactoryObject(referenceResolver.resolve(this.getFactoryBeanName()));
            this.initialized = true;
        }
    }

    @Override
    protected void finishInstantiation() {
    }

}
