package org.bananaLaba.ioc.simple;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bananaLaba.ioc.injection.Argument;
import org.bananaLaba.ioc.injection.ArgumentGroup;
import org.bananaLaba.ioc.reflection.ArgumentManager;
import org.bananaLaba.ioc.reflection.ReferenceResolver;
import org.bananaLaba.ioc.simple.beanDefinition.BeanPropertyData;
import org.bananaLaba.ioc.simple.beanDefinition.InjectionData;
import org.bananaLaba.ioc.simple.injectors.MethodInjector;
import org.bananaLaba.ioc.simple.util.ReflectionUtils;
import org.bananaLaba.util.StringUtils;

/**
 * A helper class which manages all bean property models within an IoC-container.
 * @author Judzin
 *
 */
public class InjectionManager implements BeanCacheListener {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A mapping from the bean names to their property models.
     */
    private Map<String, PropertyModel> models = new HashMap<>();
    /**
     * A set of names of singletons that have not had injections yet.
     */
    private Set<String> unprocessedSingletons = new HashSet<>();
    /**
     * A set of names of singleton beans.
     */
    private Set<String> singletons = new HashSet<>();

    // ========================================================================
    // Methods
    // ========================================================================
    /**
     * Adds a bean property model to the helper management domain.
     * @param name the bean name
     * @param data the property model data
     * @param classResolver a resolver object for bean classes
     */
    public void addBean(final String name, final BeanPropertyData data,
            final ReferenceResolver<Class<?>> classResolver) {
        final PropertyModel model = new PropertyModel();
        this.models.put(name, model);
        final Collection<InjectionData> namedInjections = data.getInjections();
        for (final InjectionData namedInjection : namedInjections) {
            final ArgumentGroup injection = namedInjection.getArguments();
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

            final Class<?> beanType = classResolver.resolve(name);
            final String methodName = namedInjection.getMethodName();
            final Method method = ReflectionUtils.findPublicMethod(beanType, methodName, argumentTypes);
            if (method == null) {
                throw new RuntimeException("Couldn't find the \"" + methodName + "\" for argument types "
                        + StringUtils.convertArray(argumentTypes) + " on the \"" + name + "\" bean of \"" + beanType
                        + "\" type for preparing injection!");
            }

            model.addInjection(new MethodInjector<>(method), argumentManager);
        }
    }

    /**
     * Re-creates the given bean's state from its property model.
     * @param name the bean name
     * @param bean the bean instance
     * @param referenceResolver a resolver object for references to other beans managed within the enclosing
     * IoC-container
     */
    public void apply(final String name, final Object bean, final ReferenceResolver<Object> referenceResolver) {
        if (this.singletons.contains(name)) {
            if (this.unprocessedSingletons.contains(name)) {
                this.unprocessedSingletons.remove(name);
            } else {
                return;
            }
        }

        final PropertyModel model = this.models.get(name);
        if (model != null) {
            model.apply(bean, referenceResolver);
        }
    }

    @Override
    public void onSingletonCached(final String name) {
    }

    @Override
    public void onSingletonDetected(final String name) {
        this.unprocessedSingletons.add(name);
        this.singletons.add(name);
    }

}
