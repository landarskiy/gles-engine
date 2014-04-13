package org.bananaLaba.bootstrap.xml.tagModel.tree;

import org.bananaLaba.bootstrap.graph.FlowNode;

public class TagInstance {

    private ExtendedTagHandler handler;
    private BaseTagContext context;
    private FlowNode<String, TagRuntimeController> parentController;
    private FlowNode<String, TagPrototype> parentProtoype;

    public FlowNode<String, TagRuntimeController> getParentController() {
        return this.parentController;
    }

    public void setParentController(final FlowNode<String, TagRuntimeController> parentController) {
        this.parentController = parentController;
    }

    public ExtendedTagHandler getHandler() {
        return this.handler;
    }

    public void setHandler(final ExtendedTagHandler handler) {
        this.handler = handler;
    }

    public BaseTagContext getContext() {
        return this.context;
    }

    public void setContext(final BaseTagContext context) {
        this.context = context;
    }

    public FlowNode<String, TagPrototype> getParentProtoype() {
        return this.parentProtoype;
    }

    public void setParentProtoype(final FlowNode<String, TagPrototype> parentProtoype) {
        this.parentProtoype = parentProtoype;
    }

}
