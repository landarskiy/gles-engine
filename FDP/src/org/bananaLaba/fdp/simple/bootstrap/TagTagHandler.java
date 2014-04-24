package org.bananaLaba.fdp.simple.bootstrap;

import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.fdp.QualifiedName;
import org.bananaLaba.fdp.TagSpecification;

public class TagTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_URI = "uri";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_OPEN_SCEANRIO_ID = "onOpen";
    private static final String ATTRIBUTE_CLOSE_SCEANRIO_ID = "onClose";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_MIN_COUNT = "minCount";
    private static final String ATTRIBUTE_MAX_COUNT = "maxCount";
    private static final String ATTRIBUTE_EXTENDS = "extends";
    private static final String ATTRIBUTE_CHARACTER_ATTRIBUTE_NAME = "characterAttribute";
    private static final String ATTRIBUTE_CHARACTER_SCENARIO_ID = "onCharacters";

    private Map<String, FlowNode<QualifiedName, TagSpecification>> tagMap;
    private Map<FlowNode<QualifiedName, TagSpecification>, String> recuriveExtensionMap;

    private FlowNode<QualifiedName, TagSpecification> tagStructure = new FlowNode<>();
    private FlowNode<QualifiedName, TagSpecification> parentTagStructure;

    @Override
    public void close() {
    }

    @Override
    public void handle(final AttributeMap attributes) {
        String uri = null;
        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_URI)) {
            uri = attributes.getAttribute(TagTagHandler.ATTRIBUTE_URI);
        }
        final String name = attributes.getAttribute(TagTagHandler.ATTRIBUTE_NAME);

        this.tagStructure.setId(new QualifiedName(uri, name));

        final TagSpecification specification = new TagSpecification();
        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_OPEN_SCEANRIO_ID)) {
            specification.setOpenScenarioId(attributes.getAttribute(TagTagHandler.ATTRIBUTE_OPEN_SCEANRIO_ID));
        }
        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_CLOSE_SCEANRIO_ID)) {
            specification.setCloseScenarioId(attributes.getAttribute(TagTagHandler.ATTRIBUTE_CLOSE_SCEANRIO_ID));
        }

        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_MIN_COUNT)) {
            final int minCount = Integer.valueOf(attributes.getAttribute(TagTagHandler.ATTRIBUTE_MIN_COUNT));
            specification.setMinCount(minCount);
        }
        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_MAX_COUNT)) {
            final int maxCount = Integer.valueOf(attributes.getAttribute(TagTagHandler.ATTRIBUTE_MAX_COUNT));
            specification.setMaxCount(maxCount);
        }
        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_CHARACTER_ATTRIBUTE_NAME)) {
            specification.setCharacterDataAttributeName(
                    attributes.getAttribute(TagTagHandler.ATTRIBUTE_CHARACTER_ATTRIBUTE_NAME));
            if (attributes.isPresent(TagTagHandler.ATTRIBUTE_CHARACTER_SCENARIO_ID)) {
                specification.setCharacterDataScenarioId(
                        attributes.getAttribute(TagTagHandler.ATTRIBUTE_CHARACTER_SCENARIO_ID));
            } else {
                // TODO: throw a custom exception here.
                throw new RuntimeException(
                        "The \"characterAttribute\" and \"characterScenarioId\" are allowed only in pair!");
            }
        } else if (attributes.isPresent(TagTagHandler.ATTRIBUTE_CHARACTER_SCENARIO_ID)) {
            // TODO: throw a custom exception here.
            throw new RuntimeException(
                    "The \"characterAttribute\" and \"characterScenarioId\" are allowed only in pair!");
        }
        this.tagStructure.setContent(specification);

        this.parentTagStructure.addChild(this.tagStructure);

        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_EXTENDS)) {
            final String baseId = attributes.getAttribute(TagTagHandler.ATTRIBUTE_EXTENDS);
            this.recuriveExtensionMap.put(this.tagStructure, baseId);
        }

        if (attributes.isPresent(TagTagHandler.ATTRIBUTE_ID)) {
            final String id = attributes.getAttribute(TagTagHandler.ATTRIBUTE_ID);
            if (this.tagMap.containsKey(id)) {
                // TODO: throw a custom exception here.
                throw new RuntimeException("The tag id \"" + id + "\" is already used!");
            }
            this.tagMap.put(id, this.tagStructure);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setContext(final TagContext context) {
        this.tagMap = context.getGlobal(ProcessorTagHandler.SHARED_TAG_MAP, Map.class);
        this.recuriveExtensionMap = context.getGlobal(ProcessorTagHandler.RECURSIVE_EXTENSION_MAP, Map.class);

        this.parentTagStructure = context.getPropagatedAttribute(ProcessorTagHandler.PARENT_TAG_NODE, FlowNode.class);
        context.propagateAttributeDown(ProcessorTagHandler.PARENT_TAG_NODE, this.tagStructure);
    }

    @Override
    public void handleCharacterData(final String data) {
    }

}
