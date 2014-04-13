package org.bananaLaba.fdp.simple.tagModel;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;

public class ScenarioTagHandler implements ExtendedTagHandler {

    private ScenarioContext scenarioContext;
    private String scenarioId;

    public void setScenarioContext(final ScenarioContext context) {
        this.scenarioContext = context;
    }

    public void setScenarioId(final String id) {
        this.scenarioId = id;
    }

    @Override
    public void close() {
    }

    @Override
    public void handle(final AttributeMap attributes) {
        if (this.scenarioId != null) {
            this.scenarioContext.setAttributeMap(attributes);
            this.scenarioContext.execute(this.scenarioId);
        }
    }

    @Override
    public void setContext(final TagContext context) {
    }

}
