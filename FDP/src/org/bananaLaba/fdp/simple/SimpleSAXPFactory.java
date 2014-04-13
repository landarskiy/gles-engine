package org.bananaLaba.fdp.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.fdp.FDPFactory;
import org.bananaLaba.fdp.QualifiedName;
import org.bananaLaba.fdp.ScenarioBuilder;
import org.bananaLaba.fdp.TagSpecification;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.scenario.ActionSpecification;
import org.bananaLaba.fdp.util.action.ActionHelperFactory;
import org.bananaLaba.ioc.BeanContainer;

public class SimpleSAXPFactory implements FDPFactory {

    private Map<String, List<ActionSpecification>> scenarioMap = new HashMap<>();
    private FlowNode<QualifiedName, TagSpecification> tagStructure;
    private BeanContainer beanContainer;

    public SimpleSAXPFactory(final BeanContainer container) {
        this.beanContainer = container;
    }

    @Override
    public void setTagStructure(final FlowNode<QualifiedName, TagSpecification> tagStructure) {
        this.tagStructure = tagStructure;
    }

    @Override
    public XMLProcessor getProcessorInstance() {
        final SimpleSAXProcessor processor = new SimpleSAXProcessor();
        processor.setBeanContainer(this.beanContainer);

        final Set<Entry<String, List<ActionSpecification>>> scenarioEntries = this.scenarioMap.entrySet();
        final ActionHelperFactory factory = ActionHelperFactory.getInstance();
        for (final Entry<String, List<ActionSpecification>> scenarioEntry : scenarioEntries) {
            final List<ActionHelper> helpers = new ArrayList<>();
            for (final ActionSpecification specification : scenarioEntry.getValue()) {
                helpers.add(factory.prepare(specification));
            }

            processor.bindScenario(scenarioEntry.getKey(), helpers);
        }

        // TODO: ensure that at this point the tag structure will be copied safely without causing any further
        // reference anomalies.
        processor.setScenarioTree(this.tagStructure);

        return processor;
    }

    @Override
    public ScenarioBuilder getScenarioBuilder() {
        return new SimpleScenarioBuilder() {

            @Override
            protected void commitInernal(final String id, final List<ActionSpecification> callSpecifications) {
                SimpleSAXPFactory.this.scenarioMap.put(id, new ArrayList<>(callSpecifications));
            }

        };
    }

    public BeanContainer getBeanContainer() {
        return this.beanContainer;
    }

    public void setBeanContainer(final BeanContainer beanContainer) {
        this.beanContainer = beanContainer;
    }

}
