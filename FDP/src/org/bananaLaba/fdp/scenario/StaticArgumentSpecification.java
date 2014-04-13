package org.bananaLaba.fdp.scenario;

public class StaticArgumentSpecification extends ArgumentSpecification {

    private Object value;

    public StaticArgumentSpecification() {
    }

    public StaticArgumentSpecification(final Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

}
