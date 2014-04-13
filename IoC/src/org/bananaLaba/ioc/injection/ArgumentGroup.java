package org.bananaLaba.ioc.injection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class which represents argument specification for communication with some invokable entity.
 * @author Judzin
 *
 */
public class ArgumentGroup {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A collection of all the group arguments that refer to some entities that should be resolved somehow.
     */
    // TODO: in future versions handle the case of dynamically changing dependency tree.
    private Collection<Argument<String>> references = new ArrayList<>();

    /**
     * A collection of all the group arguments that given direct access to entities/values associated with them.
     */
    private Collection<Argument<?>> primitives = new ArrayList<>();

    // ========================================================================
    // Methods
    // ========================================================================
    public Collection<Argument<String>> getReferences() {
        return new ArrayList<>(this.references);
    }

    public void addReferences(final Collection<Argument<String>> references) {
        this.references.addAll(references);
    }

    public void addReference(final Argument<String> reference) {
        this.references.add(reference);
    }

    public void clearReferences() {
        this.references.clear();
    }

    public Collection<Argument<?>> getPrimitives() {
        return new ArrayList<>(this.primitives);
    }

    public void addPrimitives(final Collection<Argument<?>> primitives) {
        this.primitives.addAll(primitives);
    }

    public void addPrimitive(final Argument<?> primitive) {
        this.primitives.add(primitive);
    }

    public void clearPrimitives() {
        this.primitives.clear();
    }

}
