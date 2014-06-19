package org.bananaLaba.shaderModel.template.simple.meta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bananaLaba.shaderModel.ShaderFactory;
import org.bananaLaba.shaderModel.template.ShaderFactoryPrototype;
import org.bananaLaba.shaderModel.template.IdentifiedTemplateBuilder;
import org.bananaLaba.shaderModel.template.simple.SimpleShaderFactory;
import org.bananaLaba.shaderModel.template.simple.TemplateUnit;
import org.bananaLaba.shaderModel.util.DecoratedAdjacencyListGraph;
import org.bananaLaba.shaderModel.util.DecoratedGraph;
import org.bananaLaba.shaderModel.util.DecoratedGraphNode;

public class SimpleShaderFactoryPrototype implements ShaderFactoryPrototype {

    private Set<String> unresolvedFragments = new HashSet<>();
    private Map<String, TemplateUnit> templates = new HashMap<>();
    private DecoratedGraph<String, TemplateUnit> fragmentGraph = new DecoratedAdjacencyListGraph<>();

    @Override
    public IdentifiedTemplateBuilder getTemplateBuilder() {
        return new SimpleIdentifiedTemplateBuilder() {

            @Override
            protected void commitInternal(final String id, final TemplateUnit unit) {
                SimpleShaderFactoryPrototype.this.templates.put(id, unit);
            }

            @Override
            protected void ensureFragmentDependency(final String id) {
                if (!SimpleShaderFactoryPrototype.this.fragmentGraph.containsNode(id)) {
                    SimpleShaderFactoryPrototype.this.unresolvedFragments.add(id);
                }
            }

        };
    }

    @Override
    public IdentifiedTemplateBuilder getFragmentTemplateBuilder() {
        return new SimpleIdentifiedTemplateBuilder() {

            @Override
            protected void commitInternal(final String id, final TemplateUnit unit) {
                DecoratedGraphNode<String, TemplateUnit> fragmentNode = null;
                if (SimpleShaderFactoryPrototype.this.fragmentGraph.containsNode(id)) {
                    fragmentNode = SimpleShaderFactoryPrototype.this.fragmentGraph.getNode(id);
                } else {
                    fragmentNode = SimpleShaderFactoryPrototype.this.fragmentGraph.insertNode(id);
                }

                fragmentNode.setContent(unit);

                if (SimpleShaderFactoryPrototype.this.unresolvedFragments.contains(id)) {
                    SimpleShaderFactoryPrototype.this.unresolvedFragments.remove(id);
                }
            }

            @Override
            protected void ensureFragmentDependency(final String id2) {
                final String id1 = this.getId();
                DecoratedGraphNode<String, TemplateUnit> fragmentNode = null;
                if (SimpleShaderFactoryPrototype.this.fragmentGraph.containsNode(id1)) {
                    fragmentNode = SimpleShaderFactoryPrototype.this.fragmentGraph.getNode(id1);
                } else {
                    fragmentNode = SimpleShaderFactoryPrototype.this.fragmentGraph.insertNode(id1);
                }

                if (!SimpleShaderFactoryPrototype.this.fragmentGraph.containsNode(id2)) {
                    SimpleShaderFactoryPrototype.this.fragmentGraph.insertNode(id2);
                    SimpleShaderFactoryPrototype.this.unresolvedFragments.add(id2);
                }

                fragmentNode.ensureLinkExists(id2);
            }

        };
    }

    @Override
    public void clearTemplates() {
        this.templates.clear();
    }

    @Override
    public void clearFragmentTemplates() {
        this.fragmentGraph.clear();
    }

    @Override
    public ShaderFactory create() {
        if (!this.unresolvedFragments.isEmpty()) {
            final StringBuilder nameAppender = new StringBuilder();
            boolean isFirst = true;
            for (final String framgentName : this.unresolvedFragments) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    nameAppender.append(", ");
                }
                nameAppender.append(framgentName);
            }

            throw new IllegalStateException("The following fragments are referenced but not declared: "
                    + nameAppender.toString() + "!");
        }

        final SimpleShaderFactory factory = new SimpleShaderFactory();
        factory.setFragmentTemplateGraph(this.fragmentGraph);
        factory.addTemplates(this.templates);

        return factory;
    }

}
