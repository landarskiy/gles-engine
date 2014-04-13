package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.ioc.injection.Argument;

public interface ArgumentStore {

    public static final String OBJECT_ARGUMENT_STORE = "argumentStore";

    void addPrimitive(final Argument<?> argument);

    void addReference(final Argument<String> argument);

}
