package org.bananaLaba.ioc;

public interface BeanContainerFactory extends BeanDefinitionRegistry {

    BeanContainer getContainerInstance();

}
