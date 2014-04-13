package org.bananaLaba.fdp.simple;

import org.bananaLaba.fdp.XMLProcessorContext;
import org.bananaLaba.fdp.mapping.ValueSource;
import org.bananaLaba.fdp.mapping.XMLProcessorArgument;

public class StoreActionHelper implements ActionHelper {

    private XMLProcessorArgument<?> argument;
    private String storeKey;
    private XMLProcessorContext context;
    private ValueStore store;

    @Override
    public void call() {
        this.store.put(this.storeKey, this.argument.getValue());
    }

    public ValueSource<?> getArgument() {
        return this.argument;
    }

    public void setArgument(final XMLProcessorArgument<?> argument) {
        if (this.context != null) {
            argument.bind(this.context);
        }

        this.argument = argument;
    }

    public String getStoreKey() {
        return this.storeKey;
    }

    public void setStoreKey(final String storeKey) {
        this.storeKey = storeKey;
    }

    @Override
    public void bind(final XMLProcessorContext context) {
        this.store = context.getTransientStore();
        this.context = context;
        this.argument.bind(context);
    }

}
