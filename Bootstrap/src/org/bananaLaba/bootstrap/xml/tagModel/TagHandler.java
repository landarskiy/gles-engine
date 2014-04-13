package org.bananaLaba.bootstrap.xml.tagModel;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.TagLogics;

public interface TagHandler extends TagLogics {

    public static final TagHandler NOOP_HANDLER = new TagHandler() {

        @Override
        public void handle(final AttributeMap attributes) {
        }

        @Override
        public void close() {
        }

    };

    void close();

}
