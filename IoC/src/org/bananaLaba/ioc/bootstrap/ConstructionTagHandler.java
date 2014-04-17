package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;
import org.bananaLaba.ioc.injection.ArgumentGroup;

public class ConstructionTagHandler implements ExtendedTagHandler {

    public static final String ATTRIBUTE_FACTORY_BEAN = "factoryBean";
    public static final String ATTRIBUTE_FACTORY_METHOD = "factoryMethod";

    private ObjectBeanBuilder builder;

    private ConstructionTechnique technique;

    private String factoryBeanName;
    private String factoryMethodName;

    private SimpleArgumentStore store;

    public ConstructionTagHandler() {
    }

    @Override
    public void handle(final AttributeMap attributes) {
        final boolean factoryBeanPresent = attributes.isPresent(ConstructionTagHandler.ATTRIBUTE_FACTORY_BEAN);
        final boolean factoryMethodPresent = attributes.isPresent(ConstructionTagHandler.ATTRIBUTE_FACTORY_METHOD);
        if (factoryBeanPresent && factoryMethodPresent) {
            this.technique = ConstructionTechnique.FACTORY;
            this.factoryBeanName = attributes.getAttribute(ConstructionTagHandler.ATTRIBUTE_FACTORY_BEAN);
            this.factoryMethodName = attributes.getAttribute(ConstructionTagHandler.ATTRIBUTE_FACTORY_METHOD);
        } else if (!factoryBeanPresent && factoryMethodPresent) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("Attempt to use factory method without a factory bean!");
        } else if (factoryBeanPresent && !factoryMethodPresent) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("Attempt to use factory bean without a factory method!");
        } else {
            this.technique = ConstructionTechnique.CONSTRUCTOR;
        }
    }

    @Override
    public void close() {
        final ArgumentGroup group = this.store.getGroup();
        if (this.technique == ConstructionTechnique.CONSTRUCTOR) {
            this.builder.setConstructionTechnique(group);
        } else {
            this.builder.setConstructionTechnique(this.factoryBeanName, this.factoryMethodName, group);
        }

        this.builder = null;
    }

    private static enum ConstructionTechnique {
        CONSTRUCTOR, FACTORY
    }

    @Override
    public void setContext(final TagContext context) {
        this.builder = context.getPropagatedAttribute(BaseBeanTagHandler.SHARED_BUILDER, ObjectBeanBuilder.class);
        this.store = new SimpleArgumentStore();
        context.propagateAttributeDown(ArgumentStore.OBJECT_ARGUMENT_STORE, this.store);
    }

    @Override
    public void handleCharacterData(final String data) {
    }

}
