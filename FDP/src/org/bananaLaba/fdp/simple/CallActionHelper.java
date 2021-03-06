package org.bananaLaba.fdp.simple;

import java.util.ArrayList;
import java.util.List;

import org.bananaLaba.fdp.XMLProcessorContext;
import org.bananaLaba.fdp.mapping.ValueSource;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.ContextReferenceType;
import org.bananaLaba.ioc.BeanContainer;

// TODO: modify scenario class hierarchy so e.g. call action helper could update the target method argument type hints
// and re-bind it if necessary (this will remove mandatory type hint from class constant arguments).
public class CallActionHelper implements ActionHelper {

    private List<XMLProcessorArgument<?>> arguments = new ArrayList<>();
    private MethodCall<Object> methodCall;
    private String beanName;
    private String resultKey;
    private BeanContainer container;
    private XMLProcessorContext context;
    private boolean callSkippable;
    private ContextReferenceType referenceType;

    public void addArgument(final XMLProcessorArgument<?> argument) {
        if (this.context != null) {
            argument.bind(this.context);
        }

        this.arguments.add(argument);
    }

    public void setMethodCall(final MethodCall<Object> injector) {
        this.methodCall = injector;
    }

    public void setBeanName(final String name) {
        this.beanName = name;
    }

    public String getResultKey() {
        return this.resultKey;
    }

    public void setResultKey(final String resultKey) {
        this.resultKey = resultKey;
    }

    public void setCallSkippable(final boolean skippable) {
        this.callSkippable = skippable;
    }

    public void setReferenceType(final ContextReferenceType referenceType) {
        if (referenceType == null) {
            throw new IllegalArgumentException("Expected a not-null reference type for a call action!");
        }

        this.referenceType = referenceType;
    }

    public BeanContainer getBeanContainer() {
        return this.container;
    }

    public XMLProcessorContext getContext() {
        return this.context;
    }

    @Override
    public void call() {
        if (this.callSkippable) {
            for (final ValueSource<?> argument : this.arguments) {
                if (!argument.isAvailable()) {
                    return;
                }
            }
        }

        Object bean = null;
        if (this.referenceType == ContextReferenceType.BEAN) {
            bean = this.container.getBean(this.beanName, Object.class);
        } else if (this.referenceType == ContextReferenceType.STORE) {
            bean = this.context.getTransientStore().getBean(this.beanName, Object.class);
        }
        final int argumentCount = this.arguments.size();
        final Object[] argumentValues = new Object[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            argumentValues[i] = this.arguments.get(i).getValue();
        }

        final Object result = this.methodCall.perform(bean, argumentValues);
        if (this.resultKey != null) {
            this.context.getTransientStore().put(this.resultKey, result);
        }
    }

    @Override
    public void bind(final XMLProcessorContext context) {
        this.container = context.getBeanContainer();
        this.context = context;

        for (final XMLProcessorArgument<?> argument : this.arguments) {
            argument.bind(context);
        }
    }

}
