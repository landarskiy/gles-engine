package org.bananaLaba.shaderModel.template;

public interface LoopTemplateBuilder extends TemplateBuilder {

    void setCounterId(final String id);

    void setStartValue(final double value);

    void setStartValue(final String parameterId);

    void setStepValue(final double value);

    void setStepValue(final String parameterId);

    void setCountValue(final int value);

    void setCountValue(final String parameterId);

    void setIntegerCounter(final boolean integerCounter);

}
