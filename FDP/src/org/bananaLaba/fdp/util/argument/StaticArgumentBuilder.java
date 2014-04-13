package org.bananaLaba.fdp.util.argument;

import org.bananaLaba.fdp.mapping.StaticSource;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.StaticArgumentSpecification;

public class StaticArgumentBuilder implements XMLArgumentBuilder<StaticArgumentSpecification> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public XMLProcessorArgument<?> build(final StaticArgumentSpecification specification) {
        final StaticSource source = new StaticSource();
        source.setTypeHint(specification.getTypeHint());
        source.setValue(specification.getValue());

        return source;
    }

}
