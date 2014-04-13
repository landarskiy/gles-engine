package org.bananaLaba.ioc.simple.beanDefinition;

import org.bananaLaba.ioc.bean.BeanScope;
import org.bananaLaba.ioc.injection.ArgumentGroup;

/**
 * A class which encapsulates a part of a bean definition that describes how the bean instances are created.
 * @author Judzin
 *
 */
public class BeanInstantiationData {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * Name of a bean which plays role of a factory object for the described bean.
     * Is null when a constructor is used.
     */
    private String factoryBeanName;
    /**
     * Name of a method of the factory bean that produces instances of te described bean.
     */
    private String factoryMethodName;
    /**
     * Specification of arguments that must be passed to either the described bean constructor or it's factory method.
     */
    private ArgumentGroup injection = new ArgumentGroup();
    /**
     * The described bean scope.
     */
    private BeanScope scope = BeanScope.SINGLETON;

    // ========================================================================
    // Methods
    // ========================================================================
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    public void setFactoryBeanName(final String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getFactoryMethodName() {
        return this.factoryMethodName;
    }

    public void setFactoryMethodName(final String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public ArgumentGroup getInjection() {
        return this.injection;
    }

    public void setInjection(final ArgumentGroup injection) {
        this.injection = injection;
    }

    public BeanScope getScope() {
        return this.scope;
    }

    public void setScope(final BeanScope scope) {
        this.scope = scope;
    }

}
