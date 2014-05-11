package org.bananaLaba.bootstrap.graph;

import java.util.Collection;

/**
 * A basic interface for graph data structures.
 * @author Judzin
 *
 * @param <Id> type of the graph node identifiers
 * @param <Node> type of the graph nodes
 */
public interface Graph<Id, Node extends GraphNode<Id>> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Inserts a node with the given id.
     * @param id the id value
     * @return the created node
     */
    Node insertNode(final Id id);

    /**
     * Gets the node with the given id.
     * @param id the id value
     * @return the node or null if it doesn't exist
     */
    Node getNode(final Id id);

    /**
     * Removes the node with the given id.
     * @param id the id value
     */
    void removeNode(final Id id);

    /**
     * Gets collection of all existing node id's.
     * @return the collection
     */
    Collection<Id> getNodeIds();

    /**
     * Gets count of all existing nodes.
     * @return count of the nodes
     */
    int getNodeCount();

    /**
     * Gets count of all existing edges.
     * @return count of the edges
     */
    int getEdgeCount();

    /**
     * Checks if the graph contains the node with the given id.
     * @param id the id value
     * @return true if the node exists or false otherwise
     */
    boolean containsNode(final Id id);

    /**
     * Checks if the graph has an edge between the nodes with the given id's
     * @param fromId the source node id
     * @param totId the target node id
     * @return true if the edge exists or false otherwise
     */
    boolean hasEdges(final Id fromId, final Id totId);

    /**
     * Gets an iterator for the depth-first search path from the given node. Note that the iterator will not include
     * the start node.
     * @param startId the id of the node
     * @return the iterator
     */
    GraphIterator<Id> getDepthFirstIterator(final Id startId);

    /**
     * Gets an iterator for the breadth-first search path from the given node. Note that the iterator will not include
     * the start node.
     * @param startId the id of the node
     * @return the iterator
     */
    GraphIterator<Id> getBreadthFirstIterator(final Id startId);

}
