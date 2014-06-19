package org.bananaLaba.shaderModel.template.condition;

import org.bananaLaba.shaderModel.template.TemplateCondition;

public abstract class SingleTemplateCondition implements TemplateCondition {

    private String parameterId;

    public SingleTemplateCondition() {
    }

    public SingleTemplateCondition(final String parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterId() {
        return this.parameterId;
    }

    public void setParameterId(final String parameterId) {
        this.parameterId = parameterId;
    }

}
