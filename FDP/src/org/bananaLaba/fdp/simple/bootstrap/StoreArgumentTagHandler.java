package org.bananaLaba.fdp.simple.bootstrap;

import java.util.Map;

import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.fdp.ScenarioBuilder;

public class StoreArgumentTagHandler extends ArgumentTagHandler {

    private ScenarioBuilder builder;
    private String storeKey;

    public StoreArgumentTagHandler(final ScenarioBuilder builder, final Map<String, String> attributeProjection) {
        super(attributeProjection);
        this.builder = builder;
    }

    @Override
    protected void commitAttributeArgument(final String name, final Class<?> type, final Class<?> typeHint) {
        this.builder.addStoreAttributeAction(name, type, this.storeKey);
    }

    @Override
    protected void commitBeanArgument(final String name, final Class<?> typeHint) {
        this.builder.addStoreBeanAaction(name, this.storeKey);
    }

    @Override
    protected void commitStoreArgument(final String name, final Class<?> typeHint) {
        // FIXME: review this question with the feature of duplicating stored values.
        throw new RuntimeException("A stored value cannot be stored again!");
    }

    @Override
    protected void commitProjectionArgument(final Class<?> targetType, final Class<?> typeHint,
            final Map<String, String> attributeProjection) {
        this.builder.addStoreProjectionAction(targetType, attributeProjection, this.storeKey);
    }

    @Override
    public void setContext(final TagContext context) {
        super.setContext(context);
        this.storeKey = context.getPropagatedAttribute(StoreTagHandler.ATTRIBUTE_STORE_KEY, String.class);
    }

    @Override
    protected void commitStaticValue(final Object value, final Class<?> typeHint) {
        // FIXME: review this question with storing static values.
        throw new RuntimeException("A simple value cannot be stored!");
    }

    @Override
    protected void commitClassConstant(final Class<?> sourceType, final String name, final Class<?> typeHint) {
        // FIXME: review this question with storing class constants.
        throw new RuntimeException("A class constant cannot be stored!");
    }

    @Override
    public void handleCharacterData(final String data) {
    }

}
