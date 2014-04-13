package org.bananaLaba.fdp.scenario;

public class ReferenceArgumentSpecification extends ArgumentSpecification {

    private ContextReferenceType source;
    private String key;

    public ContextReferenceType getSource() {
        return this.source;
    }

    public void setSource(final ContextReferenceType type) {
        this.source = type;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

}
