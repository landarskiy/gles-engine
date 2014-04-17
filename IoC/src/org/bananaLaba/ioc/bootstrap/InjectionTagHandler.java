package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.ioc.bean.BeanBuilder;
import org.bananaLaba.ioc.injection.ArgumentGroup;

public class InjectionTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_METHOD = "method";
    private static final String ATTRIBUTE_PROPERTY = "property";

    private BeanBuilder builder;

    private String targetName;
    private InjectionType type;

    private SimpleArgumentStore store;

    @Override
    public void handle(final AttributeMap attributes) {
        final boolean methodPresent = attributes.isPresent(InjectionTagHandler.ATTRIBUTE_METHOD);
        final boolean propertyPresent = attributes.isPresent(InjectionTagHandler.ATTRIBUTE_PROPERTY);

        if (methodPresent && propertyPresent) {
            // TODO: throw a custom exception here.
            throw new IllegalArgumentException("The \"method\" and \"property\" attributes are mutually exclusive!");
        }

        if (methodPresent) {
            this.type = InjectionType.METHOD;
            this.targetName = attributes.getAttribute(InjectionTagHandler.ATTRIBUTE_METHOD);
        } else if (propertyPresent) {
            this.type = InjectionType.PROPERTY;
            this.targetName = attributes.getAttribute(InjectionTagHandler.ATTRIBUTE_PROPERTY);
        } else {
         // TODO: throw a custom exception here.
            throw new IllegalArgumentException("Expected \"method\" or \"property\" attribute!");
        }
    }

    @Override
    public void close() {
        final ArgumentGroup group = this.store.getGroup();
        if (this.type == InjectionType.METHOD) {
            this.builder.addMethodInjection(this.targetName, group);
        } else {
            this.builder.setPropertyInjection(this.targetName, group);
        }

        this.builder = null;
    }

    private static enum InjectionType {
        METHOD, PROPERTY
    }

    @Override
    public void setContext(final TagContext context) {
        this.builder = context.getPropagatedAttribute(BaseBeanTagHandler.SHARED_BUILDER, BeanBuilder.class);
        this.store = new SimpleArgumentStore();
        context.propagateAttributeDown(ArgumentStore.OBJECT_ARGUMENT_STORE, this.store);
    }

    @Override
    public void handleCharacterData(final String data) {
    }

}
