package org.bananaLaba.ioc.simple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bananaLaba.ioc.bean.BeanScope;
import org.bananaLaba.ioc.injection.Argument;
import org.bananaLaba.ioc.injection.ArgumentGroup;
import org.bananaLaba.ioc.reflection.ArgumentManager;
import org.bananaLaba.ioc.reflection.Instantiator;
import org.bananaLaba.ioc.reflection.ReferenceResolver;
import org.bananaLaba.ioc.reflection.instantiator.InstanceConstructor;
import org.bananaLaba.ioc.reflection.instantiator.InstanceFactory;
import org.bananaLaba.ioc.simple.beanDefinition.BeanInstantiationData;
import org.bananaLaba.ioc.simple.util.ReflectionUtils;
import org.bananaLaba.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A helper class responsibility is to control all the bean instances within an IoC-container.
 * @author Judzin
 *
 */
public class InstantiationManager {

    // ========================================================================
    // Static fields
    // ========================================================================
    private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationManager.class);

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A mapping from the bean names to their instantiation models.
     */
    private Map<String, InstantiationModel<?>> models = new HashMap<>();
    /**
     * A bean name to bean instance mapping that plays role of singleton bean cache.
     */
    private Map<String, Object> singletonCache = new HashMap<>();
    /**
     * A set of names of singleton beans that have not been instantiated yet.
     */
    private Set<String> uncachedSingletons = new HashSet<>();
    /**
     * A set of names of beans that are singletons.
     */
    private Set<String> singletons = new HashSet<>();

    private List<BeanCacheListener> cacheListeners = new ArrayList<>();

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Gets an instance of the given bean.
     * @param beanName the bean name
     * @param referenceResolver a resolver object for other beans managed by the enclosing IoC-container
     * @return the bean instance
     */
    public Object getInstance(final String beanName, final ReferenceResolver<Object> referenceResolver) {
        Object bean = this.singletonCache.get(beanName);
        if (bean == null) {
            bean = this.models.get(beanName).getInstance(referenceResolver);
            if (this.uncachedSingletons.contains(beanName)) {
                InstantiationManager.LOGGER.trace("Caching a singleton bean \"" + beanName + "\".");
                this.uncachedSingletons.remove(beanName);
                this.singletonCache.put(beanName, bean);

                for (final BeanCacheListener listener : this.cacheListeners) {
                    listener.onSingletonCached(beanName);
                }
            }
        } else {
            InstantiationManager.LOGGER.trace("Returning a cached instance of a singleton bean \"" + beanName + "\".");
        }

        return bean;
    }

    /**
     * Adds the given bean which is not an instance of a meta-class to the helper management domain.
     * @param name the bean name
     * @param data the bean instantiation model data
     * @param classResolver a resolver object for other bean classes
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addBean(final String name, final BeanInstantiationData data,
            final ReferenceResolver<Class<?>> classResolver) {
        final Class<?> beanType = classResolver.resolve(name);
        if (beanType == Class.class) {
            throw new RuntimeException("Attempt to register a class bean with instantiation data!");
        }

        if (data.getScope() == BeanScope.SINGLETON) {
            this.onSingletonDetecetd(name);
        }

        final ArgumentGroup injection = data.getInjection();
        final Collection<Argument<?>> primitives = injection.getPrimitives();
        final Collection<Argument<String>> references = injection.getReferences();
        final int totalArgumentCount = primitives.size() + references.size();
        final Class<?>[] argumentTypes = new Class<?>[totalArgumentCount];

        final ArgumentManager argumentManager = new ArgumentManager();
        argumentManager.addSimpleArguments(primitives);
        for (final Argument<?> primitive : primitives) {
            argumentTypes[primitive.getIndex()] = primitive.getTypeHint();
        }

        argumentManager.addReferenceArguments(references);
        for (final Argument<String> reference : references) {
            Class<?> referenceType = reference.getTypeHint();
            if (referenceType == null) {
                referenceType = classResolver.resolve(reference.getValue());
            }
            argumentTypes[reference.getIndex()] = referenceType;
        }

        InstantiationModel model = null;

        final String factoryBeanName = data.getFactoryBeanName();
        if (factoryBeanName != null) {
            if (this.singletons.contains(factoryBeanName)) {
                model = new SingletonFactoryModel(factoryBeanName);
            } else {
                model = new DynamicFactoryModel(factoryBeanName);
            }

            final String factoryMethodName = data.getFactoryMethodName();
            final Class<?> factoryType = classResolver.resolve(factoryBeanName);
            final Method factoryMethod = ReflectionUtils.findPublicMethod(factoryType, factoryMethodName, argumentTypes);
            if (factoryMethod == null) {
                throw new RuntimeException("Couldn't find method \"" + factoryMethodName + "\" for argument types "
                        + StringUtils.convertArray(argumentTypes) + " on the factory bean \"" + factoryBeanName
                        + "\" of type \"" + factoryType + "\"!");
            }

            final InstanceFactory<?> factory = new InstanceFactory<>();
            factory.setMethod(factoryMethod);

            model.setInstantiator(factory);
        } else {
            model = new InstantiationModel<>();
            final Constructor<?> constructor = ReflectionUtils.findPublicConstructor(beanType, argumentTypes);
            if (constructor == null) {
                throw new RuntimeException("Couldn't find a constructor with arguments "
                        + StringUtils.convertArray(argumentTypes) + " in the bean of type \""
                        + beanType + "\"!");
            }
            final InstanceConstructor<?> instantiator = new InstanceConstructor<>(constructor);
            model.setInstantiator(instantiator);
        }

        model.setArgumentManager(argumentManager);
        this.models.put(name, model);
    }

    /**
     * Adds the given bean which is an instance of a meta-class to the helper management domain.
     * @param name the bean name
     * @param classResolver a resolver object for other bean classes
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addBean(final String name, final ReferenceResolver<Class<?>> classResolver) {
        final Class<?> beanType = classResolver.resolve(name);

        final InstantiationModel model = new InstantiationModel();
        model.setArgumentManager(new ArgumentManager());
        model.setInstantiator(new Instantiator<Class<?>>() {

            @Override
            public Class<?> getInstance(final Object... arguments) {
                return beanType;
            }

        });

        this.onSingletonDetecetd(name);

        this.models.put(name, model);
    }

    private void onSingletonDetecetd(final String name) {
        this.uncachedSingletons.add(name);
        this.singletons.add(name);

        for (final BeanCacheListener listener : this.cacheListeners) {
            listener.onSingletonDetected(name);
        }
    }

    public void addCacheListener(final BeanCacheListener listener) {
        this.cacheListeners.add(listener);
    }

}
