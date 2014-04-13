package org.bananaLaba.bootstrap.xml.tagModel.tree;

import java.util.Map;

import org.bananaLaba.bootstrap.common.Prototype;
import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.bootstrap.xml.TagLogicsIterator;
import org.bananaLaba.bootstrap.xml.tagModel.TagModel;

// TODO: use some graph implementation that supports global node id's, local node id's and arch additional info (for
// storing tag nesting cardinality and tag nesting blocking flags) to describe structured tag model.
public class StructuredTagModel implements TagModel {

    private FlowNode<String, TagPrototype> prototypeNode;

    public StructuredTagModel(final String uri, final String name) {
        this.prototypeNode = new FlowNode<>();
        this.prototypeNode.setId(StructuredTagLogicsIterator.getQualifiedName(uri, name));

        final TagPrototype prototype = new TagPrototype();
        this.prototypeNode.setContent(prototype);
    }

    public StructuredTagModel(final FlowNode<String, TagPrototype> rootPrototype) {
        this.prototypeNode = rootPrototype;
    }

    public void setRootHandlerPrototype(final Prototype<? extends ExtendedTagHandler> prototype) {
        this.prototypeNode.getContent().setHandlerPrototype(prototype);
    }

    public StructuredTagModel addChild(final String uri, final String name, final TagPrototype prototype) {
        final String qualifiedName = StructuredTagLogicsIterator.getQualifiedName(uri, name);
        final FlowNode<String, TagPrototype> childPrototypeNode = new FlowNode<>();
        childPrototypeNode.setId(qualifiedName);
        childPrototypeNode.setContent(prototype);
        this.prototypeNode.addChild(childPrototypeNode);

        return new StructuredTagModel(childPrototypeNode);
    }

    public void addChildren(final StructuredTagModel model) {
        final Map<String, FlowNode<String, TagPrototype>> children = model.prototypeNode.getChildMap();
        this.prototypeNode.addChildren(children.values());
    }

    @Override
    public TagLogicsIterator getIterator() {
        return new StructuredTagLogicsIterator(this.prototypeNode);
    }


}
