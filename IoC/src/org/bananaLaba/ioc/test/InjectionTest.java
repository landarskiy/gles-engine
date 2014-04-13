package org.bananaLaba.ioc.test;

import java.lang.reflect.Method;

import org.bananaLaba.ioc.simple.injectors.MethodInjector;

public class InjectionTest {

    public static void main(final String[] arguments) throws Exception {
        final TestBean bean = new TestBean();

        final Method method = bean.getClass().getMethod("setField", String.class);

        final MethodInjector<TestBean> injector = new MethodInjector<>(method);
        injector.apply(bean, "Test value");

        System.out.println(bean.getField());
    }

    public static final class TestBean {

        private String field;

        public void setField(final String field) {
            this.field = field;
        }

        public String getField() {
            return this.field;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.getClass()).append(" {").append(this.field).append("}");

            return stringBuilder.toString();
        }

    }

}
