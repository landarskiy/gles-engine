package org.bananaLaba.fdp.simple.bootstrap;

import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;

public class ProjectionTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_ATTRIBUTE = "attribute";
    private static final String ATTRIBUTE_PROPERTY = "property";

    private Map<String, String> attributeProjection;

    public ProjectionTagHandler(final Map<String, String> attributeProjection) {
        this.attributeProjection = attributeProjection;
    }

    @Override
    public void handle(final AttributeMap attributes) {
        final String attributeName = attributes.getAttribute(ProjectionTagHandler.ATTRIBUTE_ATTRIBUTE);
        String propertyName = attributeName;
        if (attributes.isPresent(ProjectionTagHandler.ATTRIBUTE_PROPERTY)) {
            propertyName = attributes.getAttribute(ProjectionTagHandler.ATTRIBUTE_PROPERTY);
        }

        this.attributeProjection.put(attributeName, propertyName);
    }

    @Override
    public void close() {
    }

    @Override
    public void setContext(final TagContext context) {
    }

}
