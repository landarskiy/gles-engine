package org.bananaLaba.shaderModel.template.simple;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.bananaLaba.bootstrap.graph.GraphIterator;
import org.bananaLaba.shaderModel.ParameterMap;
import org.bananaLaba.shaderModel.ShaderFactory;
import org.bananaLaba.shaderModel.ShaderFactorySession;
import org.bananaLaba.shaderModel.util.DecoratedGraph;
import org.bananaLaba.shaderModel.util.DecoratedGraphNode;

public class SimpleShaderFactory implements ShaderFactory {

    private DecoratedGraph<String, TemplateUnit> fragmentTemplates;
    private Map<String, TemplateUnit> templates = new HashMap<>();

    public void setFragmentTemplateGraph(final DecoratedGraph<String, TemplateUnit> graph) {
        this.fragmentTemplates = graph;
    }

    public void clearFragmentTemplates() {
        this.fragmentTemplates.clear();
    }

    public void addTemplate(final String id, final TemplateUnit unit) {
        this.templates.put(id, unit);
    }

    public void addTemplates(final Map<String, TemplateUnit> templates) {
        this.templates.putAll(templates);
    }

    public void clearTemplates() {
        this.templates.clear();
    }

    @Override
    public ShaderFactorySession getSession(final ParameterMap parameters) {
        final Map<String, String> fragments = new HashMap<>();
        final Collection<String> fragmentIds = this.fragmentTemplates.getNodeIds();
        for (final String fragmentId : fragmentIds) {
            if (fragments.containsKey(fragmentId)) {
                continue;
            } else {
                final Stack<DecoratedGraphNode<String, TemplateUnit>> templateStack = new Stack<>();
                templateStack.push(this.fragmentTemplates.getNode(fragmentId));
                final GraphIterator<String> nodeIterator =
                        this.fragmentTemplates.getDepthFirstIterator(fragmentId);
                while (nodeIterator.hasNext()) {
                    templateStack.push(this.fragmentTemplates.getNode(nodeIterator.next()));
                }

                final TemplateContext context = new TemplateContext() {

                    @Override
                    public <T> T getParameterValue(final String id, final Class<T> type) {
                        return parameters.getParameterValue(id, type);
                    }

                    @Override
                    public boolean isParameterPresent(final String id) {
                        return parameters.isParameterPresent(id);
                    }

                    @Override
                    public String getFragment(final String id) {
                        return fragments.get(id);
                    }

                    @Override
                    public <T> T getVariableValue(final String id, final Class<T> type) {
                        throw new RuntimeException("No such variable with id \"" + id + "\" in the current scope!");
                    }

                };

                while (!templateStack.isEmpty()) {
                    final DecoratedGraphNode<String, TemplateUnit> node = templateStack.pop();
                    fragments.put(node.getId(), node.getContent().instantiate(context));
                }
            }
        }

        final SimpleShaderFactorySession session = new SimpleShaderFactorySession();
        session.setParameters(parameters);
        session.addFragments(fragments);
        session.addTemplates(this.templates);

        return session;
    }

}
