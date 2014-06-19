package org.bananaLaba.shaderModel.template.simple;

import org.bananaLaba.shaderModel.ParameterMap;

public interface TemplateContext extends ParameterMap {

    String getFragment(final String id);

    <T> T getVariableValue(final String id, final Class<T> type);

}
