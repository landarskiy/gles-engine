package org.bananaLaba.fdp;

public class TagSpecification {

    private Integer minCount;
    private Integer maxCount;
    private String scenarioId;

    public TagSpecification() {
    }

    public TagSpecification(final String scenarioId) {
        this.scenarioId = scenarioId;
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

    public String getScenarioId() {
        return this.scenarioId;
    }

    public void setScenarioId(final String scenarioId) {
        this.scenarioId = scenarioId;
    }

}
