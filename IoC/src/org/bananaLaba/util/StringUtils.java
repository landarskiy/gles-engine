package org.bananaLaba.util;

/**
 * An utility class with some useful string-related methods.
 * @author Judzin
 *
 */
public final class StringUtils {

    // ========================================================================
    // Constructors
    // ========================================================================
    /**
     * Just to forbid this class instances.
     */
    private StringUtils() {
    }

    // ========================================================================
    // Static methods
    // ========================================================================
    /**
     * Converts the given array to a JSON-string.
     * @param array the array to convert
     * @return the string
     */
    public static String convertArray(final Object[] array) {
        if (array == null) {
            return "null";
        } else {
            final StringBuilder builder = new StringBuilder("[");
            boolean isFirst = true;
            for (final Object item : array) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(", ");
                }
                builder.append(item);
            }
            builder.append("]");

            return builder.toString();
        }
    }

}
