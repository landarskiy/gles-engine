package org.bananaLaba.ioc.simple.beanDefinition;

import org.bananaLaba.ioc.injection.ArgumentGroup;

public class InjectionData {

    private String methodName;
    private ArgumentGroup arguments;

    public InjectionData() {
    }

    public InjectionData(final String methodName, final ArgumentGroup arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public ArgumentGroup getArguments() {
        return this.arguments;
    }

    public void setArguments(final ArgumentGroup arguments) {
        this.arguments = arguments;
    }

}
