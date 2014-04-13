package org.bananaLaba.ioc.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerLoader;
import org.bananaLaba.ioc.bootstrap.SimpleBeanContainerLoader;

public class ContainerLoaderTest {

    public static void main(final String[] arguments) throws Exception {
        final BeanContainerLoader loader = new SimpleBeanContainerLoader();
        final BeanContainer container = loader.load(new FileInputStream(new File("files/test/simpleContext.xml")));
        System.out.println(container.getBean("test2", ContainedBean.class).getValue());
        System.out.println(container.getBean("test2", ContainedBean.class).getValue());
    }

    public static class ContainedBean {

        private String value;

        public void setValue(final String value) {
            this.value = value;
        }

        public void setList(final List<String> list) {
            for (final String string : list) {
                System.out.println(string);
            }
        }

        public String getValue() {
            return this.value;
        }

    }

    public static class Factory {

        public static ContainedBean create() {
            return new ContainedBean();
        }

        public static void init(final int integer) {
            System.out.println("The factory has been initialized with \"" + integer + "\".");
        }

    }

}
