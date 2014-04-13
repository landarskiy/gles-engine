package org.bananaLaba.fdp.scenario;

public abstract class ArgumentSpecification {

    private Class<?> typeHint;

    public Class<?> getTypeHint() {
        return this.typeHint;
    }

    public void setTypeHint(final Class<?> typeHint) {
        this.typeHint = typeHint;
    }

}
