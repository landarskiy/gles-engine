package org.bananaLaba.fdp.simple.bootstrap;

import java.util.Map;

import org.bananaLaba.fdp.BeanCallBuilder;

public class CallArgumentTagHandler extends ArgumentTagHandler {

    private BeanCallBuilder builder;

    public CallArgumentTagHandler(final BeanCallBuilder builder, final Map<String, String> attributeProjection) {
        super(attributeProjection);
        this.builder = builder;
    }

    @Override
    protected void commitAttributeArgument(final String name, final Class<?> type, final Class<?> typeHint) {
        this.builder.addSimpleArgument(name, type, typeHint);
    }

    @Override
    protected void commitBeanArgument(final String name, final Class<?> typeHint) {
        this.builder.addBeanArgument(name, typeHint);
    }

    @Override
    protected void commitStoreArgument(final String name, final Class<?> typeHint) {
        this.builder.addStoreArgument(name, typeHint);
    }

    @Override
    protected void commitProjectionArgument(final Class<?> targetType, final Class<?> typeHint,
            final Map<String, String> attributeProjection) {
        if (typeHint != null) {
            this.builder.addProjectionArgument(targetType, typeHint, attributeProjection);
        } else {
            this.builder.addProjectionArgument(targetType, attributeProjection);
        }
    }

    @Override
    protected void commitSimpleValue(final Object value, final Class<?> typeHint) {
        this.builder.addStaticArgument(value, typeHint);
    }

}
