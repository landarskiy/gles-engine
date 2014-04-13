package org.bananaLaba.ioc.simple.builder;

import org.bananaLaba.ioc.bean.BeanScope;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;
import org.bananaLaba.ioc.injection.ArgumentGroup;
import org.bananaLaba.ioc.simple.beanDefinition.BeanDefinition;
import org.bananaLaba.ioc.simple.beanDefinition.BeanInstantiationData;

public class SimpleObjectBeanBuilder extends SimpleClassBeanBuilder implements ObjectBeanBuilder {

    private BeanScope beanScope = BeanScope.SINGLETON;

    private String factoryBeanName;
    private String factoryMethodName;
    private ArgumentGroup constructionArguments;

    public SimpleObjectBeanBuilder(final BeanRegistry registry) {
        super(registry);
    }

    public SimpleObjectBeanBuilder(final SimpleObjectBeanBuilder builder) {
        super(builder);

        this.beanScope = builder.beanScope;

        this.factoryBeanName = builder.factoryBeanName;
        this.factoryMethodName = builder.factoryMethodName;
        this.constructionArguments = builder.constructionArguments;
    }

    @Override
    public void setScope(final BeanScope scope) {
        this.beanScope = scope;
    }

    public BeanScope getBeanScope() {
        return this.beanScope;
    }

    @Override
    public void setConstructionTechnique(final ArgumentGroup constructorArguments) {
        this.constructionArguments = constructorArguments;
    }

    @Override
    public void setConstructionTechnique(final String facotyrBeanName, final String factoryMethodName,
            final ArgumentGroup factoryArguments) {
        this.factoryBeanName = facotyrBeanName;
        this.factoryMethodName = factoryMethodName;
        this.constructionArguments = factoryArguments;
    }

    @Override
    public ObjectBeanBuilder getClone() {
        return new SimpleObjectBeanBuilder(this);
    }

    @Override
    protected BeanDefinition assembleDefinition() {
        final BeanDefinition definition = super.assembleDefinition();

        final BeanInstantiationData instantiationData = new BeanInstantiationData();
        instantiationData.setScope(this.beanScope);
        instantiationData.setFactoryBeanName(this.factoryBeanName);
        instantiationData.setFactoryMethodName(this.factoryMethodName);
        if (this.constructionArguments != null) {
            instantiationData.setInjection(this.constructionArguments);
        }
        definition.setInstantiationData(instantiationData);

        return definition;
    }

}
