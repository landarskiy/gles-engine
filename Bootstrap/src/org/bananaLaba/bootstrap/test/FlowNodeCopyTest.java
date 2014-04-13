package org.bananaLaba.bootstrap.test;

import org.bananaLaba.bootstrap.common.Converter;
import org.bananaLaba.bootstrap.common.IdentityConverter;
import org.bananaLaba.bootstrap.graph.FlowNode;

public class FlowNodeCopyTest {

    public static void main(final String[] arguments) {
        final FlowNode<Integer, Integer> sourceNode = new FlowNode<>();
        sourceNode.setContent(1);
        sourceNode.setId(1);

        FlowNode<Integer, Integer> childNode = new FlowNode<>();
        childNode.setContent(2);
        childNode.setId(2);
        sourceNode.addChild(childNode);

        childNode = new FlowNode<>();
        childNode.setContent(3);
        childNode.setId(3);
        childNode.addChild(sourceNode);
        sourceNode.addChild(childNode);

        sourceNode.getChild(2).addChild(childNode);

        System.out.println(sourceNode);

        final Converter<Integer, Integer> converter = IdentityConverter.prepare(Integer.class);
        System.out.println(sourceNode.convert(converter, converter));
    }

}
