package org.bananaLaba.fdp.scenario;

public abstract class ProjectionArgumentSpecification extends ArgumentSpecification {

    private Class<?> targetType;

    public Class<?> getTargetType() {
        return this.targetType;
    }

    public void setTargetType(final Class<?> targetType) {
        this.targetType = targetType;
    }

}
