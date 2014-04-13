package org.bananaLaba.bootstrap.xml.tagModel.plain;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.common.Prototype;
import org.bananaLaba.bootstrap.xml.TagLogicsIterator;
import org.bananaLaba.bootstrap.xml.tagModel.BaseTagLogicsIterator;
import org.bananaLaba.bootstrap.xml.tagModel.TagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.TagModel;

public class PlainTagModel implements TagModel {

    private Map<String, Prototype<? extends TagHandler>> handlerMap = new HashMap<>();

    public void bind(final String uri, final String entity, final Prototype<? extends TagHandler> handlerPrototype) {
            this.handlerMap.put(PlainTagModel.getQualifiedName(uri, entity), handlerPrototype);
    }

    public void bind(final String entity, final Prototype<? extends TagHandler> handlerPrototype) {
        this.bind(null, entity, handlerPrototype);
    }

    @Override
    public TagLogicsIterator getIterator() {
        return new PlainTagModelIterator();
    }

    private static String getQualifiedName(final String uri, final String name) {
        if ((uri != null) && !uri.isEmpty()) {
            return String.format("%s:%s", uri, name);
        } else {
            return name;
        }
    }

    private class PlainTagModelIterator extends BaseTagLogicsIterator {

        @Override
        protected TagHandler getHandler(final String uri, final String name) {
            final String qualifiedName = PlainTagModel.getQualifiedName(uri, name);
            final Prototype<? extends TagHandler> handlerPrototype = PlainTagModel.this.handlerMap.get(qualifiedName);
            if (handlerPrototype == null) {
                throw new IllegalArgumentException("Unknown entity \"" + name + "\" in the namespace \"" + uri
                        + "\"!");
            } else {
                final TagHandler handler = handlerPrototype.create();
                return handler;
            }
        }

    }

}
