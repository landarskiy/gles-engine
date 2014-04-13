package org.bananaLaba.fdp.simple.tagModel;

import org.bananaLaba.bootstrap.common.AttributeMap;

public interface ScenarioContext {

    void execute(final String id);

    void setAttributeMap(final AttributeMap map);

}
