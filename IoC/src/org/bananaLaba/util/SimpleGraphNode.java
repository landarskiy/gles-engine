package org.bananaLaba.util;

import org.bananaLaba.bootstrap.graph.GraphNode;

/**
 * A basic interface for graph structure nodes that allow direct edge management between them.
 * Loop edges are allowed and parallel edges are just ignored.
 * @author Judzin
 *
 * @param <Id> type of the node identifiers
 */
public interface SimpleGraphNode<Id> extends GraphNode<Id> {

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Ensures that the link between this node and the node with the given id exists.
     * @param targetId the target node id
     */
    void ensureLinkExists(final Id targetId);

    /**
     * Ensures that the link between this node and the node with the given id doesn't exist.
     * @param targetId the target node id
     */
    void ensureLinkBroken(final Id targetId);

    /**
     * Ensures that the node has no outgoing arches.
     */
    void ensureLinksBroken();

}
