package org.bananaLaba.ioc.simple.beanDefinition;

/**
 * A class that encapsulates a formal bean definition. Instances of this class can be used directly to register beans
 * on an IoC-container.
 * @author Judzin
 *
 */
// TODO: make class bean definitions certainly distinguishable - now it's only assumption that their construction
// data is null.
public class BeanDefinition {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A part of the bean's definition which is common for it's instances.
     */
    private BeanMetaData metaData;
    /**
     * A part of the bean's definition which specifies how it's instances are created.
     */
    private BeanInstantiationData instantiationData;
    /**
     * A part of the bean's definition which specifies it's state after instantiation.
     */
    private BeanPropertyData propertyData;

    // ========================================================================
    // Methods
    // ========================================================================
    public BeanMetaData getMetaData() {
        return this.metaData;
    }

    public void setMetaData(final BeanMetaData metaData) {
        this.metaData = metaData;
    }

    public BeanInstantiationData getInstantiationData() {
        return this.instantiationData;
    }

    public void setInstantiationData(final BeanInstantiationData instantiationData) {
        this.instantiationData = instantiationData;
    }

    public BeanPropertyData getPropertyData() {
        return this.propertyData;
    }

    public void setPropertyData(final BeanPropertyData propretyData) {
        this.propertyData = propretyData;
    }

    @Override
    public String toString() {
        return this.metaData.getName();
    }

}
