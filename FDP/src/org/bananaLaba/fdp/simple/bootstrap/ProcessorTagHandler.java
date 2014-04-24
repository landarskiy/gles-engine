package org.bananaLaba.fdp.simple.bootstrap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
    public static final String RECURSIVE_EXTENSION_MAP = "extensionMap";

    public static final String PARENT_TAG_NODE = "parentNode";

    private FDPFactory factory;
    private FlowNode<QualifiedName, TagSpecification> tagStructure = new FlowNode<>();

    private Map<String, FlowNode<QualifiedName, TagSpecification>> tagMap = new HashMap<>();
    private Map<FlowNode<QualifiedName, TagSpecification>, String> recuriveExtensionMap = new HashMap<>();

    public ProcessorTagHandler(final FDPFactory factory) {
        this.factory = factory;
    }

    @Override
    public void close() {
        for (final Entry<FlowNode<QualifiedName, TagSpecification>, String> extensionEntry
                : this.recuriveExtensionMap.entrySet()) {
            final String baseId = extensionEntry.getValue();
            if (!this.tagMap.containsKey(baseId)) {
                // TODO: throw a custom exception here.
                throw new RuntimeException("Attempt to extend a missing tag with id \"" + baseId + "\"!");
            }

            extensionEntry.getKey().addChildren(this.tagMap.get(baseId).getChildMap().values());
        }

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
        context.setGlobal(ProcessorTagHandler.RECURSIVE_EXTENSION_MAP, this.recuriveExtensionMap);

        context.propagateAttributeDown(ProcessorTagHandler.PARENT_TAG_NODE, this.tagStructure);
    }

    @Override
    public void handleCharacterData(final String data) {
    }

}
