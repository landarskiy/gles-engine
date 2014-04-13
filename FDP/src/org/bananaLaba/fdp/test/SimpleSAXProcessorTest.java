package org.bananaLaba.fdp.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.fdp.QualifiedName;
import org.bananaLaba.fdp.TagSpecification;
import org.bananaLaba.fdp.scenario.AttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.CallActionSpecification;
import org.bananaLaba.fdp.scenario.CompositeAttributeArgumentSpecification;
import org.bananaLaba.fdp.simple.ActionHelper;
import org.bananaLaba.fdp.simple.SimpleSAXProcessor;
import org.bananaLaba.fdp.util.action.ActionHelperFactory;
import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;
import org.bananaLaba.ioc.simple.builder.SimpleBeanContainerFactory;

public class SimpleSAXProcessorTest {

    public static void main(final String[] arguments) throws Exception {
        final ActionHelperFactory helperFactory = ActionHelperFactory.getInstance();
        final BeanContainerFactory factory = new SimpleBeanContainerFactory();

        final ObjectBeanBuilder builder = factory.getObjectBeanBuilder();
        builder.setName("listener");
        builder.setType(ListenerBean.class);
        builder.commit();

        final BeanContainer container = factory.getContainerInstance();

        final SimpleSAXProcessor processor = new SimpleSAXProcessor();
        processor.setBeanContainer(container);

        CallActionSpecification callSpecification = new CallActionSpecification();
        callSpecification.setTargetId("listener");
        callSpecification.setMethodName("onTag");

        final AttributeArgumentSpecification projectionSpecification = new AttributeArgumentSpecification();
        projectionSpecification.setTypeHint(String.class);
        projectionSpecification.setAttributeName("attribute");
        projectionSpecification.setTargetType(String.class);
        callSpecification.addArgument(projectionSpecification);

        List<ActionHelper> calls = new ArrayList<>();
        calls.add(helperFactory.prepare(callSpecification));

        processor.bindScenario("entity", calls);

        callSpecification = new CallActionSpecification();
        callSpecification.setTargetId("listener");
        callSpecification.setMethodName("onChildTag");

        final CompositeAttributeArgumentSpecification compositeSpecification =
                new CompositeAttributeArgumentSpecification();
        compositeSpecification.setTypeHint(Object.class);
        compositeSpecification.setTargetType(DataBean.class);
        compositeSpecification.addProperty("a");
        compositeSpecification.addProperty("b");
        callSpecification.addArgument(compositeSpecification);

        calls = new ArrayList<>();
        calls.add(helperFactory.prepare(callSpecification));
        processor.bindScenario("child", calls);

        final FlowNode<QualifiedName, TagSpecification> root = new FlowNode<>();
        root.setContent(new TagSpecification("entity"));
        root.setId(new QualifiedName("org.bananaLaba.namespace", "entity"));
        root.addChild(root);

        final FlowNode<QualifiedName, TagSpecification> child = new FlowNode<>();
        child.setContent(new TagSpecification("child"));
        child.setId(new QualifiedName("org.bananaLaba.namespace", "child"));
        root.addChild(child);

        processor.setScenarioTree(root);

        processor.process(new FileInputStream(new File("files/test/testXML.xml")));
    }

    public static class ListenerBean {

        public void onTag(final String attribute) {
            System.out.println("On tag fired. Attribute value: " + attribute);
        }

        public void onChildTag(final Object bean) {
            System.out.println("On child tag fired. Bean is: " + bean);
        }

    }

    public static class DataBean {

        private int a;
        private float b;

        public void setA(final int a) {
            this.a = a;
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
