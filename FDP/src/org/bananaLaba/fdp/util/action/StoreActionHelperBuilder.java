package org.bananaLaba.fdp.util.action;

import org.bananaLaba.bootstrap.common.Builder;
import org.bananaLaba.fdp.scenario.StoreActionSpecification;
import org.bananaLaba.fdp.simple.ActionHelper;
import org.bananaLaba.fdp.simple.StoreActionHelper;
import org.bananaLaba.fdp.util.argument.XMLArgumentFactory;

public class StoreActionHelperBuilder implements Builder<StoreActionSpecification, ActionHelper> {

    @Override
    public ActionHelper build(final StoreActionSpecification specification) {
        final StoreActionHelper helper = new StoreActionHelper();
        helper.setStoreKey(specification.getTargetId());

        final XMLArgumentFactory factory = XMLArgumentFactory.getInstance();
        helper.setArgument(factory.prepare(specification.getArgument()));

        return helper;
    }

}
