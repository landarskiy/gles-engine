package org.bananaLaba.fdp.simple.tagModel;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;

public class ScenarioTagHandler implements ExtendedTagHandler {

    private ScenarioContext scenarioContext;
    private String openScenarioId;
    private String closeScenarioId;
    private String characterScenarioId;
    private ExtensibleAttributeMap currentAttributeMap = new ExtensibleAttributeMap();
    private String characterDataAttributeName;

    public void setScenarioContext(final ScenarioContext context) {
        this.scenarioContext = context;
    }

    public void setOpenScenarioId(final String id) {
        this.openScenarioId = id;
    }

    public void setCloseScenarioId(final String closeScenarioId) {
        this.closeScenarioId = closeScenarioId;
    }

    @Override
    public void handle(final AttributeMap attributes) {
        this.currentAttributeMap.setOriginalMap(attributes);
        if (this.openScenarioId != null) {
            this.scenarioContext.setAttributeMap(this.currentAttributeMap);
            this.scenarioContext.execute(this.openScenarioId);
        }
    }

    @Override
    public void close() {
        if (this.closeScenarioId != null) {
            this.scenarioContext.setAttributeMap(this.currentAttributeMap);
            this.scenarioContext.execute(this.closeScenarioId);
        }

        this.currentAttributeMap.setOriginalMap(null);
        this.currentAttributeMap.clearExtensionAttributes();
    }

    @Override
    public void setContext(final TagContext context) {
    }

    public void setCharacterDataAttributeName(final String characterDataAttributeName) {
        this.characterDataAttributeName = characterDataAttributeName;
    }

    @Override
    public void handleCharacterData(final String data) {
        if (this.characterScenarioId != null) {
            this.currentAttributeMap.setAttribute(this.characterDataAttributeName, data);
            this.scenarioContext.setAttributeMap(this.currentAttributeMap);
            this.scenarioContext.execute(this.characterScenarioId);
        }
    }

    public void setCharacterScenarioId(final String characterScenarioId) {
        this.characterScenarioId = characterScenarioId;
    }

}
