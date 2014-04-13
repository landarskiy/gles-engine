package org.bananaLaba.bootstrap.xml.tagModel.tree;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.TagHandler;

public interface ExtendedTagHandler extends TagHandler {

    public static final ExtendedTagHandler NOOP_HANDLER  = new ExtendedTagHandler() {

        @Override
        public void close() {
        }

        @Override
        public void handle(final AttributeMap attributes) {
        }

        @Override
        public void setContext(final TagContext context) {
        }

    };

    void setContext(final TagContext context);

}
