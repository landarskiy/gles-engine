package org.bananaLaba.fdp.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.fdp.BeanCallBuilder;
import org.bananaLaba.fdp.FDPFactory;
import org.bananaLaba.fdp.QualifiedName;
import org.bananaLaba.fdp.ScenarioBuilder;
import org.bananaLaba.fdp.TagSpecification;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.scenario.ContextReferenceType;
import org.bananaLaba.fdp.simple.SimpleSAXPFactory;
import org.bananaLaba.fdp.test.SimpleSAXProcessorTest.DataBean;
import org.bananaLaba.fdp.test.SimpleSAXProcessorTest.ListenerBean;
import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;
import org.bananaLaba.ioc.simple.builder.SimpleBeanContainerFactory;

public class SimpleSAXPFactoryTest {

    public static void main(final String[] arguments) throws Exception {
        final BeanContainerFactory factory = new SimpleBeanContainerFactory();

        final ObjectBeanBuilder builder = factory.getObjectBeanBuilder();
        builder.setName("listener");
        builder.setType(ListenerBean.class);
        builder.commit();

        final BeanContainer container = factory.getContainerInstance();

        final FDPFactory processorFactory = new SimpleSAXPFactory(container);

        final ScenarioBuilder scenarioBuilder = processorFactory.getScenarioBuilder();
        scenarioBuilder.setId("entity");
        final BeanCallBuilder callBuilder = scenarioBuilder.getBeanCallBuilder();
        callBuilder.addSimpleArgument("attribute", String.class);
        callBuilder.setBean("listener", ContextReferenceType.BEAN);
        callBuilder.setMethodName("onTag");
        callBuilder.commit();
        scenarioBuilder.commit();

        scenarioBuilder.clearBeanCalls();
        scenarioBuilder.setId("child");
        callBuilder.clearArguments();
        callBuilder.setMethodName("onChildTag");
        final Map<String, String> attributeMapping = new HashMap<>();
        attributeMapping.put("a", "a");
        attributeMapping.put("b", "b");
        callBuilder.addProjectionArgument(DataBean.class, attributeMapping);
        callBuilder.commit();
        scenarioBuilder.commit();

        final FlowNode<QualifiedName, TagSpecification> root = new FlowNode<>();
        root.setContent(new TagSpecification("entity"));
        root.setId(new QualifiedName("org.bananaLaba.namespace", "entity"));
        root.addChild(root);

        final FlowNode<QualifiedName, TagSpecification> child = new FlowNode<>();
        child.setContent(new TagSpecification("child"));
        child.setId(new QualifiedName("org.bananaLaba.namespace", "child"));
        root.addChild(child);

        processorFactory.setTagStructure(root);

        final XMLProcessor processor = processorFactory.getProcessorInstance();
        processor.process(new FileInputStream(new File("files/test/testXML.xml")));
    }

}
