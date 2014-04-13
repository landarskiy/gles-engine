package org.bananaLaba.fdp.simple.bootstrap;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.fdp.FDPFactory;
import org.bananaLaba.fdp.QualifiedName;
import org.bananaLaba.fdp.TagSpecification;

public class ProcessorTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_ROOT_URI = "rootUri";
    private static final String ATTRIBUTE_ROOT_NAME = "rootName";
    private static final String ATTRIBUTE_ROOT_SCEANRIO_ID = "rootScenarioId";
    private static final String ATTRIBUTE_ROOT_ID = "rootId";

    public static final String SHARED_TAG_MAP = "tagMap";

    public static final String PARENT_TAG_NODE = "parentNode";

    private FDPFactory factory;
    private FlowNode<QualifiedName, TagSpecification> tagStructure = new FlowNode<>();

    private Map<String, FlowNode<QualifiedName, TagSpecification>> tagMap = new HashMap<>();

    public ProcessorTagHandler(final FDPFactory factory) {
        this.factory = factory;
    }

    @Override
    public void close() {
        this.factory.setTagStructure(this.tagStructure);
    }

    @Override
    public void handle(final AttributeMap attributes) {
        String rootUri = null;
        if (attributes.isPresent(ProcessorTagHandler.ATTRIBUTE_ROOT_URI)) {
            rootUri = attributes.getAttribute(ProcessorTagHandler.ATTRIBUTE_ROOT_URI);
        }
        final String rootName = attributes.getAttribute(ProcessorTagHandler.ATTRIBUTE_ROOT_NAME);
        String rootScenarioId = null;
        if (attributes.isPresent(ProcessorTagHandler.ATTRIBUTE_ROOT_SCEANRIO_ID)) {
            rootScenarioId = attributes.getAttribute(ProcessorTagHandler.ATTRIBUTE_ROOT_SCEANRIO_ID);
        }

        this.tagStructure.setId(new QualifiedName(rootUri, rootName));
        this.tagStructure.setContent(new TagSpecification(rootScenarioId));

        if (attributes.isPresent(ProcessorTagHandler.ATTRIBUTE_ROOT_ID)) {
            final String rootId = attributes.getAttribute(ProcessorTagHandler.ATTRIBUTE_ROOT_ID);
            this.tagMap.put(rootId, this.tagStructure);
        }
    }

    @Override
    public void setContext(final TagContext context) {
        context.setGlobal(ProcessorTagHandler.SHARED_TAG_MAP, this.tagMap);

        context.propagateAttributeDown(ProcessorTagHandler.PARENT_TAG_NODE, this.tagStructure);
    }

}
