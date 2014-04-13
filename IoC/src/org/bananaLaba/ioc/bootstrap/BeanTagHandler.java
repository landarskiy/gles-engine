package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.ioc.BeanContainerFactory;

public class BeanTagHandler extends BaseBeanTagHandler {

    public BeanTagHandler(final BeanContainerFactory containerFactory) {
        super(containerFactory);
    }

    @Override
    protected String getBeanName(final AttributeMap attributes) {
        if (!attributes.isPresent(BaseBeanTagHandler.ATTRIBUTE_NAME)) {
            // TODO: throw a custom exception here.
            throw new IllegalArgumentException("Each regular bean must have a name!");
        }

        return attributes.getAttribute(BaseBeanTagHandler.ATTRIBUTE_NAME);
    }

    @Override
    protected void onCheckCategory(final BeanCategory category) {
    }

    @Override
    protected boolean canBeInherited() {
        return true;
    }

}
