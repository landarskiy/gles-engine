package org.bananaLaba.ioc.bean;

import org.bananaLaba.ioc.injection.ArgumentGroup;

public interface ObjectBeanBuilder extends BeanBuilder {

    void setScope(final BeanScope scope);

    /**
     * Sets the defined bean instantiation technique via one of its constructors.
     * Note that the ArgumentGroup object is NOT copied, but is taken by reference.
     * @param constructorArguments the constructor argument specification
     */
    void setConstructionTechnique(final ArgumentGroup constructorArguments);

    /**
     * Sets the defined bean instantiation technique via a factory bean managed within the same container.
     * Note that the ArgumentGroup object is NOT copied, but is taken by reference.
     * @param facotyrBeanName the factory bean name
     * @param factoryMethodName the factory bean method that returns the defined bean instances
     * @param factoryArguments an argument specification for the factory method
     */
    void setConstructionTechnique(final String facotyrBeanName, final String factoryMethodName,
            final ArgumentGroup factoryArguments);

    ObjectBeanBuilder getClone();

}
