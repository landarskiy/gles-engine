package org.bananaLaba.shaderModel;

public interface ParameterMap {

    <T> T getParameterValue(final String id, final Class<T> type);

    boolean isParameterPresent(final String id);

}
