package org.bananaLaba.shaderModel.template;

import org.bananaLaba.shaderModel.ShaderMetaFactory;

public interface ShaderFactoryPrototype extends ShaderMetaFactory {

    IdentifiedTemplateBuilder getTemplateBuilder();

    IdentifiedTemplateBuilder getFragmentTemplateBuilder();

    void clearTemplates();

    void clearFragmentTemplates();

}
