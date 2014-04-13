package org.bananaLaba.bootstrap.xml.tagModel;

import java.util.Stack;

import org.bananaLaba.bootstrap.xml.TagLogics;
import org.bananaLaba.bootstrap.xml.TagLogicsIterator;

public abstract class BaseTagLogicsIterator implements TagLogicsIterator {

    private Stack<TagHandler> handlerStack = new Stack<>();

    @Override
    public void enterNamespace(final String uri, final String prefix) {
    }

    @Override
    public void leaveNamespace(final String prefix) {
    }

    @Override
    public TagLogics enterTag(final String uri, final String name) {
        final TagHandler handler = this.getHandler(uri, name);
        this.handlerStack.push(handler);

        return handler;
    }

    @Override
    public void leaveTag() {
        this.handlerStack.pop().close();
    }

    protected abstract TagHandler getHandler(final String uri, final String name);

}
