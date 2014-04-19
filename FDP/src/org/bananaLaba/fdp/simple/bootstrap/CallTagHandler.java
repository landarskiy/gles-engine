package org.bananaLaba.fdp.simple.bootstrap;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.fdp.BeanCallBuilder;
import org.bananaLaba.fdp.scenario.ContextReferenceType;

public class CallTagHandler implements ExtendedTagHandler {

    private static final String ATTRIBUTE_BEAN = "bean";
    private static final String ATTRIBUTE_STORE_KEY = "storeKey";
    private static final String ATTRIBUTE_METHOD = "method";
    private static final String ATTRIBUTE_ON_MISSING_ARGUMENTS = "onMissingArguments";

    private BeanCallBuilder builder;

    public CallTagHandler(final BeanCallBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void handle(final AttributeMap attributes) {
        final boolean needsContainerBean = attributes.isPresent(CallTagHandler.ATTRIBUTE_BEAN);
        final boolean needsStoredBean = attributes.isPresent(CallTagHandler.ATTRIBUTE_STORE_KEY);

        if ((needsContainerBean && needsStoredBean) || (!needsContainerBean && !needsStoredBean)) {
            // TODO: throw a custom exception here.
            throw new RuntimeException("One and only one of the \"bean\" and \"storeKey\""
                    + "attributes are allowed for the \"call\" tag!");
        }

        if (needsContainerBean) {
            this.builder.setBean(attributes.getAttribute(CallTagHandler.ATTRIBUTE_BEAN), ContextReferenceType.BEAN);
        } else {
            this.builder.setBean(attributes.getAttribute(CallTagHandler.ATTRIBUTE_STORE_KEY),
                    ContextReferenceType.STORE);
        }

        this.builder.setMethodName(attributes.getAttribute(CallTagHandler.ATTRIBUTE_METHOD));

        MissingArgumentsPolicy policy = MissingArgumentsPolicy.THROW;
        if (attributes.isPresent(CallTagHandler.ATTRIBUTE_ON_MISSING_ARGUMENTS)) {
            policy = MissingArgumentsPolicy.valueOf(
                    attributes.getAttribute(CallTagHandler.ATTRIBUTE_ON_MISSING_ARGUMENTS).toUpperCase());
        }

        if (policy == MissingArgumentsPolicy.SKIP) {
            this.builder.setSkippable(true);
        } else {
            this.builder.setSkippable(false);
        }
    }

    @Override
    public void close() {
        this.builder.commit();
        this.builder.clearArguments();
    }

    private static enum MissingArgumentsPolicy {

        SKIP,
        THROW

    }

    @Override
    public void setContext(final TagContext context) {
    }

    @Override
    public void handleCharacterData(final String data) {
    }

}
