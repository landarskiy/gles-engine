package org.bananaLaba.shaderModel.template.simple.meta;

import org.bananaLaba.shaderModel.template.LoopTemplateBuilder;
import org.bananaLaba.shaderModel.template.simple.ConstantValue;
import org.bananaLaba.shaderModel.template.simple.LoopTemplateUnit;
import org.bananaLaba.shaderModel.template.simple.ParameterExtractor;
import org.bananaLaba.shaderModel.template.simple.TemplateUnit;

public abstract class SimpleLoopTemplateBuilder extends SimpleTemplateBuilder implements LoopTemplateBuilder {

    private LoopTemplateUnit loopUnit = new LoopTemplateUnit();

    @Override
    public void setCounterId(final String id) {
        this.loopUnit.setCounterVariableId(id);
    }

    @Override
    public void setStartValue(final double value) {
        this.loopUnit.setStartValueParameter(ConstantValue.create(value));
    }

    @Override
    public void setStartValue(final String parameterId) {
        this.loopUnit.setStartValueParameter(ParameterExtractor.create(parameterId, Double.class));
    }

    @Override
    public void setStepValue(final double value) {
        this.loopUnit.setStepParameter(ConstantValue.create(value));
    }

    @Override
    public void setStepValue(final String parameterId) {
        this.loopUnit.setStepParameter(ParameterExtractor.create(parameterId, Double.class));
    }

    @Override
    public void setCountValue(final int value) {
        this.loopUnit.setCountParameter(ConstantValue.create(value));
    }

    @Override
    public void setCountValue(final String parameterId) {
        this.loopUnit.setCountParameter(ParameterExtractor.create(parameterId, Integer.class));
    }

    @Override
    public void setIntegerCounter(final boolean integerCounter) {
        this.loopUnit.setIntegerCounter(integerCounter);
    }

    @Override
    protected TemplateUnit modify(final TemplateUnit unit) {
        if (this.loopUnit.getCountParameter() == null) {
            throw new IllegalStateException("Cannot commit a loop tempalte without a count parameter!");
        }

        if (this.loopUnit.getStartValueParameter() == null) {
            throw new IllegalStateException("Cannot commit a loop tempalte without a start value parameter!");
        }

        if (this.loopUnit.getStepParameter() == null) {
            throw new IllegalStateException("Cannot commit a loop tempalte without a step parameter!");
        }

        this.loopUnit.setUnit(unit);

        return this.loopUnit;
    }

    public TemplateUnit getLoopUnit() {
        return this.loopUnit;
    }

}
