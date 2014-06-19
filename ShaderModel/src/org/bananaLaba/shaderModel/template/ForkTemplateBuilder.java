package org.bananaLaba.shaderModel.template;

public interface ForkTemplateBuilder extends BaseTemplateBuilder {

    TemplateBuilder addBranch(final TemplateCondition condition);

    TemplateBuilder getDefaultTemplateBuilder();

}
