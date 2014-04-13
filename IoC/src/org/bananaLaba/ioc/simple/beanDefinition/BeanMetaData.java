package org.bananaLaba.ioc.simple.beanDefinition;

/**
 * A class which encapsulates a part of a bean definition that is common for all the bean instances.
 * @author Judzin
 *
 */
public class BeanMetaData {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The described bean name.
     */
    private String name;
    /**
     * The described bean type.
     */
    private Class<?> type;

    // ========================================================================
    // Methods
    // ========================================================================
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return this.type;
    }

    public void setType(final Class<?> type) {
        this.type = type;
    }

}
