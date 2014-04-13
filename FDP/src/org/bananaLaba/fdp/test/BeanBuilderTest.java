package org.bananaLaba.fdp.test;

import org.bananaLaba.fdp.mapping.bean.BeanReification;

public class BeanBuilderTest {

    public static void main(final String[] arguments) {
        final BeanReification<SimpleBean> builder = new BeanReification<>(SimpleBean.class);
        builder.setProperty("a", 10);
        builder.setProperty("b", "property");

        System.out.println(builder.build());
    }

    public static class SimpleBean {

        private int a;
        private String b;

        public void setA(final int a) {
            this.a = a;
        }

        public void setB(final String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder("{a: ");
            stringBuilder.append(this.a).append(", b: ").append(this.b).append("}");

            return stringBuilder.toString();
        }

    }

}
