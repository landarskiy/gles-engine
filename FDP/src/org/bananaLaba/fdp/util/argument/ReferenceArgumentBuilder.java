package org.bananaLaba.fdp.util.argument;

import org.bananaLaba.fdp.mapping.ContextReference;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.ReferenceArgumentSpecification;

public class ReferenceArgumentBuilder implements XMLArgumentBuilder<ReferenceArgumentSpecification> {

    @Override
    public XMLProcessorArgument<?> build(final ReferenceArgumentSpecification specification) {
        final ContextReference beanArgument = new ContextReference(specification.getSource());
        beanArgument.setBeanName(specification.getKey());
        beanArgument.setTypeHint(specification.getTypeHint());

        return beanArgument;
    }

}
