package org.bananaLaba.ioc.bean;

import java.util.Map;

import org.bananaLaba.ioc.injection.ArgumentGroup;

public interface BeanBuilder {

    void setType(final Class<?> type);

    void setName(final String name);

    void clearMethodInjections();

    /**
     * Specifies a mapping from the bean methods to argument groups that will be evaluated and injected to each the
     * bean instance after its instantiation.
     * Note that the ArgumentGroup objects are NOT copied, but are taken by reference.
     * @param injections the mapping
     */
    void addMethodInjections(final Map<String, ArgumentGroup> injections);

    /**
     * Specifies an argument group that will be evaluated and injected to each the bean instance after its
     * instantiation using a method with the given name.
     * Note that the ArgumentGroup object is NOT copied, but is taken by reference.
     * @param methodName the method name
     * @param arguments the argument group
     */
    void addMethodInjection(final String methodName, final ArgumentGroup arguments);

    /**
     * Specifies a mapping from the bean properties to argument groups that will be evaluated and injected to each the
     * bean instance after its instantiation.
     * Note that the ArgumentGroup objects are NOT copied, but are taken by reference.
     * @param injections the mapping
     */
    void setPropertyInjections(final Map<String, ArgumentGroup> injections);

    /**
     * Specifies an argument group that will be evaluated and injected to each the bean instance after its
     * instantiation through the given property.
     * Note that the ArgumentGroup object is NOT copied, but is taken by reference.
     * @param methodName the property name
     * @param arguments the argument group
     */
    void setPropertyInjection(final String name, final ArgumentGroup arguments);

    void clearPropertyInjections();

    /**
     * Commits the current builder data to its parent {@link org.bananaLaba.ioc.BeanDefinitionRegistry} instance, which
     * then registers the bean definition. If no remove operations will be performed after that, this definition will
     * be added to all container objects produced by the associated factory.
     * Note that when several builder commits for beans with the same names occurs, then the last commit will overwrite
     * all the preceding ones.
     */
    void commit();

}
