package org.bananaLaba.ioc.test;

import org.bananaLaba.ioc.bean.BeanScope;
import org.bananaLaba.ioc.injection.ArgumentGroup;
import org.bananaLaba.ioc.injection.SimpleArgument;
import org.bananaLaba.ioc.simple.SimpleContainer;
import org.bananaLaba.ioc.simple.beanDefinition.BeanDefinition;
import org.bananaLaba.ioc.simple.beanDefinition.BeanInstantiationData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanMetaData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanPropertyData;

public class SimpleContainerTest {

    public static void main(final String[] arguments) {
        BeanDefinition definition = new BeanDefinition();

        BeanMetaData metaData = new BeanMetaData();
        metaData.setName("bean1");
        metaData.setType(ManagedBean.class);
        definition.setMetaData(metaData);

        BeanInstantiationData instantiationData = new BeanInstantiationData();
        instantiationData.setScope(BeanScope.SINGLETON);
        definition.setInstantiationData(instantiationData);

        BeanPropertyData propertyData = new BeanPropertyData();
        SimpleArgument<Object> argument = new SimpleArgument<Object>("managedString");
        argument.setTypeHint(String.class);
        ArgumentGroup injection = new ArgumentGroup();
        injection.addPrimitive(argument);
        propertyData.addInjection("setString", injection);
        definition.setPropertyData(propertyData);

        final SimpleContainer container = new SimpleContainer();
        container.registerDefinition(definition);

        definition = new BeanDefinition();

        metaData = new BeanMetaData();
        metaData.setName("bean2");
        metaData.setType(DependentBean.class);
        definition.setMetaData(metaData);

        instantiationData = new BeanInstantiationData();
        instantiationData.setScope(BeanScope.SINGLETON);
        injection = new ArgumentGroup();
        SimpleArgument<String> reference = new SimpleArgument<>(null, "bean1");
        injection.addReference(reference);
        instantiationData.setInjection(injection);
        definition.setInstantiationData(instantiationData);

        propertyData = new BeanPropertyData();
        definition.setPropertyData(propertyData);

        container.registerDefinition(definition);

        System.out.println(container.getBean("bean2", Object.class));
    }

    public static class ManagedBean {

        private String string;

        public String getString() {
            return this.string;
        }

        public void setString(final String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{string: ").append(this.string).append("}");


            return stringBuilder.toString();
        }

    }

    public static class DependentBean {

        private ManagedBean bean;

        public DependentBean(final ManagedBean bean) {
            this.bean = bean;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{bean: ").append(this.bean).append("}");

            return stringBuilder.toString();
        }

    }

}
