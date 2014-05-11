package org.bananaLaba.bootstrap.graph;

public interface GraphIterator<ID> {

    ID next();

    boolean hasNext();

    void setListener(final GraphSearchListener<ID> listener);

}
