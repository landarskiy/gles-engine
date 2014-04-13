package org.bananaLaba.ioc.simple.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * An utility class with some useful methods for working with reflections.
 * @author Judzin
 *
 */
// FIXME: throw exceptions with detailed messages for such cases as ambiguous method search results.
// TODO: consider creating a separate hierarchy of reflective toolkit classes that allow, for example, caching
// reflection meta-objects. That allows in such cases when on the bean definition building stage store the meta-objects
// in these caches after checking that they exist and are accessible. Then the caches can be passed to an instance of
// an IoC-container to improve its initialization timings. Also different beans can share the same meta-objects from
// these caches.
// TODO: consider adding to the IoC-container such features as meta-class extensions.
// TODO: if it would reasonable it's possible to introduce the following method/constructor signature metric and choose the method with the smallest metric value:
// (argumentType1 - memberArgumentType1) * argumentCount + ... + (argumentTypeN - memberArgumentTypeN) * argumentCount.
public final class ReflectionUtils {

    // ========================================================================
    // Static fields
    // ========================================================================
    /**
     * A mapping from formal classes of the primitive Java types to their one-dimensional array classes.
     */
    private static final Map<Class<?>, Class<?>> PRIMITIVE_ARRAY_CLASSES = new HashMap<>();
    static {
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(short.class, short[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(byte.class, byte[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(char.class, char[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(boolean.class, boolean[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(int.class, int[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(long.class, long[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(float.class, float[].class);
        ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.put(double.class, double[].class);
    }

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Just to forbid instances of this class.
     */
    private ReflectionUtils() {
    }

    // ========================================================================
    // Static methods
    // ========================================================================
    /**
     * Gets the one-dimensional array class for the given type.
     * @param type the type
     * @return the array class
     */
    @SuppressWarnings("unchecked")
    public static<T> Class<T[]> getArrayClass(final Class<T> type) {
        if (type.isPrimitive()) {
            return (Class<T[]>) ReflectionUtils.PRIMITIVE_ARRAY_CLASSES.get(type);
        } else {
            try {
                return (Class<T[]>) Class.forName(String.format("[L%s;", type.getName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Merges the given array of objects that should be passed to a vararg method/constructor.
     * @param arguments the argument objects
     * @param argumentCount the actual argument count in the method/constructor signature
     * @return an array of argument objects merged according to the signature
     */
    public static Object[] mergeVarargs(final Object[] arguments, final int argumentCount) {
        final Object[] mergedArguments = new Object[argumentCount];
        final int nonVarargCount = argumentCount - 1;
        for (int i = 0; i < nonVarargCount; i++) {
            mergedArguments[i] = arguments[i];
        }

        final Class<? extends Object[]> varargType =
                ReflectionUtils.getArrayClass(arguments[nonVarargCount].getClass());
        mergedArguments[nonVarargCount] = Arrays.copyOfRange(arguments, nonVarargCount, argumentCount, varargType);

        return mergedArguments;
    }

    /**
     * Finds the getter method for property of a class that meets bean conventions.
     * @param propertyName the property name
     * @param type the class
     * @return the getter
     */
    public static Method getPropertyGetter(final String propertyName, final Class<?> type) {
        final String getterName =
                ReflectionUtils.getPropertyGetterName(propertyName, type);
        try {
            return type.getMethod(getterName, new Class<?>[] {});
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds the name of the getter method for property of a class that meets bean conventions.
     * @param propertyName the property name
     * @param type the class
     * @return the name of the getter
     */
    // FIXME: ensure that when the type is Class the property is at least static and when the type is not Class
    // the property is at least not static.
    // FIXME: check if the found field has an accessor declared on the same type hierarchy, cause if it hasn't this is not a bean property.
    public static String getPropertyGetterName(final String propertyName, final Class<?> type) {
        Field property = null;
        Class<?> currentType = null;
        NoSuchFieldException ex = null;
        do {
            currentType = currentType == null ? type : currentType.getSuperclass();
            try {
                property = currentType.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                ex = e;
            }
        } while ((type != Object.class) && (property == null));

        if (property == null) {
            throw new RuntimeException(ex);
        }

        final boolean primitiveBoolean = property.getType() == boolean.class;
        final StringBuilder getterNameBuilder = new StringBuilder(primitiveBoolean ? "is" : "get");
        getterNameBuilder.append(propertyName.substring(0, 1).toUpperCase()).append(propertyName.substring(1));

        return getterNameBuilder.toString();
    }

    // FIXME: move property field lookup to a separate method.
    public static Method getPropertySetter(final String propertyName, final Class<?> type) {
        Field property = null;
        Class<?> currentType = null;
        NoSuchFieldException ex = null;
        do {
            currentType = currentType == null ? type : currentType.getSuperclass();
            try {
                property = currentType.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                ex = e;
            }
        } while ((type != Object.class) && (property == null));

        if (property == null) {
            throw new RuntimeException(ex);
        }

        final StringBuilder getterNameBuilder = new StringBuilder("set");
        getterNameBuilder.append(propertyName.substring(0, 1).toUpperCase()).append(propertyName.substring(1));

        try {
            return type.getMethod(getterNameBuilder.toString(), property.getType());
        } catch (Exception e) {
            // TODO Use special exception type here.
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a method that meets the given signature within the given class.
     * @param type the class
     * @param name the method name
     * @param argumentTypes the method argument types
     * @return the method or null if it couldn't be found
     */
    // TODO: implement support for vararg methods.
    public static Method findPublicMethod(final Class<?> type, final String methodName,
            final Class<?>... argumentTypes) {
        try {
            return type.getMethod(methodName, argumentTypes);
        } catch (final NoSuchMethodException e) {
        }

        Method foundMethod = null;
        final Method[] publicMethods = type.getMethods();
        int foundCount = 0;
        final int targetArgumentCount = argumentTypes.length;
        for (final Method method : publicMethods) {
            if (method.getName().equals(methodName)) {
                final Class<?>[] methodArgumentTypes = method.getParameterTypes();
                if (methodArgumentTypes.length == targetArgumentCount) {
                    boolean matches = true;
                    for (int i = 0; i < targetArgumentCount; i++) {
                        if (!methodArgumentTypes[i].isAssignableFrom(argumentTypes[i])) {
                            matches = false;
                            break;
                        }
                    }

                    if (matches) {
                        foundMethod = method;
                        foundCount++;
                    }
                }
            }

            if (foundCount > 1) {
                return null;
            }
        }

        return foundMethod;
    }

    /**
     * Finds a constructor that meets the given signature within the given class.
     * @param type the class
     * @param argumentTypes the method argument types
     * @return the constructor or null if it couldn't be found
     */
    // TODO: implement support for vararg constructors.
    @SuppressWarnings("unchecked")
    public static<T> Constructor<T> findPublicConstructor(final Class<T> type, final Class<?>... argumentTypes) {
        try {
            return type.getConstructor(argumentTypes);
        } catch (NoSuchMethodException e) {
        }

        Constructor<T> foundConstructor = null;
        final Constructor<T>[] publicConstructors = (Constructor<T>[]) type.getConstructors();
        int foundCount = 0;
        final int targetArgumentCount = argumentTypes.length;
        for (final Constructor<T> constructor : publicConstructors) {
            final Class<?>[] constructorArgumentTypes = constructor.getParameterTypes();
            if (constructorArgumentTypes.length == targetArgumentCount) {
                boolean matches = true;
                for (int i = 0; i < targetArgumentCount; i++) {
                    if (!constructorArgumentTypes[i].isAssignableFrom(argumentTypes[i])) {
                        matches = false;
                        break;
                    }
                }

                if (matches) {
                    foundConstructor = constructor;
                    foundCount++;
                }
            }

            if (foundCount > 1) {
                return null;
            }
        }

        return foundConstructor;
    }

}
