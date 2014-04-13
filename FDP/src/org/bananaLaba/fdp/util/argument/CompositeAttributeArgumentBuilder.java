package org.bananaLaba.fdp.util.argument;

import java.util.Map.Entry;
import java.util.Set;

import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.fdp.mapping.DataBeanSource;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.mapping.bean.BeanReification;
import org.bananaLaba.fdp.scenario.CompositeAttributeArgumentSpecification;

public class CompositeAttributeArgumentBuilder implements XMLArgumentBuilder<CompositeAttributeArgumentSpecification> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public XMLProcessorArgument<?> build(final CompositeAttributeArgumentSpecification specification) {
        final DataBeanSource beanArgument = new DataBeanSource();
        beanArgument.setTypeHint(specification.getTypeHint());

        final BeanReification reification = new BeanReification(specification.getTargetType());
        beanArgument.setBeanBuilder(reification);

        final Set<Entry<String, String>> propertyEntries = specification.getPropertyNameMap().entrySet();
        for (final Entry<String, String> propertyEntry : propertyEntries) {
            final String propertyName = propertyEntry.getKey();
            final Class<?> propertyType = reification.getPropertyType(propertyName);
            beanArgument.addAttribute(propertyName, propertyEntry.getValue(),
                    ConversionUtils.getStringConverter(propertyType));
        }

        return beanArgument;
    }

}
