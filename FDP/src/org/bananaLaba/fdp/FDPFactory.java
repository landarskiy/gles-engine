package org.bananaLaba.fdp;

import org.bananaLaba.bootstrap.graph.FlowNode;

public interface FDPFactory {

    void setTagStructure(final FlowNode<QualifiedName, TagSpecification> root);

    XMLProcessor getProcessorInstance();

    ScenarioBuilder getScenarioBuilder();

}
