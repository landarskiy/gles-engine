package org.bananaLaba.fdp.util.argument;

import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.bootstrap.common.Converter;
import org.bananaLaba.fdp.mapping.AttributeSource;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.AttributeArgumentSpecification;

public class AttributeArgumentBuilder implements XMLArgumentBuilder<AttributeArgumentSpecification> {

    @SuppressWarnings("unchecked")
    @Override
    public XMLProcessorArgument<?> build(final AttributeArgumentSpecification specification) {
        final AttributeSource<Object> attributeArgument = new AttributeSource<>();
        attributeArgument.setAttributeName(specification.getAttributeName());
        attributeArgument.setConverter((Converter<String, Object>)
                ConversionUtils.getStringConverter(specification.getTargetType()));
        attributeArgument.setTypeHint(specification.getTypeHint());

        return attributeArgument;
    }

}
