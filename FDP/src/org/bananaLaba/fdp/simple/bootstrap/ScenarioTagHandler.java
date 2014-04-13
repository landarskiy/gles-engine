package org.bananaLaba.fdp.simple.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.fdp.ScenarioBuilder;

public class ScenarioTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_ID = "id";

    private ScenarioBuilder builder;
    private Set<String> usedIds = new HashSet<>();

    public ScenarioTagHandler(final ScenarioBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void handle(final AttributeMap attributes) {
        final String id = attributes.getAttribute(ScenarioTagHandler.ATTRIBUTE_ID);
        if (this.usedIds.contains(id)) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("The scenario id \"" + id + "\" is already used!");
        } else {
            this.usedIds.add(id);
        }

        this.builder.setId(id);
    }

    @Override
    public void close() {
        this.builder.commit();
        this.builder.clearBeanCalls();
    }

    @Override
    public void setContext(final TagContext context) {
    }

}
