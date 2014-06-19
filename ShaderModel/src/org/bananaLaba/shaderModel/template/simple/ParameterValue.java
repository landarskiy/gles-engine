package org.bananaLaba.shaderModel.template.simple;

import org.bananaLaba.shaderModel.ParameterMap;

public interface ParameterValue<T> {

    T get(final ParameterMap map);

}
