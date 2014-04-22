package org.bananaLaba.fdp.simple.bootstrap;

import java.util.Map;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.bootstrap.common.TypeHintUtils;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;

// TODO: re-design the "argument" tag in the following way:
// 1) add "source" attribute - enum of "attribute", "bean", "call" (for usage with a nested "call" tag), "store"
// (for usage with nested "key" tag), "classConstant". I.e. all additional parameters like store keys, store scopes and
// bean element attributes should be specified via corresponding nested tags;
// 2) add "class" abbreviation in the "type" attribute to be able to convert a string value to a loaded class.
public abstract class ArgumentTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_TYPE_HINT = "typeHint";

    private static final String ATTRIBUTE_BEAN_NAME = "beanName";
    private static final String ATTRIBUTE_ATTRIBUTE = "attribute";
    private static final String ATTRIBUTE_STORE_KEY = "storeKey";
    private static final String ATTRIBUTE_CONSTANT = "constant";
    private static final String ATTRIBUTE_CONSTANT_ATTRIBUTE = "constantAttribute";

    private static final String ATTRIBUTE_TYPE = "type";

    private boolean isProjection;
    private Map<String, String> attributeProjection;
    private Class<?> type;
    private Class<?> typeHint;

    private TagContext context;

    public ArgumentTagHandler(final Map<String, String> attributeProjection) {
        this.attributeProjection = attributeProjection;
    }

    @Override
    public void handle(final AttributeMap attributes) {
        final boolean isAttribute = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_ATTRIBUTE);
        final boolean isBean = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_BEAN_NAME);
        final boolean isStored = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_STORE_KEY);
        final boolean isConstant = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_CONSTANT);
        final boolean isConstantAttribute = attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_CONSTANT_ATTRIBUTE);
        this.isProjection = !(isAttribute || isBean || isStored || isConstant || isConstantAttribute);

        int checkSum = 0;
        if (isAttribute) {
            checkSum++;
        }
        if (isBean) {
            checkSum++;
        }
        if (isStored) {
            checkSum++;
        }
        if (isConstant) {
            checkSum++;
        }

        if (checkSum > 1) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("None or only one of the following attributes"
                    + " must be present in the \"argument\" tag: attribute, beanName, storeKey, constant!");
        }

        if (attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE_HINT)) {
            final String typeHintName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE_HINT);
            this.typeHint = TypeHintUtils.getPrimitiveType(typeHintName);
            if (this.typeHint == null) {
                try {
                    this.typeHint = Class.forName(typeHintName);
                } catch (ClassNotFoundException e) {
                    // TODO throw a custom exception here.
                    throw new RuntimeException("Invalid type hint \"" + typeHintName
                            + "\" - this class is not found!");
                }
            }
        }

        if (!isBean && !isStored && !isConstant && !isConstantAttribute) {
            this.type = String.class;
            if (attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE)) {
                final String attributeTypeName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE);
                this.type = TypeHintUtils.getPrimitiveType(attributeTypeName);
                if (this.type == null) {
                    try {
                        this.type = Class.forName(attributeTypeName);
                    } catch (ClassNotFoundException e) {
                        // TODO throw a custom exception here.
                        throw new RuntimeException("Invalid attribute converiosn type \"" + attributeTypeName
                                + "\" - this class is not found!");
                    }
                }
            }

            if (this.typeHint == null) {
                this.typeHint = this.type;
            }
        }

        if (isAttribute) {
            final String attributeName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_ATTRIBUTE);
            this.commitAttributeArgument(attributeName, this.type, this.typeHint);
            this.context.getChildController(null, TagConstants.TAG_PROJECTION).setBlocked(true);
        } else if (isBean) {
            final String beanName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_BEAN_NAME);
            this.commitBeanArgument(beanName, this.typeHint);
            this.context.getChildController(null, TagConstants.TAG_PROJECTION).setBlocked(true);
        } else if (isStored) {
            final String storeKey = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_STORE_KEY);
            this.commitStoreArgument(storeKey, this.typeHint);
            this.context.getChildController(null, TagConstants.TAG_PROJECTION).setBlocked(true);
        } else if (isConstant) {
            if (!attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE)) {
                // TODO throw a custom exception here.
                throw new RuntimeException("The \"type\" attribute is required with the \"constant\" attribute!");
            }

            final String typeName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE);
            Class<?> sourceType = null;
            try {
                sourceType = Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                // TODO throw a custom exception here.
                throw new RuntimeException("Invalid attribute converiosn type \"" + typeName
                        + "\" - this class is not found!");
            }

            final String constantName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_CONSTANT);
            final Object value = ConversionUtils.getConsant(constantName, sourceType);
            this.commitStaticValue(value, this.typeHint);
        } else if (isConstantAttribute) {
            if (!attributes.isPresent(ArgumentTagHandler.ATTRIBUTE_TYPE)) {
                // TODO throw a custom exception here.
                throw new RuntimeException(
                        "The \"type\" attribute is required with the \"constantAttribute\" attribute!");
            }

            final String typeName = attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_TYPE);
            Class<?> sourceType = null;
            try {
                sourceType = Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                // TODO throw a custom exception here.
                throw new RuntimeException("Invalid attribute converiosn type \"" + typeName
                        + "\" - this class is not found!");
            }

            final String constantAttributeName =
                    attributes.getAttribute(ArgumentTagHandler.ATTRIBUTE_CONSTANT_ATTRIBUTE);
            this.commitClassConstant(sourceType,constantAttributeName, this.typeHint);
        }

        this.typeHint = null;
    }

    @Override
    public void close() {
        if (this.isProjection) {
            this.commitProjectionArgument(this.type, this.typeHint, this.attributeProjection);
            this.attributeProjection.clear();
        } else if (!this.attributeProjection.isEmpty()) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("The \"projection\" elements are allowed only within projection arguments!");
        }
    }

    @Override
    public void setContext(final TagContext context) {
        this.context = context;
    }

    protected abstract void commitAttributeArgument(final String name, final Class<?> type, final Class<?> typeHint);

    protected abstract void commitBeanArgument(final String name, final Class<?> typeHint);

    protected abstract void commitStoreArgument(final String name, final Class<?> typeHint);

    protected abstract void commitProjectionArgument(final Class<?> targetType, final Class<?> typeHint,
            final Map<String, String> attributeProjection);

    protected abstract void commitStaticValue(final Object value, final Class<?> typeHint);

    protected abstract void commitClassConstant(final Class<?> sourceType, final String name, final Class<?> typeHint);

}
