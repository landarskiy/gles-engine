package org.bananaLaba.ioc.injection;

public final class ArgumentUtils {

    private ArgumentUtils() {
    }

    public static ArgumentGroup getSingleReferenceGroup(final String reference, final Class<?> typeHint) {
        final ArgumentGroup group = new ArgumentGroup();
        final SimpleArgument<String> argument = new SimpleArgument<>(typeHint, reference);
        group.addReference(argument);

        return group;
    }

    public static ArgumentGroup getSingleReferenceGroup(final String reference) {
        return ArgumentUtils.getSingleReferenceGroup(reference, null);
    }

    public static ArgumentGroup getSinglePrimitiveGroup(final Object value, final Class<?> typeHint) {
        final ArgumentGroup group = new ArgumentGroup();
        final SimpleArgument<Object> argument = new SimpleArgument<Object>(value);
        argument.setTypeHint(typeHint);
        group.addPrimitive(argument);

        return group;
    }

    public static ArgumentGroup getSinglePrimitiveGroup(final Object value) {
        final ArgumentGroup group = new ArgumentGroup();
        final SimpleArgument<Object> argument = new SimpleArgument<Object>(value);
        group.addPrimitive(argument);

        return group;
    }

}
