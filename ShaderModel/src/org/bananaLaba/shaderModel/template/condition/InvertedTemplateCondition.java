package org.bananaLaba.shaderModel.template.condition;

import org.bananaLaba.shaderModel.ParameterMap;
import org.bananaLaba.shaderModel.template.TemplateCondition;

public class InvertedTemplateCondition implements TemplateCondition {

    private TemplateCondition condition;

    public TemplateCondition getCondition() {
        return this.condition;
    }

    public void setCondition(final TemplateCondition condition) {
        this.condition = condition;
    }

    @Override
    public boolean check(final ParameterMap parameters) {
        return !this.condition.check(parameters);
    }

}
