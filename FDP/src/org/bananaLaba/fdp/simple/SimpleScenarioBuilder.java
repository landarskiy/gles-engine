package org.bananaLaba.fdp.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bananaLaba.fdp.BeanCallBuilder;
import org.bananaLaba.fdp.ScenarioBuilder;
import org.bananaLaba.fdp.scenario.ActionSpecification;
import org.bananaLaba.fdp.scenario.AttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.CallActionSpecification;
import org.bananaLaba.fdp.scenario.CompositeAttributeArgumentSpecification;
import org.bananaLaba.fdp.scenario.ContextReferenceType;
import org.bananaLaba.fdp.scenario.ReferenceArgumentSpecification;
import org.bananaLaba.fdp.scenario.StoreActionSpecification;

public abstract class SimpleScenarioBuilder implements ScenarioBuilder {

    private String id;

    private List<ActionSpecification> actionSpecifications = new ArrayList<>();

    @Override
    public BeanCallBuilder getBeanCallBuilder() {
        return new SimpleBeanCallBuilder() {

            @Override
            protected void commitInternal(final CallActionSpecification specification) {
                SimpleScenarioBuilder.this.actionSpecifications.add(specification);
            }

        };
    }

    @Override
    public void clearBeanCalls() {
        this.actionSpecifications.clear();
    }

    @Override
    public void commit() {
        this.commitInernal(this.id, this.actionSpecifications);
    }

    protected abstract void commitInernal(final String id, final List<ActionSpecification> callSpecifications);

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public void addStoreAttributeAction(final String attributeName, final Class<?> type, final String storeKey) {
        final StoreActionSpecification actionSpecification = new StoreActionSpecification();
        actionSpecification.setTargetId(storeKey);

        final AttributeArgumentSpecification argumentSpecification = new AttributeArgumentSpecification();
        argumentSpecification.setAttributeName(attributeName);
        argumentSpecification.setTargetType(type);
        argumentSpecification.setTypeHint(type);

        actionSpecification.setArgument(argumentSpecification);

        this.actionSpecifications.add(actionSpecification);
    }

    @Override
    public void addStoreProjectionAction(final Class<?> beanType, final Map<String, String> attributeMapping,
            final String storeKey) {
        final StoreActionSpecification actionSpecification = new StoreActionSpecification();
        actionSpecification.setTargetId(storeKey);

        final CompositeAttributeArgumentSpecification argumentSpecification =
                new CompositeAttributeArgumentSpecification();
        argumentSpecification.setTargetType(beanType);
        argumentSpecification.setTypeHint(beanType);
        argumentSpecification.addProperties(attributeMapping);

        actionSpecification.setArgument(argumentSpecification);

        this.actionSpecifications.add(actionSpecification);
    }

    @Override
    public void addStoreBeanAaction(final String beanName, final String storeKey) {
        final StoreActionSpecification actionSpecification = new StoreActionSpecification();
        actionSpecification.setTargetId(storeKey);

        final ReferenceArgumentSpecification argumentSpecification = new ReferenceArgumentSpecification();
        argumentSpecification.setKey(beanName);
        argumentSpecification.setSource(ContextReferenceType.BEAN);

        actionSpecification.setArgument(argumentSpecification);

        this.actionSpecifications.add(actionSpecification);
    }

}
