package org.bananaLaba.shaderModel.util;

import org.bananaLaba.bootstrap.graph.GraphNode;

public interface DecoratedGraphNode<Id, Content> extends GraphNode<Id> {

    void setContent(final Content content);

    Content getContent();

    void ensureLinkExists(final Id id);

    void ensureLinkBroken(final Id id);

    void breakLinks();

}
