package org.bananaLaba.shaderModel.util;

import org.bananaLaba.bootstrap.graph.Graph;

public interface DecoratedGraph<Id, Content> extends Graph<Id, DecoratedGraphNode<Id, Content>> {

    void clear();

}
