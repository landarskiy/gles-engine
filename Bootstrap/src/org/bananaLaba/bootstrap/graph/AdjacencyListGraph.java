package org.bananaLaba.bootstrap.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * A base class for graph implementation based on adjacency lists.
 * @author Judzin
 *
 * @param <Id> type of the graph node identifiers
 * @param <Node> type of the graph nodes
 */
public abstract class AdjacencyListGraph<Id, Node extends GraphNode<Id>> implements Graph<Id, Node> {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A mapping from node id's to their adjacency lists.
     */
    private Map<Id, Collection<Id>> adjacencyLists = new HashMap<>();

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public Node insertNode(final Id id) {
        if (this.containsNode(id)) {
            throw new IllegalArgumentException("Attempt to insert new node for existing id \"" + id + "\"!");
        } else {
            final ArrayList<Id> adjacencyList = new ArrayList<>();
            this.adjacencyLists.put(id, adjacencyList);
            return this.createNode(id, adjacencyList);
        }
    }

    @Override
    public abstract Node getNode(final Id id);

    @Override
    public void removeNode(final Id id) {
        final Collection<Id> adjacencyList = this.adjacencyLists.remove(id);
        if (adjacencyList == null) {
            throw new IllegalArgumentException("Attempt to remove non-existent node with id=\"" + id + "\"!");
        } else {
            this.removeNodeData(id);
        }
    }

    @Override
    public Collection<Id> getNodeIds() {
        return this.adjacencyLists.keySet();
    }

    @Override
    public int getNodeCount() {
        return this.adjacencyLists.size();
    }

    @Override
    public int getEdgeCount() {
        final Collection<Collection<Id>> lists = this.adjacencyLists.values();
        int edgeCount = 0;
        for (final Collection<Id> list : lists) {
            edgeCount += list.size();
        }

        return edgeCount;
    }

    @Override
    public boolean containsNode(final Id id) {
        return this.adjacencyLists.containsKey(id);
    }

    @Override
    public boolean hasEdges(final Id fromId, final Id totId) {
        if (this.containsNode(fromId)) {
            return this.adjacencyLists.get(fromId).contains(totId);
        }
        return false;
    }

    @Override
    public Iterator<Id> getDepthFirstIterator(final Id startId) {
        return new DepthAdjacencyListIterator(startId);
    }

    @Override
    public Iterator<Id> getBreadthFirstIterator(final Id startId) {
        return new BreadthAdjacencyListIterator(startId);
    }

    /**
     * Is called within the base class code when new node needs to be created.
     * @param id the node id
     * @param adjacencyList the node adjacency list
     * @return the node
     */
    protected abstract Node createNode(final Id id, final Collection<Id> adjacencyList);

    /**
     * Is called within the base class code when a node needs to be reomved.
     * @param id the node id
     */
    protected abstract void removeNodeData(final Id id);

    /**
     * Gets the adjacency list for the given node.
     * @param id the node id
     * @return the adjacency list
     */
    protected Collection<Id> getAdjacencyList(final Id id) {
        return this.adjacencyLists.get(id);
    }

    // ========================================================================
    // Inner types
    // ========================================================================
    /**
     * An implementation of iterator for depth-first search paths for graphs represented by the adjacency lists.
     * @author Judzin
     *
     */
    private class DepthAdjacencyListIterator implements Iterator<Id> {

        // ========================================================================
        // Fields
        // ========================================================================
        /**
         * Stack of adjacency list iterators.
         */
        private Stack<Iterator<Id>> nodeIteratorStack = new Stack<>();
        /**
         * The current adjacency list iterator.
         */
        private Iterator<Id> currentIterator;

        // ========================================================================
        // Constructors
        // ========================================================================
        public DepthAdjacencyListIterator(final Id startId) {
            this.currentIterator = AdjacencyListGraph.this.adjacencyLists.get(startId).iterator();
        }

        // ========================================================================
        // Methods
        // ========================================================================
        @Override
        public boolean hasNext() {
            return this.currentIterator.hasNext();
        }

        @Override
        public Id next() {
            // If the invoker code is aware of the "hasNext" condition, the current iterator has stuff to return.
            final Id next = this.currentIterator.next();

            final Iterator<Id> nextIterator = AdjacencyListGraph.this.adjacencyLists.get(next).iterator();
            // Check if it's possible to get deeper in the graph.
            if (nextIterator.hasNext()) {
                // If it is, remember the current iterator position and get deeper.
                this.nodeIteratorStack.push(this.currentIterator);
                this.currentIterator = nextIterator;
            } else {
                // Otherwise get back while either the previous node has more unvisited connections or up to the start
                // node.
                while (!(this.currentIterator.hasNext() || this.nodeIteratorStack.isEmpty())) {
                    this.currentIterator = this.nodeIteratorStack.pop();
                }
            }

            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * An implementation of iterator for breadth-first search path for graphs represented with adjacency lists.
     * @author Judzin
     *
     */
    private class BreadthAdjacencyListIterator implements Iterator<Id> {

        // ========================================================================
        // Fields
        // ========================================================================
        /**
         * A list of nodes to visit after the current iterator will finish its job.
         */
        private ArrayList<Id> nodesToVisit = new ArrayList<>();
        /**
         * The current adjacency list iterator.
         */
        private Iterator<Id> currentIterator;

        // ========================================================================
        // Constructors
        // ========================================================================
        public BreadthAdjacencyListIterator(final Id startId) {
            this.currentIterator = AdjacencyListGraph.this.adjacencyLists.get(startId).iterator();
        }

        // ========================================================================
        // Methods
        // ========================================================================
        @Override
        public boolean hasNext() {
            return this.currentIterator.hasNext();
        }

        @Override
        public Id next() {
            final Id next = this.currentIterator.next();
            this.nodesToVisit.add(next);
            while (!(this.currentIterator.hasNext() || this.nodesToVisit.isEmpty())) {
                this.currentIterator =
                        AdjacencyListGraph.this.adjacencyLists.get(this.nodesToVisit.remove(0)).iterator();
            }

            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
