package org.bananaLaba.ioc.simple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import org.bananaLaba.bootstrap.graph.Graph;
import org.bananaLaba.bootstrap.graph.GraphIterator;
import org.bananaLaba.bootstrap.graph.GraphSearchListener;
import org.bananaLaba.util.SimpleGraph;
import org.bananaLaba.util.SimpleGraphNode;
import org.bananaLaba.util.StringUtils;

/**
 * An implementation of dependency tracker based on a graph representation of bean dependencies.
 * It doesn't allow dependency cycles and doesn't differentiate construction dependencies from method dependencies.
 * @author Judzin
 *
 */
// FIXME: review graph API (especially searching) and move such features as cycle detection in separate utils.
public class DependencyGraphTracker implements DependencyTracker, BeanCacheListener {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * The dependency graph.
     */
    private Graph<String, SimpleGraphNode<String>> graph = new SimpleGraph<String>();
    /**
     * A flag that specifies if the dependency graph has been modified and thus must be validated.
     */
    private boolean dirty;

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public List<String> getInstantiationChain(final String origin) {
        if (this.dirty) {
            this.validateGraph();
            this.dirty = false;
        }

        final ArrayList<String> chain = new ArrayList<>();
        chain.add(origin);
        final GraphIterator<String> reverseChainIterator = this.graph.getDepthFirstIterator(origin);
        while (reverseChainIterator.hasNext()) {
            chain.add(reverseChainIterator.next());
        }

        Collections.reverse(chain);

        return chain;
    }

    /**
     * Validates the dependency graph.
     */
    private void validateGraph() {
        final Collection<String> beanNames = this.graph.getNodeIds();
        final HashSet<String> visitedNames = new HashSet<>();
        final Stack<String> currentPath = new Stack<>();

        final GraphSearchListener<String> listener = new GraphSearchListener<String>() {

            @Override
            public void onDeepInto(final String id) {
                if (currentPath.contains(id)) {
                    throw new IllegalStateException("Dependency cycle detected: "
                            + StringUtils.convertArray(currentPath.toArray()) + "\"!");
                }
                currentPath.push(id);
            }

            @Override
            public void onClimbUp() {
                currentPath.pop();
            }

        };

        for (final String beanName : beanNames) {
            currentPath.clear();
            if (visitedNames.contains(beanName)) {
                continue;
            }

            currentPath.add(beanName);
            visitedNames.add(beanName);

            final GraphIterator<String> iterator = this.graph.getDepthFirstIterator(beanName);
            iterator.setListener(listener);
            while (iterator.hasNext()) {
                final String beanOnPath = iterator.next();
                visitedNames.add(beanOnPath);
            }
        }
    }

    @Override
    public void ensureConstructionDependency(final String source, final String target) {
        this.ensureDependency(source, target);
    }

    @Override
    public void ensureMethodDependency(final String source, final String target) {
        this.ensureDependency(source, target);
    }

    /**
     * Ensures that the dependency link between the given beans exists.
     * @param source name of the dependent bean
     * @param target name of the dependency bean
     */
    private void ensureDependency(final String source, final String target) {
        SimpleGraphNode<String> sourceNode = null;
        if (this.graph.containsNode(source)) {
            sourceNode = this.graph.getNode(source);
        } else {
            sourceNode = this.graph.insertNode(source);
        }

        if (!this.graph.containsNode(target)) {
            this.graph.insertNode(target);
        }

        sourceNode.ensureLinkExists(target);

        this.dirty = true;
    }

    @Override
    public void ensureBeanExists(final String name) {
        if (this.graph.containsNode(name)) {
            return;
        }

        this.graph.insertNode(name);

        this.dirty = true;
    }

    @Override
    public void onSingletonCached(final String name) {
        this.graph.getNode(name).ensureLinksBroken();
    }

    @Override
    public void onSingletonDetected(final String name) {
    }

}
