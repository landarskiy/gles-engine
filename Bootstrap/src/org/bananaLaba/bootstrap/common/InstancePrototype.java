package org.bananaLaba.bootstrap.common;

public class InstancePrototype<T> implements Prototype<T> {

    private T instance;

    private InstancePrototype(final T instance) {
        this.instance = instance;
    }

    @Override
    public T create() {
        return this.instance;
    }

    public static<I> Prototype<I> prepare(final I instance) {
        return new InstancePrototype<I>(instance);
    }

}
