package org.bananaLaba.ioc.simple.builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.bean.ClassBeanBuilder;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;
import org.bananaLaba.ioc.simple.SimpleContainer;
import org.bananaLaba.ioc.simple.beanDefinition.BeanDefinition;

public class SimpleBeanContainerFactory implements BeanContainerFactory {

    private Map<String, BeanDefinition> definitions = new HashMap<>();
    private BeanRegistry registry = new BeanRegistry() {

        @Override
        public void registerDefinition(final BeanDefinition definition) {
            SimpleBeanContainerFactory.this.definitions.put(definition.getMetaData().getName(), definition);
        }

        @Override
        public void registerDefinitions(final Collection<BeanDefinition> definitions) {
            for (final BeanDefinition definition : definitions) {
                this.registerDefinition(definition);
            }
        }

    };

    @Override
    public ClassBeanBuilder getClassBeanBuilder() {
        return new SimpleClassBeanBuilder(this.registry);
    }

    @Override
    public ObjectBeanBuilder getObjectBeanBuilder() {
        return new SimpleObjectBeanBuilder(this.registry);
    }

    @Override
    public BeanContainer getContainerInstance() {
        final SimpleContainer container = new SimpleContainer();
        container.registerDefinitions(this.definitions.values());
        return container;
    }

    @Override
    public void removeAllDefinitions() {
        this.definitions.clear();
    }

    @Override
    public void removeDefinition(final String beanName) {
        this.definitions.remove(beanName);
    }

}
