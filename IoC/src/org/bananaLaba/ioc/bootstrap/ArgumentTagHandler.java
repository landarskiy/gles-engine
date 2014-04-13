package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.bootstrap.common.Converter;
import org.bananaLaba.bootstrap.common.TypeHintUtils;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagController;
import org.bananaLaba.ioc.injection.SimpleArgument;

public class ArgumentTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_TYPE_HINT = "typeHint";
    private static final String ATTRIBUTE_BEAN_NAME = "beanName";

    private TagContext context;
    private ArgumentStore store;

    @Override
    public void close() {
    }

    @Override
    public void handle(final AttributeMap attributes) {
        final boolean isBean = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_BEAN_NAME);
        final boolean isValue = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_VALUE);

        if (isBean && isValue) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("None or only one of the following attributes"
                    + " must be present in the \"argument\" tag: beanName, value!");
        }

        if (isBean || isValue) {
            this.context.getChildController(null, IoCTagConstants.TAG_BEAN).setBlocked(true);
        } else {
            TagController controller = this.context.getChildController(null, IoCTagConstants.TAG_BEAN);
            controller.setMinCount(1);
            controller.setMaxCount(1);

            this.context.propagateAttributeDown(ArgumentStore.OBJECT_ARGUMENT_STORE, this.store);
        }

        Class<?> typeHint = null;
        if (attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE_HINT)) {
            final String typeHintName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE_HINT);
            typeHint = TypeHintUtils.getPrimitiveType(typeHintName);
            if (typeHint == null) {
                try {
                    typeHint = Class.forName(typeHintName);
                } catch (ClassNotFoundException e) {
                    // TODO throw a custom exception here.
                    throw new RuntimeException("Invalid type hint \"" + typeHintName
                            + "\" - this class is not found!");
                }
            }
        }

        Class<?> type = null;
        if (isValue) {
            type = String.class;
            if (attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE)) {
                final String attributeTypeName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE);
                type = TypeHintUtils.getPrimitiveType(attributeTypeName);
                if (type == null) {
                    try {
                        type = Class.forName(attributeTypeName);
                    } catch (ClassNotFoundException e) {
                     // TODO throw a custom exception here.
                        throw new RuntimeException("Invalid attribute converiosn type \"" + attributeTypeName
                                + "\" - this class is not found!");
                    }
                }
            }

            if (typeHint == null) {
                typeHint = type;
            }
        } else {
            if (attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE)) {
                // TODO: throw a custom exception here.
                throw new IllegalStateException(
                        "The \"type\" attribute is available only in pair with the \"value\" attribute!");
            }
        }

        if (isValue) {
            final String rawValue = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_VALUE);
            final Converter<String, ?> converter = ConversionUtils.getStringConverter(type);
            final Object value = converter.convert(rawValue);

            final SimpleArgument<Object> argument = new SimpleArgument<Object>(typeHint, value);
            this.store.addPrimitive(argument);
        } else if (isBean) {
            final String beanName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_BEAN_NAME);
            final SimpleArgument<String> argument = new SimpleArgument<String>(typeHint, beanName);
            this.store.addReference(argument);
        } else {
            this.context.propagateAttributeDown(ArgumentTagHandler.ATTRIBUTE_TYPE_HINT, typeHint);
        }
    }

    @Override
    public void setContext(final TagContext context) {
        this.context = context;
        this.store = this.context.getPropagatedAttribute(ArgumentStore.OBJECT_ARGUMENT_STORE, ArgumentStore.class);
    }

}
