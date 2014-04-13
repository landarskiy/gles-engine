package org.bananaLaba.ioc.test;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.ioc.injection.ArgumentGroup;
import org.bananaLaba.ioc.injection.SimpleArgument;
import org.bananaLaba.ioc.reflection.ReferenceResolver;
import org.bananaLaba.ioc.simple.InjectionManager;
import org.bananaLaba.ioc.simple.InstantiationManager;
import org.bananaLaba.ioc.simple.beanDefinition.BeanInstantiationData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanPropertyData;
import org.bananaLaba.ioc.test.InjectionTest.TestBean;

public class InstantiationManagerTest {

    public static void main(final String[] arguments) throws Exception {
        final ReferenceResolver<Class<?>> classResolver = new ClassResolver();
        final InstantiationManager instantiationManager = new InstantiationManager();
        final InjectionManager injectionManager = new InjectionManager();

        BeanInstantiationData data1 = new BeanInstantiationData();
        data1.setInjection(new ArgumentGroup());
        instantiationManager.addBean("bean1", data1, classResolver);

        BeanPropertyData data2 = new BeanPropertyData();
        ArgumentGroup injection = new ArgumentGroup();
        injection.addPrimitive(new SimpleArgument<Object>("a field value"));
        data2.addInjection("setField", injection);
        injectionManager.addBean("bean1", data2, classResolver);

        data1 = new BeanInstantiationData();
        injection = new ArgumentGroup();
        final SimpleArgument<String> reference = new SimpleArgument<String>("bean1");
        reference.setTypeHint(TestBean.class);
        injection.addReference(reference);
        data1.setInjection(injection);
        instantiationManager.addBean("bean2", data1, classResolver);

        final Object bean = instantiationManager.getInstance("bean1", null);
        injectionManager.apply("bean1", bean, null);

        System.out.println(bean);
        System.out.println(instantiationManager.getInstance("bean2", new ReferenceResolver<Object>() {

            @Override
            public Object resolve(final String name) {
                if ("bean1".equals(name)) {
                    return bean;
                }
                else {
                    return null;
                }
            }

        }));
    }

    public static class TestBean2 {

        private TestBean bean;

        public TestBean2(final TestBean bean) {
            this.bean = bean;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder(this.getClass().getName());
            builder.append(" {bean: ").append(this.bean).append("}");

            return builder.toString();
        }

    }

    private static class ClassResolver implements ReferenceResolver<Class<?>> {

        private static final Map<String, Class<?>> TYPES = new HashMap<>();
        static {
            ClassResolver.TYPES.put("bean1", TestBean.class);
            ClassResolver.TYPES.put("bean2", TestBean2.class);
        }
        @Override
        public Class<?> resolve(final String name) {
            return ClassResolver.TYPES.get(name);
        }

    }

}
