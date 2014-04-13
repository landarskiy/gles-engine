package org.bananaLaba.fdp.simple.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.common.InstancePrototype;
import org.bananaLaba.bootstrap.common.Prototype;
import org.bananaLaba.bootstrap.xml.BootstrapProcessor;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.StructuredTagModel;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagPrototype;
import org.bananaLaba.fdp.BeanCallBuilder;
import org.bananaLaba.fdp.FDPFactory;
import org.bananaLaba.fdp.FDPLoader;
import org.bananaLaba.fdp.ScenarioBuilder;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.simple.SimpleSAXPFactory;
import org.bananaLaba.ioc.BeanContainer;

public class SimpleFDPLoader implements FDPLoader {

    private FDPFactory factory;
    private BootstrapProcessor bootstrapper = new BootstrapProcessor();
    private StructuredTagModel tagModel = new StructuredTagModel(null, TagConstants.TAG_PROCESSOR);

    public SimpleFDPLoader(final BeanContainer container) {
        this.factory = new SimpleSAXPFactory(container);

        this.tagModel.setRootHandlerPrototype(InstancePrototype.prepare(new ProcessorTagHandler(this.factory)));

        final ScenarioBuilder scenarioBuilder = this.factory.getScenarioBuilder();
        final ExtendedTagHandler scenarioHandler = new ScenarioTagHandler(scenarioBuilder);
        TagPrototype specification = new TagPrototype(InstancePrototype.prepare(scenarioHandler));
        final StructuredTagModel scenarioModel = this.tagModel.addChild(null, TagConstants.TAG_SCENARIO, specification);

        final Map<String, String> attributeProjection = new HashMap<>();


        final StoreTagHandler storeHandler = new StoreTagHandler();
        specification = new TagPrototype(InstancePrototype.prepare(storeHandler));
        final StructuredTagModel storeModel = scenarioModel.addChild(null, TagConstants.TAG_STORE, specification);

        final StoreArgumentTagHandler storeArgumentHandler =
                new StoreArgumentTagHandler(scenarioBuilder, attributeProjection);
        specification = new TagPrototype(InstancePrototype.prepare(storeArgumentHandler));
        specification.setMaxCount(1);
        specification.setMinCount(1);
        final StructuredTagModel storeArgumentModel =
                storeModel.addChild(null, TagConstants.TAG_ARGUMENT, specification);

        final BeanCallBuilder callBuilder = scenarioBuilder.getBeanCallBuilder();
        final ExtendedTagHandler callHandler = new CallTagHandler(callBuilder);
        specification = new TagPrototype(InstancePrototype.prepare(callHandler));
        final StructuredTagModel callModel = scenarioModel.addChild(null, TagConstants.TAG_CALL, specification);

        final ExtendedTagHandler callArgumentHandler = new CallArgumentTagHandler(callBuilder, attributeProjection);
        specification = new TagPrototype(InstancePrototype.prepare(callArgumentHandler));
        final StructuredTagModel callArgumentModel = callModel.addChild(null, TagConstants.TAG_ARGUMENT, specification);

        final ExtendedTagHandler projectionHandler = new ProjectionTagHandler(attributeProjection);
        specification = new TagPrototype(InstancePrototype.prepare(projectionHandler));

        callArgumentModel.addChild(null, TagConstants.TAG_PROJECTION, specification);
        storeArgumentModel.addChild(null, TagConstants.TAG_PROJECTION, specification);

        specification = new TagPrototype();
        specification.setHandlerPrototype(new Prototype<ExtendedTagHandler>() {

            @Override
            public ExtendedTagHandler create() {
                return new TagTagHandler();
            }

        });
        final StructuredTagModel tagTagModel = this.tagModel.addChild(null, TagConstants.TAG_TAG, specification);
        tagTagModel.addChildren(tagTagModel);
    }

    @Override
    public XMLProcessor load(final InputStream xmlStream) throws IOException {
        this.bootstrapper.process(xmlStream, this.tagModel.getIterator());
        return this.factory.getProcessorInstance();
    }

}
