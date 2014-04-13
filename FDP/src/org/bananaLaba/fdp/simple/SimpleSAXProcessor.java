package org.bananaLaba.fdp.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bananaLaba.bootstrap.common.Converter;
import org.bananaLaba.bootstrap.common.Prototype;
import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.bootstrap.xml.BootstrapProcessor;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.StructuredTagLogicsIterator;
import org.bananaLaba.bootstrap.xml.tagModel.tree.StructuredTagModel;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagPrototype;
import org.bananaLaba.fdp.QualifiedName;
import org.bananaLaba.fdp.TagSpecification;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.simple.tagModel.ScenarioTagHandler;
import org.bananaLaba.ioc.BeanContainer;

// TODO: add support for default attribute values.
// TODO: add support for conditionals that deliver the feature of dynamic attribute value-dependent action choice.
// TODO: add nested scenario support to ease alternate tag-to-action mapping for different nesting configurations of
// the same tags. Also beans called within these nested scenarios should be able to have child-parent communications.
public class SimpleSAXProcessor implements XMLProcessor {

    private BeanContainer container;
    private Map<String, List<ActionHelper>> helperMap = new HashMap<>();
    private BootstrapProcessor bootstrapper = new BootstrapProcessor();
    private StructuredTagModel tagModel;
    // TODO: make something with this context: it should be re-created for each "process" call and there must be
    // possibility to copy helpers somehow.
    private SAXProcessorContext context = new SAXProcessorContext(new BeanContainer() {

        @Override
        public <T> T getBean(final String name, final Class<T> type) {
            return SimpleSAXProcessor.this.container.getBean(name, type);
        }

        @Override
        public Class<?> getBeanType(final String name) {
            return SimpleSAXProcessor.this.container.getBeanType(name);
        }

        @Override
        public boolean hasBean(final String name) {
            return SimpleSAXProcessor.this.container.hasBean(name);
        }

    }, this.helperMap);

    @Override
    public void process(final InputStream xmlStream) throws IOException {
        this.bootstrapper.process(xmlStream, this.tagModel.getIterator());
        this.context.clear();
    }

    @Override
    public void setBeanContainer(final BeanContainer container) {
        this.container = container;
    }

    public void setScenarioTree(final FlowNode<QualifiedName, TagSpecification> specificationRoot) {
        final Converter<QualifiedName, String> idConverter = new Converter<QualifiedName, String>() {

            @Override
            public String convert(final QualifiedName value) {
                return StructuredTagLogicsIterator.getQualifiedName(value.getUri(), value.getName());
            }

        };
        final Converter<TagSpecification, TagPrototype> contentConverter =
                new Converter<TagSpecification, TagPrototype>() {

                @Override
                public TagPrototype convert(final TagSpecification value) {
                    final TagPrototype prototype = new TagPrototype();
                    prototype.setMinCount(value.getMinCount());
                    prototype.setMaxCount(value.getMaxCount());

                    final Prototype<ExtendedTagHandler> handlerPrototype = new Prototype<ExtendedTagHandler>() {

                        @Override
                        public ExtendedTagHandler create() {
                            final ScenarioTagHandler handler = new ScenarioTagHandler();
                            handler.setScenarioContext(SimpleSAXProcessor.this.context);
                            handler.setScenarioId(value.getScenarioId());

                            return handler;
                        }

                    };
                    prototype.setHandlerPrototype(handlerPrototype);

                    return prototype;
                }

        };
        final FlowNode<String, TagPrototype> modelRoot = specificationRoot.convert(idConverter, contentConverter);
        this.tagModel = new StructuredTagModel(modelRoot);
    }

    public BeanContainer getBeanContainer() {
        return this.container;
    }

    public void bindScenario(final String id, final List<ActionHelper> helpers) {
        // TODO: not ideal in the meaning, that items directly from the method parameter are taken.
        // Maybe some kind of cloning?
        for (final ActionHelper helper : helpers) {
            helper.bind(this.context);
        }

        this.helperMap.put(id, new ArrayList<>(helpers));
    }

}
