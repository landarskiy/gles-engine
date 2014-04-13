package org.bananaLaba.bootstrap.xml.tagModel.tree;

public interface TagController {

    void setBlocked(final boolean blocked);

    void setMinCount(final Integer count);

    void setMaxCount(final Integer count);

    int getCurrentCount();

}
