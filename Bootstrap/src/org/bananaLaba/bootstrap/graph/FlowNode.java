package org.bananaLaba.bootstrap.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.bananaLaba.bootstrap.common.Converter;

public class FlowNode<Id, C> {

    private Map<Id, FlowNode<Id, C>> childMap = new HashMap<>();

    private Id id;
    private C content;

    public FlowNode() {
    }

    public FlowNode(final C content) {
        this.content = content;
    }

    public void addChild(final FlowNode<Id, C> child) {
        this.childMap.put(child.id, child);
    }

    public void addChildren(final Collection<FlowNode<Id, C>> children) {
        for (final FlowNode<Id, C> child : children) {
            this.addChild(child);
        }
    }

    public void removeChild(final FlowNode<Id, C> child) {
        this.childMap.remove(child.getId());
    }

    public Id getId() {
        return this.id;
    }

    public void setId(final Id id) {
        this.id = id;
    }

    public C getContent() {
        return this.content;
    }

    public void setContent(final C content) {
        this.content = content;
    }

    public boolean containsChild(final Id id) {
        return this.childMap.containsKey(id);
    }

    public FlowNode<Id, C> getChild(final Id id) {
        return this.childMap.get(id);
    }

    public Map<Id, FlowNode<Id, C>> getChildMap() {
        return new HashMap<>(this.childMap);
    }

    public void clearChildren() {
        this.childMap.clear();
    }

    public <Id2, C2> FlowNode<Id2, C2> convert(final Converter<Id, Id2> idConverter,
            final Converter<C, C2> contentConverter) {
        final Set<FlowNode<Id, C>> drilledNodes = new HashSet<>();
        final Map<FlowNode<Id, C>, FlowNode<Id2, C2>> createdNodes = new HashMap<>();

        final Stack<FlowNode<Id, C>> sourceStack = new Stack<>();
        sourceStack.push(this);

        final Stack<FlowNode<Id2, C2>> targetStack = new Stack<>();
        final FlowNode<Id2, C2> result = new FlowNode<>();
        result.setId(idConverter.convert(this.id));
        result.setContent(contentConverter.convert(this.content));
        targetStack.push(result);

        createdNodes.put(this, result);

        while (!sourceStack.isEmpty()) {
            final FlowNode<Id, C> currentSource = sourceStack.pop();
            final FlowNode<Id2, C2> currentTarget = targetStack.pop();
            if (drilledNodes.contains(currentSource)) {
                continue;
            } else {
                drilledNodes.add(currentSource);

                final Collection<FlowNode<Id, C>> sourceChildren = currentSource.childMap.values();
                for (final FlowNode<Id, C> sourceChild : sourceChildren) {
                    sourceStack.push(sourceChild);

                    FlowNode<Id2, C2> targetChild = createdNodes.get(sourceChild);
                    if (targetChild == null) {
                        targetChild = new FlowNode<>();
                        targetChild.setId(idConverter.convert(sourceChild.id));
                        targetChild.setContent(contentConverter.convert(sourceChild.content));

                        createdNodes.put(sourceChild, targetChild);
                    }

                    currentTarget.addChild(targetChild);
                    targetStack.push(targetChild);
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.id).append(" => {\r\n");
        final Set<FlowNode<Id, C>> drilledNodes = new HashSet<>();

        final Stack<Iterator<FlowNode<Id, C>>> iteratorStack = new Stack<>();
        iteratorStack.push(this.childMap.values().iterator());
        while (!iteratorStack.isEmpty()) {
            final Iterator<FlowNode<Id, C>> currentIterator = iteratorStack.peek();
            final int stackDepth = iteratorStack.size();
            if (currentIterator.hasNext()) {
                final FlowNode<Id, C> currentNode = currentIterator.next();
                for (int i = 0; i < stackDepth; i++) {
                    builder.append("    ");
                }

                if (drilledNodes.contains(currentNode)) {
                    builder.append(currentNode.id).append(" => ...\r\n");
                } else {
                    builder.append(currentNode.id).append(" => {\r\n");
                    drilledNodes.add(currentNode);
                    iteratorStack.push(currentNode.childMap.values().iterator());
                }
            } else {
                for (int i = 0; i < stackDepth - 1; i++) {
                    builder.append("    ");
                }
                builder.append("}\r\n");

                iteratorStack.pop();
            }
        }

        return builder.toString();
    }

}
