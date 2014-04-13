package org.bananaLaba.ioc.simple.beanDefinition;

import java.util.ArrayList;
import java.util.Collection;

import org.bananaLaba.ioc.injection.ArgumentGroup;

/**
 * A class which encapsulates a part of a bean definition that specifies the bean instance state after its creation.
 * @author Judzin
 *
 */
public class BeanPropertyData {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * Property injections that re-create the desired state for the bean instance after it's created.
     */
    private Collection<InjectionData> injections = new ArrayList<>();

    // ========================================================================
    // Methods
    // ========================================================================
    public void addInjection(final String methodName, final ArgumentGroup arguments) {
        this.injections.add(new InjectionData(methodName, arguments));
    }

    public void addInjection(final InjectionData injection) {
        this.injections.add(injection);
    }

    public Collection<InjectionData> getInjections() {
        return new ArrayList<>(this.injections);
    }

    public void clearInjections() {
        this.injections.clear();
    }

}
