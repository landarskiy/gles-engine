package org.bananaLaba.fdp.util.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bananaLaba.bootstrap.common.Builder;
import org.bananaLaba.fdp.mapping.ContextReference;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;
import org.bananaLaba.fdp.scenario.ArgumentSpecification;
import org.bananaLaba.fdp.scenario.CallActionSpecification;
import org.bananaLaba.fdp.scenario.ContextReferenceType;
import org.bananaLaba.fdp.simple.ActionHelper;
import org.bananaLaba.fdp.simple.CallActionHelper;
import org.bananaLaba.fdp.util.argument.XMLArgumentFactory;
import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.simple.injectors.MethodInjector;
import org.bananaLaba.ioc.simple.util.ReflectionUtils;

public class CallActionHelperBuilder implements Builder<CallActionSpecification, ActionHelper> {

    @Override
    public ActionHelper build(final CallActionSpecification specification) {
        final CallActionHelper helper = new CallActionHelper();
        helper.setBeanName(specification.getTargetId());
        helper.setCallSkippable(specification.isSkippable());

        final XMLArgumentFactory factory = XMLArgumentFactory.getInstance();
        final List<ArgumentSpecification> argumentSpecifications = specification.getArguments();
        final int argumentCount = argumentSpecifications.size();
        final Class<?>[] argumentTypes = new Class<?>[argumentCount];
        final Map<Integer, String> typelessBeanArguments = new HashMap<>();
        final Map<Integer, String> typelessStoredArguments = new HashMap<>();
        for (int i = 0; i < argumentCount; i++) {
            final ArgumentSpecification argumentSpecification = argumentSpecifications.get(i);
            argumentTypes[i] = argumentSpecification.getTypeHint();

            final XMLProcessorArgument<?> argument = factory.prepare(argumentSpecification);
            helper.addArgument(argument);

            // TODO: maybe there is a better solution of deferred type resolution for managed and stored beans?
            if (argumentTypes[i] == null) {
                if (argument.getClass() == ContextReference.class) {
                    final ContextReference referenceArgument = (ContextReference) argument;
                    final String referenceId = referenceArgument.getBeanName();
                    if (referenceArgument.getReferenceType() == ContextReferenceType.BEAN) {
                        typelessBeanArguments.put(i, referenceId);
                    } else {
                        typelessStoredArguments.put(i, referenceId);
                    }
                } else {
                    throw new IllegalArgumentException("Cannot resolve type of the argument with index " + (i + 1)
                            + " in the method \"" + specification.getMethodName() + " of the bean \""
                            + specification.getTargetId()
                            + "\"! It neither has an explicit type hint nor refers to a container-managed bean.");
                }
            }
        }

        final String beanMethodName = specification.getMethodName();
        final MethodInjector<Object> injector = new MethodInjector<Object>() {

            private boolean initialized;

            @Override
            public void apply(final Object bean, final Object... arguments) {
                if (!this.initialized) {
                    // If some arguments for this call have not been supplied by an explicit type hint, but refer
                    // to beans managed by a container either stored in the transient store, this is the last chance to
                    // resolve their types.
                    // FIXME: at least catch exceptions thrown by container or transient store and wrap them with custom
                    // exceptions that tell that deferred type resolution has failed. Otherwise at this point these
                    // exceptions are very hard to understand.
                    Set<Entry<Integer, String>> typelessEntries = typelessBeanArguments.entrySet();
                    final BeanContainer container = helper.getBeanContainer();
                    for (final Entry<Integer, String> typelessEntry : typelessEntries) {
                        argumentTypes[typelessEntry.getKey()] = container.getBeanType(typelessEntry.getValue());
                    }

                    typelessEntries = typelessStoredArguments.entrySet();
                    final BeanContainer transientStore = helper.getContext().getTransientStore();
                    for (final Entry<Integer, String> typelessEntry : typelessEntries) {
                        argumentTypes[typelessEntry.getKey()] = transientStore.getBeanType(typelessEntry.getValue());
                    }

                    this.setMethod(ReflectionUtils.findPublicMethod(bean.getClass(), beanMethodName, argumentTypes));
                }
                super.apply(bean, arguments);
            }

        };
        helper.setInjector(injector);

        return helper;
    }

}
