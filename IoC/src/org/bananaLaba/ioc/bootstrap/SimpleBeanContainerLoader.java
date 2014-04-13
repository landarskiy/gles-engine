package org.bananaLaba.ioc.bootstrap;

import java.io.IOException;
import java.io.InputStream;

import org.bananaLaba.bootstrap.common.InstancePrototype;
import org.bananaLaba.bootstrap.common.Prototype;
import org.bananaLaba.bootstrap.xml.BootstrapProcessor;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.StructuredTagModel;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagPrototype;
import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.BeanContainerLoader;
import org.bananaLaba.ioc.simple.builder.SimpleBeanContainerFactory;

public class SimpleBeanContainerLoader implements BeanContainerLoader {

    private BootstrapProcessor bootstrapper = new BootstrapProcessor();
    private StructuredTagModel tagModel = new StructuredTagModel(null, IoCTagConstants.TAG_BEANS);
    private BeanContainerFactory containerFactory = new SimpleBeanContainerFactory();

    public SimpleBeanContainerLoader() {
        this.tagModel.setRootHandlerPrototype(InstancePrototype.prepare(new BeansTagHandler()));

        TagPrototype specification = new TagPrototype();
        final ExtendedTagHandler beanHandler = new BeanTagHandler(this.containerFactory);
        specification.setHandlerPrototype(InstancePrototype.prepare(beanHandler));
        final StructuredTagModel beanModel = this.tagModel.addChild(null, IoCTagConstants.TAG_BEAN, specification);

        specification = new TagPrototype();
        specification.setHandlerPrototype(new Prototype<ExtendedTagHandler>() {

            @Override
            public ExtendedTagHandler create() {
                return new ConstructionTagHandler();
            }

        });
        specification.setMaxCount(1);
        final StructuredTagModel constructionModel =
                beanModel.addChild(null, IoCTagConstants.TAG_CONSTRUCTION, specification);

        specification = new TagPrototype();
        specification.setHandlerPrototype(new Prototype<ExtendedTagHandler>() {

            @Override
            public ExtendedTagHandler create() {
                return new InjectionTagHandler();
            }

        });
        final StructuredTagModel injectionModel =
                beanModel.addChild(null, IoCTagConstants.TAG_INJECTION, specification);

        final TagPrototype argumentSpecification = new TagPrototype();
        argumentSpecification.setHandlerPrototype(new Prototype<ExtendedTagHandler>() {

            @Override
            public ExtendedTagHandler create() {
                return new ArgumentTagHandler();
            }

        });
        StructuredTagModel argumentModel = constructionModel.addChild(null, IoCTagConstants.TAG_ARGUMENT, argumentSpecification);

        specification = new TagPrototype();
        specification.setHandlerPrototype(new Prototype<ExtendedTagHandler>() {

            @Override
            public ExtendedTagHandler create() {
                return new AnonymousBeanTagHandler(SimpleBeanContainerLoader.this.containerFactory);
            }

        });
        argumentModel.addChild(null, IoCTagConstants.TAG_BEAN, specification).addChildren(beanModel);

        argumentModel = injectionModel.addChild(null, IoCTagConstants.TAG_ARGUMENT, argumentSpecification);
        argumentModel.addChild(null, IoCTagConstants.TAG_BEAN, specification).addChildren(beanModel);
    }

    @Override
    public BeanContainer load(final InputStream stream) throws IOException {
        this.bootstrapper.process(stream, this.tagModel.getIterator());
        return this.containerFactory.getContainerInstance();
    }

}
