package org.bananaLaba.ioc.test;

import org.bananaLaba.ioc.reflection.instantiator.InstanceConstructor;
import org.bananaLaba.ioc.reflection.instantiator.InstanceFactory;
import org.bananaLaba.ioc.test.InjectionTest.TestBean;

public class InstantiatorTest {

    public static void main(final String[] arguments) throws Exception {
        final InstanceConstructor<TestBean> constructor = new InstanceConstructor<>(TestBean.class.getConstructor());
        final TestBean bean1 = constructor.getInstance();
        bean1.setField("bean1");
        System.out.println(bean1.getField());

        final InstanceFactory<TestBean> factory = new InstanceFactory<>();
        factory.setFactoryObject(new Factory());
        factory.setMethod(Factory.class.getMethod("produce", String.class));
        final TestBean bean2 = factory.getInstance("bean2");
        System.out.println(bean2.getField());

        final InstanceFactory<TestBean> staticFactory = new InstanceFactory<>();
        staticFactory.setFactoryObject(Factory.class);
        staticFactory.setMethod(Factory.class.getMethod("getBean", String.class));
        final TestBean bean3 = staticFactory.getInstance("bean3");
        System.out.println(bean3.getField());
    }

    public static final class Factory {

        public static TestBean getBean(final String field) {
            final TestBean bean = new TestBean();
            bean.setField(field);
            return bean;
        }

        public TestBean produce(final String field) {
            return Factory.getBean(field);
        }

    }

}
