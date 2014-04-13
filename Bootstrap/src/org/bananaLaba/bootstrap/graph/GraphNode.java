package org.bananaLaba.bootstrap.graph;

import java.util.Iterator;

/**
 * A basic interface for graph structure node.
 * @author Judzin
 *
 * @param <Id> type of the node identifier
 */
public interface GraphNode<Id> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Gets the node id.
     * @return the id value
     */
    Id getId();

    /**
     * Gets iterator for the depth-first search path starting from the node.
     * @return the iterator
     */
    Iterator<Id> getDepthFirstIterator();

    /**
     * Gets iterator for the breadth-first search path starting from the node.
     * @return the iterator
     */
    Iterator<Id> getBreadthFirstIterator();

}
