package org.bananaLaba.ioc.reflection;

import java.util.ArrayList;
import java.util.Collection;

import org.bananaLaba.ioc.injection.Argument;

/**
 * A class that encapsulates behavior of objects that manage a set of arguments from some communication specification.
 * Its instances are used to retrieve current values from a whole argument group in the right order.
 * @author Judzin
 *
 */
// TODO: looks like this class can be derived from the ArgumentGroup or contain its instance.
public class ArgumentManager {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A collection of all the group arguments that given direct access to entities/values associated with them.
     */
    private Collection<Argument<?>> simpleArguments = new ArrayList<>();
    /**
     * A collection of all the group arguments that refer to some entities that should be resolved somehow.
     */
    private Collection<Argument<String>> referenceArguments = new ArrayList<>();
    /**
     * The current argument count in both collections.
     */
    private int currentArgumentCount;

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Creates an argument manager with no arguments.
     */
    public ArgumentManager() {
    }

    // ========================================================================
    // Methods
    // ========================================================================
    public void addSimpleArgument(final Argument<?> argument) {
        this.simpleArguments.add(argument);
        this.currentArgumentCount++;
    }

    public void addSimpleArguments(final Collection<Argument<?>> arguments) {
        this.simpleArguments.addAll(arguments);
        this.currentArgumentCount += arguments.size();
    }

    public void clearSimpleArguments() {
        this.currentArgumentCount -= this.simpleArguments.size();
        this.simpleArguments.clear();
    }

    public void addReferenceArgument(final Argument<String> argument) {
        this.referenceArguments.add(argument);
        this.currentArgumentCount++;
    }

    public void addReferenceArguments(final Collection<Argument<String>> arguments) {
        this.referenceArguments.addAll(arguments);
        this.currentArgumentCount += arguments.size();
    }

    public void clearReferenceArguments() {
        this.currentArgumentCount -= this.referenceArguments.size();
        this.referenceArguments.clear();
    }

    public Object[] getArgumentValues(final ReferenceResolver<Object> resolver) {
        final Object[] values = new Object[this.currentArgumentCount];
        for (final Argument<?> argument : this.simpleArguments) {
            values[argument.getIndex()] = argument.getValue();
        }

        for (final Argument<String> argument : this.referenceArguments) {
            values[argument.getIndex()] = resolver.resolve(argument.getValue());
        }

        return values;
    }

}
