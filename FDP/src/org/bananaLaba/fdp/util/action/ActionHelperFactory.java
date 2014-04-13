package org.bananaLaba.fdp.util.action;

import org.bananaLaba.bootstrap.common.BaseStandardFactory;
import org.bananaLaba.fdp.scenario.ActionSpecification;
import org.bananaLaba.fdp.scenario.CallActionSpecification;
import org.bananaLaba.fdp.scenario.StoreActionSpecification;
import org.bananaLaba.fdp.simple.ActionHelper;

public class ActionHelperFactory extends BaseStandardFactory<ActionSpecification, ActionHelper> {

    private static ActionHelperFactory instance;

    private ActionHelperFactory() {
        this.bindBuilder(CallActionSpecification.class, new CallActionHelperBuilder());
        this.bindBuilder(StoreActionSpecification.class, new StoreActionHelperBuilder());
    }

    public static ActionHelperFactory getInstance() {
        if (ActionHelperFactory.instance == null) {
            ActionHelperFactory.instance = new ActionHelperFactory();
        }

        return ActionHelperFactory.instance;
    }

}
