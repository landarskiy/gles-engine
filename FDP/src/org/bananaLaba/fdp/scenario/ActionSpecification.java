package org.bananaLaba.fdp.scenario;

public abstract class ActionSpecification {

    private boolean skippable;
    private String targetId;

    public boolean isSkippable() {
        return this.skippable;
    }

    public void setSkippable(final boolean skippable) {
        this.skippable = skippable;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(final String targetId) {
        this.targetId = targetId;
    }

}
