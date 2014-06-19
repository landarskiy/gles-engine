package org.bananaLaba.shaderModel.template.condition;

import org.bananaLaba.shaderModel.ParameterMap;

public class ExistsCondition extends SingleTemplateCondition {

    @Override
    public boolean check(final ParameterMap parameters) {
        return parameters.isParameterPresent(this.getParameterId());
    }

}
