package org.bananaLaba.shaderModel.template.condition;

import java.util.ArrayList;
import java.util.List;

import org.bananaLaba.shaderModel.ParameterMap;
import org.bananaLaba.shaderModel.template.TemplateCondition;

public class CompositeTemplateCondition implements TemplateCondition {

    private List<TemplateCondition> conditions = new ArrayList<>();

    public void addCondition(final TemplateCondition condition) {
        this.conditions.add(condition);
    }

    public void clearConditions() {
        this.conditions.clear();
    }

    @Override
    public boolean check(final ParameterMap parameters) {
        for (final TemplateCondition condition : this.conditions) {
            if (!condition.check(parameters)) {
                return false;
            }
        }

        return true;
    }

}
