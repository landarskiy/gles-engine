package org.bananaLaba.fdp.scenario;

public class ClassConstantSpecification extends ArgumentSpecification {

    private String constantName;
    private Class<?> sourceType;

    public String getConstantName() {
        return this.constantName;
    }

    public void setConstantName(final String constantName) {
        this.constantName = constantName;
    }

    public Class<?> getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(final Class<?> sourceType) {
        this.sourceType = sourceType;
    }

}
