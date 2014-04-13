package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.injection.SimpleArgument;

public class AnonymousBeanTagHandler extends BaseBeanTagHandler {

    private ArgumentStore store;
    private String generatedName;
    private Class<?> typeHint;

    public AnonymousBeanTagHandler(final BeanContainerFactory containerFactory) {
        super(containerFactory);
    }

    @Override
    protected String getBeanName(final AttributeMap attributes) {
        if (attributes.isPresent(BaseBeanTagHandler.ATTRIBUTE_NAME)) {
            // TODO: throw a custom exception here.
            throw new IllegalArgumentException("Anonymous beans cannot have a name!");
        }

        this.generatedName = ContainerConfigurationUtils.getAnonymousBeanName("", this.hashCode());
        return this.generatedName;
    }

    @Override
    protected void onCheckCategory(final BeanCategory category) {
        if (category == BeanCategory.TEMPLATE) {
            // TODO: throw a custom exception here.
            throw new IllegalStateException("An anonymous bean cannot be a template!");
        }
    }

    @Override
    protected boolean canBeInherited() {
        return false;
    }

    @Override
    public void setContext(final TagContext context) {
        super.setContext(context);
        this.store = context.getPropagatedAttribute(ArgumentStore.OBJECT_ARGUMENT_STORE, ArgumentStore.class);
        this.typeHint = context.getPropagatedAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE_HINT, Class.class);
    }

    @Override
    public void close() {
        super.close();

        final SimpleArgument<String> argument = new SimpleArgument<>();
        argument.setValue(this.generatedName);
        argument.setTypeHint(this.typeHint);

        this.store.addReference(argument);
    }

}
