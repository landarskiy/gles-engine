package org.bananaLaba.bootstrap.xml.tagModel.tree;

//FIXME: make something about global context values.
public interface TagContext {

    <T> T getPropagatedAttribute(final String name, final Class<T> type);

    void propagateAttributeDown(final String name, final Object value);

    void setGlobal(final String name, final Object value);

    <T> T getGlobal(final String name, final Class<T> type);

    TagController getChildController(final String uri, final String name);

}
