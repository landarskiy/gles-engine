package org.bananaLaba.fdp.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.fdp.XMLProcessorContext;
import org.bananaLaba.fdp.simple.tagModel.ScenarioContext;
import org.bananaLaba.ioc.BeanContainer;

public class SAXProcessorContext implements XMLProcessorContext, ScenarioContext {

    private BeanContainer container;
    private AttributeMap currentAttributes;
    private AttributeMap attributeMapWrapper = new AttributeMap() {

        @Override
        public String getAttribute(final String name) {
            return SAXProcessorContext.this.currentAttributes.getAttribute(name);
        }

        @Override
        public boolean isPresent(final String name) {
            return SAXProcessorContext.this.currentAttributes.isPresent(name);
        }

    };
    private Map<String, List<ActionHelper>> helperMap = new HashMap<>();
    private SimpleValueStore transientStore = new SimpleValueStore();

    public SAXProcessorContext(final BeanContainer container, final Map<String, List<ActionHelper>> helperMap) {
        this.container = container;
        // TODO: temporary code.
        this.helperMap = helperMap;
        //
    }

    @Override
    public BeanContainer getBeanContainer() {
        return this.container;
    }

    @Override
    public ValueStore getTransientStore() {
        return this.transientStore;
    }

    @Override
    public AttributeMap getAttributeMap() {
        return this.attributeMapWrapper;
    }

    @Override
    public void execute(final String id) {
        final List<ActionHelper> helpers = this.helperMap.get(id);
        for (final ActionHelper helper : helpers) {
            helper.call();
        }
    }

    @Override
    public void setAttributeMap(final AttributeMap map) {
        this.currentAttributes = map;
    }

    public void clear() {
        this.transientStore.clear();
        this.currentAttributes = null;
    }

}
