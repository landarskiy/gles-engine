package org.bananaLaba.bootstrap.xml.tagModel.tree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.bananaLaba.bootstrap.graph.FlowNode;
import org.bananaLaba.bootstrap.xml.TagLogics;
import org.bananaLaba.bootstrap.xml.TagLogicsIterator;

// TODO: add feature of taf stack trace printing in exceptions to ease debugging.
public class StructuredTagLogicsIterator implements TagLogicsIterator {

    private Stack<TagInstance> instanceStack = new Stack<>();
    private FlowNode<String, TagPrototype> prototypeNode;
    private FlowNode<String, TagRuntimeController> controllerNode;

    private Map<String, Object> globalMap = new HashMap<>();

    public StructuredTagLogicsIterator(final FlowNode<String, TagPrototype> rootPrototype) {
        this.prototypeNode = rootPrototype;
        this.controllerNode = StructuredTagLogicsIterator.convertNode(this.prototypeNode);
    }

    private void loadDirectChildControllers() {
        final Collection<FlowNode<String, TagPrototype>> childPrototypes = this.prototypeNode.getChildMap().values();
        for (final FlowNode<String, TagPrototype> childPrototype : childPrototypes) {
            this.controllerNode.addChild(StructuredTagLogicsIterator.convertNode(childPrototype));
        }
    }

    private static FlowNode<String, TagRuntimeController> convertNode(
            final FlowNode<String, TagPrototype> prototypeNode) {
        final FlowNode<String, TagRuntimeController> controllerNode = new FlowNode<>();
        controllerNode.setId(prototypeNode.getId());
        controllerNode.setContent(new TagRuntimeController(prototypeNode.getContent()));

        return controllerNode;
    }

    @Override
    public TagLogics enterTag(final String uri, final String name) {
        final String qualifiedName = StructuredTagLogicsIterator.getQualifiedName(uri, name);
        boolean started = !this.instanceStack.isEmpty();
        final FlowNode<String, TagRuntimeController> parentControllerNode = this.controllerNode;
        final FlowNode<String, TagPrototype> parentPrototypeNode = this.prototypeNode;

        SimpleTagContext context = null;
        if (started) {
            this.prototypeNode = this.prototypeNode.getChild(qualifiedName);
            if (this.prototypeNode == null) {
                // TODO: throw a custom exception here.
                throw new IllegalStateException("The \"" + qualifiedName + "\" nodes are not allowed inside the \""
                        + this.controllerNode.getId() + "\" nodes!");
            }

            this.controllerNode = this.controllerNode.getChild(qualifiedName);

            context = new SimpleTagContext(this.instanceStack.peek().getContext().getPropagatedAttributes());
        } else if (!this.controllerNode.getId().equals(qualifiedName)) {
            // TODO: throw a custom exception here.
            throw new IllegalStateException("Expected a root \"" + qualifiedName + "\"!");
        } else {
            context = new SimpleTagContext(null);
        }

        final TagRuntimeController controller = this.controllerNode.getContent();
        this.loadDirectChildControllers();

        ExtendedTagHandler handler = null;
        try {
            handler = controller.onTagOpen();
        } catch (TagStructureViolationException e) {
            // TODO: throw a custom exception here.
            throw new IllegalStateException("Structure violation with the \"" + qualifiedName + "\" tag!", e);
        }

        final TagInstance tagInstance = new TagInstance();
        tagInstance.setContext(context);
        tagInstance.setHandler(handler);
        tagInstance.setParentController(parentControllerNode);
        tagInstance.setParentProtoype(parentPrototypeNode);
        handler.setContext(context);
        this.instanceStack.push(tagInstance);

        return handler;
    }

    @Override
    public void leaveTag() {
        final Set<Entry<String, FlowNode<String, TagRuntimeController>>> childEntries =
                this.controllerNode.getChildMap().entrySet();
        for (final Entry<String, FlowNode<String, TagRuntimeController>> childEntry : childEntries) {
            try {
                childEntry.getValue().getContent().onParentTagClose();
            } catch (TagStructureViolationException e) {
                final String qualifiedName = childEntry.getKey();
                throw new IllegalStateException("Structure violation for \"" + qualifiedName + "\"!", e);
            }
        }

        final TagInstance instance = this.instanceStack.pop();
        instance.getHandler().close();
        this.controllerNode.clearChildren();
        this.controllerNode = instance.getParentController();
        this.prototypeNode = instance.getParentProtoype();
    }

    public static String getQualifiedName(final String uri, final String name) {
        if ((uri == null) || uri.isEmpty()) {
            return name;
        } else {
            return String.format("%s:%s", uri, name);
        }
    }

    @Override
    public void enterNamespace(final String uri, final String prefix) {
    }

    @Override
    public void leaveNamespace(final String prefix) {
    }

    private class SimpleTagContext extends BaseTagContext {

        public SimpleTagContext(final Map<String, Object> parentAttributes) {
            super(parentAttributes);
        }

        @Override
        public TagController getChildController(final String uri, final String name) {
            final String qualifiedName = StructuredTagLogicsIterator.getQualifiedName(uri, name);
            final FlowNode<String, TagRuntimeController> childNode =
                    StructuredTagLogicsIterator.this.controllerNode.getChild(qualifiedName);
            if (childNode == null) {
                // TODO: throw a custom exception here.
                throw new IllegalStateException("The \"" + qualifiedName + "\" nodes are not allowed inside the \""
                        + StructuredTagLogicsIterator.this.controllerNode.getId() + "\" nodes!");
            }

            return childNode.getContent();
        }

        @Override
        public void setGlobal(final String name, final Object value) {
            StructuredTagLogicsIterator.this.globalMap.put(name, value);
        }

        @SuppressWarnings("unchecked")
        @Override
        public<T> T getGlobal(final String name, final Class<T> type) {
            return (T) StructuredTagLogicsIterator.this.globalMap.get(name);
        }

    }

}
