package org.bananaLaba.ioc.test;

import org.bananaLaba.bootstrap.graph.GraphIterator;
import org.bananaLaba.util.SimpleGraph;
import org.bananaLaba.util.SimpleGraphNode;

public class GraphTest {

    public static void main(final String[] arguments) {
        final SimpleGraph<Integer> graph = new SimpleGraph<>();
        for (int id = 1; id <= 10; id++) {
            graph.insertNode(id);
        }

        SimpleGraphNode<Integer> node = graph.getNode(1);
        node.ensureLinkExists(2);
        node.ensureLinkExists(3);
        node.ensureLinkExists(4);

        node = graph.getNode(2);
        node.ensureLinkExists(5);

        node = graph.getNode(3);
        node.ensureLinkExists(5);

        node = graph.getNode(4);
        node.ensureLinkExists(6);
        node.ensureLinkExists(7);

        node = graph.getNode(6);
        node.ensureLinkExists(8);

        node = graph.getNode(7);
        node.ensureLinkExists(9);
        node.ensureLinkExists(10);

        GraphIterator<Integer> iterator = graph.getBreadthFirstIterator(1);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        graph.getNode(4).ensureLinkBroken(7);

        iterator = graph.getDepthFirstIterator(1);
        System.out.println();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
