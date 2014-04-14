package org.bananaLaba.fdp.util.argument;

import org.bananaLaba.bootstrap.common.BaseStandardFactory;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.ArgumentSpecification;
import org.bananaLaba.fdp.scenario.AttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.ClassConstantSpecification;
import org.bananaLaba.fdp.scenario.CompositeAttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.ReferenceArgumentSpecification;
import org.bananaLaba.fdp.scenario.StaticArgumentSpecification;

public class XMLArgumentFactory extends BaseStandardFactory<ArgumentSpecification, XMLProcessorArgument<?>> {

    private static XMLArgumentFactory instance;

    private XMLArgumentFactory() {
        this.bindBuilder(ReferenceArgumentSpecification.class, new ReferenceArgumentBuilder());
        this.bindBuilder(AttributeArgumentSpecification.class, new AttributeArgumentBuilder());
        this.bindBuilder(CompositeAttributeArgumentSpecification.class, new CompositeAttributeArgumentBuilder());
        this.bindBuilder(StaticArgumentSpecification.class, new StaticArgumentBuilder());
        this.bindBuilder(ClassConstantSpecification.class, new ClassConstantArgumentBuilder());
    }

    public static XMLArgumentFactory getInstance() {
        if (XMLArgumentFactory.instance == null) {
            XMLArgumentFactory.instance = new XMLArgumentFactory();
        }

        return XMLArgumentFactory.instance;
    }

}
