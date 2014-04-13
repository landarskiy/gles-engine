package org.bananaLaba.fdp;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.fdp.simple.ValueStore;
import org.bananaLaba.ioc.BeanContainer;

public interface XMLProcessorContext {

    BeanContainer getBeanContainer();

    ValueStore getTransientStore();

    AttributeMap getAttributeMap();

}
