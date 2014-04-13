package org.bananaLaba.ioc.test;

import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;
import org.bananaLaba.ioc.injection.ArgumentUtils;
import org.bananaLaba.ioc.simple.builder.SimpleBeanContainerFactory;
import org.bananaLaba.ioc.test.SimpleContainerTest.DependentBean;
import org.bananaLaba.ioc.test.SimpleContainerTest.ManagedBean;

public class SimpleContainerFactoryTest {

    public static void main(final String[] arguments) {
        final BeanContainerFactory factory = new SimpleBeanContainerFactory();

        final ObjectBeanBuilder builder = factory.getObjectBeanBuilder();
        builder.setName("bean1");
        builder.setType(ManagedBean.class);
        builder.setPropertyInjection("string", ArgumentUtils.getSinglePrimitiveGroup("managedString"));
        builder.commit();

        builder.clearPropertyInjections();
        builder.setName("bean2");
        builder.setType(DependentBean.class);
        builder.setConstructionTechnique(ArgumentUtils.getSingleReferenceGroup("bean1"));
        builder.commit();

        final BeanContainer container = factory.getContainerInstance();

        System.out.println(container.getBean("bean2", Object.class));
    }

}
