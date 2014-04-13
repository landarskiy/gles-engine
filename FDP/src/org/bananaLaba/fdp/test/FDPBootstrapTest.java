package org.bananaLaba.fdp.test;

import java.io.File;
import java.io.FileInputStream;

import org.bananaLaba.fdp.FDPLoader;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.simple.bootstrap.SimpleFDPLoader;
import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerLoader;
import org.bananaLaba.ioc.bootstrap.SimpleBeanContainerLoader;

public class FDPBootstrapTest {

    public static void main(final String[] arguments) throws Exception {
        final BeanContainerLoader containerLoader = new SimpleBeanContainerLoader();
        final BeanContainer container =
                containerLoader.load(new FileInputStream(new File("files/test/processorTestContainer.xml")));
        final FDPLoader loader = new SimpleFDPLoader(container);
        final XMLProcessor processor = loader.load(new FileInputStream(new File("files/test/scenario.xml")));
        processor.process(new FileInputStream(new File("files/test/processorTest.xml")));
    }

    public static class Action {

        public void print(final Object tuple) {
            System.out.println(tuple);
        }

        public void add(final float a, final float b) {
            System.out.println(String.format("%f + %f = %f", a, b, a + b));
        }

    }

    public static class Tuple {

        private float a;
        private float b;

        public float getA() {
            return this.a;
        }

        public void setA(final float a) {
            this.a = a;
        }

        public float getB() {
            return this.b;
        }

        public void setB(final float b) {
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
