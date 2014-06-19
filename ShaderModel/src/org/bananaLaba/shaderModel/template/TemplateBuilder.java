package org.bananaLaba.shaderModel.template;

public interface TemplateBuilder extends BaseTemplateBuilder {

    void addStaticUnit(final String content);

    void addInclusionUnit(final String id);

    void addParameterUnit(final String id);

    void addVariableUnit(final String id);

    ForkTemplateBuilder addForkUnit();

    LoopTemplateBuilder addLoopUnit();

    void clearUnits();

}
