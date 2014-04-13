package org.bananaLaba.bootstrap.xml;

public interface TagLogicsIterator {

    void enterNamespace(final String uri, final String prefix);

    void leaveNamespace(final String prefix);

    TagLogics enterTag(final String uri, final String name);

    void leaveTag();

}
