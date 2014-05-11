package org.bananaLaba.bootstrap.graph;

public interface GraphSearchListener<ID> {

    void onDeepInto(final ID id);

    void onClimbUp();

}
