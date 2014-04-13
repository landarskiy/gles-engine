package org.bananaLaba.ioc;

import org.bananaLaba.ioc.bean.ClassBeanBuilder;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;

public interface BeanDefinitionRegistry {

    ClassBeanBuilder getClassBeanBuilder();

    ObjectBeanBuilder getObjectBeanBuilder();

    /**
     * Removes all bean definitions previously committed by the associated builder objects.
     */
    void removeAllDefinitions();

    /**
     * Removes a bean definition committed by one of the associated builder objects under the given name. If the given
     * name is not currently used by any committed definitions, no actions will be performed.
     * @param beanName the bean name
     */
    void removeDefinition(final String beanName);

}
