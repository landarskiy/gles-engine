package org.bananaLaba.bootstrap.common;

import java.util.HashMap;
import java.util.Map;

public final class TypeHintUtils {

    private static final Map<String, Class<?>> PRIMITIVE_TYPES = new HashMap<>();
    static {
        TypeHintUtils.PRIMITIVE_TYPES.put("float", float.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("double", double.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("long", long.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("int", int.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("char", char.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("short", short.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("byte", byte.class);
        TypeHintUtils.PRIMITIVE_TYPES.put("boolean", boolean.class);
    }

    private TypeHintUtils() {
    }

    public static Class<?> getPrimitiveType(final String name) {
        return TypeHintUtils.PRIMITIVE_TYPES.get(name);
    }

}
