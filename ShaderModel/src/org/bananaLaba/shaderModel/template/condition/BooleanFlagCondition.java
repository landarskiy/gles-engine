package org.bananaLaba.shaderModel.template.condition;

import org.bananaLaba.shaderModel.ParameterMap;

public class BooleanFlagCondition extends SingleTemplateCondition {

    public BooleanFlagCondition() {
    }

    public BooleanFlagCondition(final String parameterId) {
        super(parameterId);
    }

    @Override
    public boolean check(final ParameterMap parameters) {
        return parameters.getParameterValue(this.getParameterId(), boolean.class);
    }

}
