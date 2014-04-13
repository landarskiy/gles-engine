package org.bananaLaba.fdp.simple.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;

public class StoreTagHandler implements ExtendedTagHandler {

    public static final String ATTRIBUTE_STORE_KEY = "key";

    private TagContext context;

    @Override
    public void handle(final AttributeMap attributes) {
        this.context.propagateAttributeDown(StoreTagHandler.ATTRIBUTE_STORE_KEY,
                attributes.getAttribute(StoreTagHandler.ATTRIBUTE_STORE_KEY));
    }

    @Override
    public void close() {
    }

    @Override
    public void setContext(final TagContext context) {
        this.context = context;
    }

}
