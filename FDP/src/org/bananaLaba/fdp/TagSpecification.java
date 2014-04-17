package org.bananaLaba.fdp;

public class TagSpecification {

    private Integer minCount;
    private Integer maxCount;
    private String openScenarioId;
    private String closeScenarioId;
    private String characterDataAttributeName;
    private String characterDataScenarioId;

    public TagSpecification() {
    }

    public TagSpecification(final String scenarioId) {
        this.openScenarioId = scenarioId;
    }

    public Integer getMinCount() {
        return this.minCount;
    }

    public void setMinCount(final Integer minCount) {
        this.minCount = minCount;
    }

    public Integer getMaxCount() {
        return this.maxCount;
    }

    public void setMaxCount(final Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getOpenScenarioId() {
        return this.openScenarioId;
    }

    public void setOpenScenarioId(final String scenarioId) {
        this.openScenarioId = scenarioId;
    }

    public String getCharacterDataAttributeName() {
        return this.characterDataAttributeName;
    }

    public void setCharacterDataAttributeName(final String characterDataAttributeName) {
        this.characterDataAttributeName = characterDataAttributeName;
    }

    public String getCharacterDataScenarioId() {
        return this.characterDataScenarioId;
    }

    public void setCharacterDataScenarioId(final String characterDataScenarioId) {
        this.characterDataScenarioId = characterDataScenarioId;
    }

    public String getCloseScenarioId() {
        return this.closeScenarioId;
    }

    public void setCloseScenarioId(final String closeScenarioId) {
        this.closeScenarioId = closeScenarioId;
    }

}
