package org.bananaLaba.ioc.reflection;

import org.bananaLaba.ioc.simple.util.ReflectionUtils;

/**
 * A base class for objects that invoke something reflectively.
 * @author Judzin
 *
 */
public abstract class ReflectiveInvoker {

    // ========================================================================
    // Fields
    // ========================================================================
    /**
     * A flag specifying if the current invocation signature has variable arguments.
     */
    private boolean hasVararg;
    /**
     * The current argument count in the invocation signature.
     */
    private int argumentCount;

    // ========================================================================
    // Methods
    // ========================================================================
    protected void setHasVararg(final boolean has) {
        this.hasVararg = has;
    }

    protected void setArgumentCount(final int argumentCount) {
        this.argumentCount = argumentCount;
    }

    /**
     * Prepares the given arguments for such special cases like variable arguments methods.
     * @param values the argument values
     * @return argument values ready for passing to the invokable member
     */
    protected Object[] prepareArgumentValues(final Object... values) {
        if (this.hasVararg) {
            return ReflectionUtils.mergeVarargs(values, this.argumentCount);
        } else {
            return values;
        }
    }

}
