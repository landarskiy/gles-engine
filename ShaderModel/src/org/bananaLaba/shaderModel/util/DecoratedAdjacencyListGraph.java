package org.bananaLaba.shaderModel.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.bootstrap.graph.AdjacencyListGraph;
import org.bananaLaba.bootstrap.graph.GraphIterator;

public class DecoratedAdjacencyListGraph<Id, Content> extends AdjacencyListGraph<Id, DecoratedGraphNode<Id, Content>>
        implements DecoratedGraph<Id, Content> {

    private Map<Id, DecoratedGraphNode<Id, Content>> nodeMap = new HashMap<>();

    @Override
    public DecoratedGraphNode<Id, Content> getNode(final Id id) {
        return this.nodeMap.get(id);
    }

    @Override
    protected DecoratedGraphNode<Id, Content> createNode(final Id id, final Collection<Id> adjacencyList) {
        final DecoratedGraphNode<Id, Content> node = new SimpleDecoratedGraphNode(id, adjacencyList);
        this.nodeMap.put(id, node);
        return node;
    }

    @Override
    protected void removeNodeData(final Id id) {
        this.nodeMap.remove(id);
    }

    @Override
    public void clear() {
        for (final Id id : this.nodeMap.keySet()) {
            this.removeNode(id);
        }
    }

    private class SimpleDecoratedGraphNode implements DecoratedGraphNode<Id, Content> {

     // ========================================================================
        // Fields
        // ========================================================================
        /**
         * The node id.
         */
        private final Id id;
        /**
         * The node adjacency list.
         */
        private final Collection<Id> adjacencyList;

        private Content content;

        // ========================================================================
        // Constructors
        // ========================================================================
        /**
         * Creates a simple node with the given id and the given adjacency list. Note that the adjacency list is not
         * copied but taken by reference.
         * @param id the id value
         * @param adjacencyList the adjacency list
         */
        public SimpleDecoratedGraphNode(final Id id, final Collection<Id> adjacencyList) {
            this.id = id;
            this.adjacencyList = adjacencyList;
        }

        // ========================================================================
        // Methods
        // ========================================================================
        @Override
        public Id getId() {
            return this.id;
        }

        @Override
        public GraphIterator<Id> getDepthFirstIterator() {
            return DecoratedAdjacencyListGraph.this.getDepthFirstIterator(this.id);
        }

        @Override
        public GraphIterator<Id> getBreadthFirstIterator() {
            return DecoratedAdjacencyListGraph.this.getBreadthFirstIterator(this.id);
        }

        @Override
        public void ensureLinkExists(final Id targetId) {
            if (!DecoratedAdjacencyListGraph.this.containsNode(targetId)) {
                throw new IllegalArgumentException("Attempt to connect the node \"" + this.id
                        + "\" with an absent node \"" + targetId + "\"!");
            }

            if (!this.adjacencyList.contains(targetId)) {
                this.adjacencyList.add(targetId);
            }
        }

        @Override
        public void ensureLinkBroken(final Id targetId) {
            if (!DecoratedAdjacencyListGraph.this.containsNode(targetId)) {
                throw new IllegalArgumentException("Attempt to disconnect the node \"" + this.id
                        + "\" from an absent node \"" + targetId + "\"!");
            }

            if (this.adjacencyList.contains(targetId)) {
                this.adjacencyList.remove(targetId);
            }
        }

        @Override
        public void breakLinks() {
            this.adjacencyList.clear();
        }

        @Override
        public void setContent(final Content content) {
            this.content = content;
        }

        @Override
        public Content getContent() {
            return this.content;
        }

    }

}
