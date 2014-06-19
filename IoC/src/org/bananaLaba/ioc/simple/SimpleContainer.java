package org.bananaLaba.ioc.simple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.reflection.ReferenceResolver;
import org.bananaLaba.ioc.simple.beanDefinition.BeanDefinition;
import org.bananaLaba.ioc.simple.beanDefinition.BeanInstantiationData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanMetaData;
import org.bananaLaba.ioc.simple.beanDefinition.BeanPropertyData;
import org.bananaLaba.ioc.simple.builder.BeanRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: review bean caching feature - that was a really mess to notify dependency tracker and injection manager about
// that singletons should have injections only once, right after their instantiation.
// TODO: add support for bean aliases.
// TODO: add support for bean namespaces. It would be useful if a container is used for multiple purposes
// simultaneously (e.g. client uses the container wrapped by the simplest BeanContainer interface and XML processor
// uses it for data bean instantiation.
// TODO: improve dependency tracking system for support of dependency cycles resolving. Possible extensions to do this:
// 1) more complicated graph structures that are able to build chains of nodes each containing bean name and
// description of actions that must be performed;
// 2) despite dependency cycle is bad design signs in most cases, it can be broken if there is at least one
// non-constructional dependency in it and this dependency points to a singleton bean.
// 3) build dependency priority system;
// 4) build bean life cycle manager system.
// FIXME: there is an issue which is appears when e.g. we have a bean which has two or more properties which are
// injected with the same prototype bean. The problem: due to the current instantiation chain the same instance of the
// prototype bean will be used for all injections of the corresponding type for the dependent bean. The solution:
// modify dependency tracker to use graph with arches weighted with dependency cardinality and use bean cache which
// contains stacks for each bean name (necessart instance count for each bean). When a requested to the cache is
// performed the corresponding stack pops a bean instance and gives it away.
/**
 * A simple implementation of an IoC-container with basic DI functionality. No bean dependency cycle resolving is
 * supported.
 * @author Judzin
 *
 */
public class SimpleContainer implements BeanContainer, BeanRegistry {

    // ========================================================================
    // Static fields
    // ========================================================================
    /**
     * An instance of Slf4J logger dedicated for the container class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleContainer.class);

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A collection of fresh bean definitions that need to be processed before any further requests to the container.
     */
    private Collection<BeanDefinition> freshDefinitions = new ArrayList<>();

    /**
     * An instance of bean instantiation helper.
     */
    private InstantiationManager instantiationManager = new InstantiationManager();
    /**
     * An instance of bean injection helper.
     */
    private InjectionManager injectionManager = new InjectionManager();

    /**
     * An instance of dependency tracker.
     */
    private DependencyTracker dependencyTracker;

    /**
     * A mapping from the managed bean names to their classes.
     */
    private Map<String, Class<?>> beanTypes = new HashMap<>();

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates an instance of simple container with no beans to manage.
     */
    public SimpleContainer() {
        final DependencyGraphTracker tracker = new DependencyGraphTracker();
        this.dependencyTracker = tracker;

        this.instantiationManager.addCacheListener(tracker);
        this.instantiationManager.addCacheListener(this.injectionManager);

        SimpleContainer.LOGGER.info("Created an empty container.");
    }

    // ========================================================================
    // Methods
    // ========================================================================
    @Override
    public void registerDefinition(final BeanDefinition definition) {
        final BeanMetaData metaData = definition.getMetaData();
        final String beanName = metaData.getName();
        if (this.beanTypes.containsKey(beanName)) {
            throw new IllegalArgumentException("The bean named \"" + beanName + "\" is already registered!");
        } else {
            this.freshDefinitions.add(definition);
            this.beanTypes.put(beanName, metaData.getType());
        }
    }

    @Override
    public void registerDefinitions(final Collection<BeanDefinition> definitions) {
        for (final BeanDefinition definition : definitions) {
            final BeanMetaData metaData = definition.getMetaData();
            final String beanName = metaData.getName();
            if (this.beanTypes.containsKey(beanName)) {
                throw new IllegalArgumentException("The bean named \"" + beanName + "\" is already registered!");
            } else {
                this.freshDefinitions.add(definition);
                this.beanTypes.put(beanName, metaData.getType());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(final String name, final Class<T> type) {
        final String typeName = type == null ? null : type.getName();
        SimpleContainer.LOGGER.debug(
                "Requested a bean with name \"" + name + "\" as an instance of \"" + typeName + "\".");
        if (!this.freshDefinitions.isEmpty()) {
            SimpleContainer.LOGGER.debug("Has fresh bean definitions. Updating metadata...");
            this.updateInternalData();
            SimpleContainer.LOGGER.debug("Metadata updated.");
        }

        if (!this.beanTypes.containsKey(name)) {
            SimpleContainer.LOGGER.debug("The bean \"" + name + "\" not found!");
            return null;
        }

        SimpleContainer.LOGGER.trace("Building the bean dependency chain...");
        final List<String> beanChain =
                this.dependencyTracker.getInstantiationChain(name);
        final Map<String, Object> beanCache = new HashMap<>();
        final TemporalBeanResolver resolver = new TemporalBeanResolver(beanCache);
        SimpleContainer.LOGGER.trace("Instantiating and weaving the bean chain...");
        for (final String beanName : beanChain) {
            final Object bean = this.instantiationManager.getInstance(beanName, resolver);
            beanCache.put(beanName, bean);
            this.injectionManager.apply(beanName, bean, resolver);
        }

        SimpleContainer.LOGGER.trace("The bean is ready!");
        return (T) beanCache.get(name);
    }

    @Override
    public Class<?> getBeanType(final String name) {
        final Class<?> beanType = this.beanTypes.get(name);
        if (beanType == null) {
            throw new IllegalArgumentException("The bean named \"" + name + "\" is not registered!");
        } else {
            return beanType;
        }
    }

    @Override
    public boolean hasBean(final String name) {
        return this.beanTypes.containsKey(name);
    }

    /**
     * Updates all internal data and helper objects of the container. Is called before satisfying any outer requests to
     *  the container when there are unprocessed bean definitions.
     */
    private void updateInternalData() {
        final ReferenceResolver<Class<?>> typeResolver = new ReferenceResolver<Class<?>>() {

            @Override
            public Class<?> resolve(final String name) {
                return SimpleContainer.this.beanTypes.get(name);
            }

        };

        for (final BeanDefinition definition : this.freshDefinitions) {
            final BeanMetaData metaData = definition.getMetaData();
            final String beanName = metaData.getName();

            this.dependencyTracker.ensureBeanExists(beanName);

            final BeanPropertyData propertyData = definition.getPropertyData();
            for (final String dependency : DefinitionUtils.getMethodDependencies(propertyData)) {
                this.dependencyTracker.ensureMethodDependency(beanName, dependency);
            }

            // TODO: this is that assumption that class beans don't have construction data - fragile enough!
            final BeanInstantiationData instantiationData = definition.getInstantiationData();
            final boolean isClass = (instantiationData == null);
            //
            if (isClass) {
                this.instantiationManager.addBean(beanName, typeResolver);
            } else {
                for (final String dependency : DefinitionUtils.getConstructionDependencies(instantiationData)) {
                    this.dependencyTracker.ensureConstructionDependency(beanName, dependency);
                }

                this.instantiationManager.addBean(beanName, instantiationData, typeResolver);
            }

            this.injectionManager.addBean(beanName, propertyData, typeResolver);
        }

        this.freshDefinitions.clear();
    }

    // ========================================================================
    // Inner types
    // ========================================================================
    /**
     * A lightweight implementation of bean reference resolver. Is used inside the IoC-container for storing dependency
     * chain of a bean that is requested from outside.
     * @author Judzin
     *
     */
    private static class TemporalBeanResolver implements ReferenceResolver<Object> {

        // ========================================================================
        // Fields
        // ========================================================================
        /**
         * A mapping from bean names to their instances representing the temporal cache.
         */
        private Map<String, Object> beanCache;

        // ========================================================================
        // Constructors
        // ========================================================================
        /**
         * Creates a bean resolver based on the given map reference.
         * @param beanCache
         */
        public TemporalBeanResolver(final Map<String, Object> beanCache) {
            this.beanCache = beanCache;
        }

        // ========================================================================
        // Methods
        // ========================================================================
        @Override
        public Object resolve(final String name) {
            return this.beanCache.get(name);
        }

    }

}
