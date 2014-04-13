package org.bananaLaba.util;

import java.util.Collection;
import java.util.Iterator;

import org.bananaLaba.bootstrap.graph.AdjacencyListGraph;

/**
 * A simple graph implementation based on the adjacency list representation. The graph nodes care no content except
 * their identifiers and allow to directly manage their links. Loops are allowed and parallel links are just ignored.
 * @author Judzin
 *
 * @param <Id> type of the graph node identifiers
 */
public class SimpleGraph<Id> extends AdjacencyListGraph<Id, SimpleGraphNode<Id>> {

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public SimpleGraphNode<Id> getNode(final Id id) {
        return new SimpleNode(id, this.getAdjacencyList(id));
    }

    @Override
    protected SimpleGraphNode<Id> createNode(final Id id, final Collection<Id> adjacencyList) {
        return this.getNode(id);
    }

    @Override
    protected void removeNodeData(final Id id) {
    }

    // ========================================================================
    // Inner types
    // ========================================================================
    /**
     * The simplest implementation of node of a graph represented by adjacency lists.
     * This class provides a basic approach to its link management - it's possible to ensure that there is a directed
     * (in the general case) edge targeted to some other or the same node (i.e. a loop), but parallel edges between
     * such nodes are ignored.
     * @author Judzin
     *
     */
    private class SimpleNode implements SimpleGraphNode<Id> {

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

        // ========================================================================
        // Constructors
        // ========================================================================
        /**
         * Creates a simple node with the given id and the given adjacency list. Note that the adjacency list is not
         * copied but taken by reference.
         * @param id the id value
         * @param adjacencyList the adjacency list
         */
        public SimpleNode(final Id id, final Collection<Id> adjacencyList) {
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
        public Iterator<Id> getDepthFirstIterator() {
            return SimpleGraph.this.getDepthFirstIterator(this.id);
        }

        @Override
        public Iterator<Id> getBreadthFirstIterator() {
            return SimpleGraph.this.getBreadthFirstIterator(this.id);
        }

        @Override
        public void ensureLinkExists(final Id targetId) {
            if (!SimpleGraph.this.containsNode(targetId)) {
                throw new IllegalArgumentException("Attempt to connect the node \"" + this.id
                        + "\" with an absent node \"" + targetId + "\"!");
            }

            if (!this.adjacencyList.contains(targetId)) {
                this.adjacencyList.add(targetId);
            }
        }

        @Override
        public void ensureLinkBroken(final Id targetId) {
            if (!SimpleGraph.this.containsNode(targetId)) {
                throw new IllegalArgumentException("Attempt to disconnect the node \"" + this.id
                        + "\" from an absent node \"" + targetId + "\"!");
            }

            if (this.adjacencyList.contains(targetId)) {
                this.adjacencyList.remove(targetId);
            }
        }

        @Override
        public void ensureLinksBroken() {
            this.adjacencyList.clear();
        }

    }

}
