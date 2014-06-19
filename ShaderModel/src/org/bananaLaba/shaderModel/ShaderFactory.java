package org.bananaLaba.shaderModel;


public interface ShaderFactory {

    ShaderFactorySession getSession(final ParameterMap parameters);

}
