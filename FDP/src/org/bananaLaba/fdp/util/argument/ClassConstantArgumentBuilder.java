package org.bananaLaba.fdp.util.argument;

import org.bananaLaba.fdp.mapping.ClassConstantSource;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.ClassConstantSpecification;

public class ClassConstantArgumentBuilder implements XMLArgumentBuilder<ClassConstantSpecification> {

    @Override
    public XMLProcessorArgument<?> build(final ClassConstantSpecification specification) {
        final ClassConstantSource source = new ClassConstantSource();
        source.setAttributeName(specification.getConstantName());
        source.setSourceType(specification.getSourceType());
        source.setTypeHint(specification.getTypeHint());

        return source;
    }

}
