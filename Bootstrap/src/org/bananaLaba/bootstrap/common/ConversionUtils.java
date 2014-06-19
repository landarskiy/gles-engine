package org.bananaLaba.bootstrap.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class ConversionUtils {

    private static final Map<Class<?>, Converter<String, ?>> CONVERTERS = new HashMap<>();
    static {
        Converter<String, ?> converter = new Converter<String, Integer>() {

            @Override
            public Integer convert(final String value) {
                return Integer.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(int.class, converter);
        ConversionUtils.CONVERTERS.put(Integer.class, converter);

        converter = new Converter<String, Long>() {

            @Override
            public Long convert(final String value) {
                return Long.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(long.class, converter);
        ConversionUtils.CONVERTERS.put(Long.class, converter);

        converter = new Converter<String, Short>() {

            @Override
            public Short convert(final String value) {
                return Short.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(short.class, converter);
        ConversionUtils.CONVERTERS.put(Short.class, converter);

        converter = new Converter<String, Byte>() {

            @Override
            public Byte convert(final String value) {
                return Byte.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(byte.class, converter);
        ConversionUtils.CONVERTERS.put(Byte.class, converter);

        converter = new Converter<String, Character>() {

            @Override
            public Character convert(final String value) {
                if (value.length() != 1) {
                    throw new IllegalArgumentException("A string should have length=1 to be convertable to character!");
                } else {
                    return value.charAt(0);
                }
            }

        };
        ConversionUtils.CONVERTERS.put(char.class, converter);
        ConversionUtils.CONVERTERS.put(Character.class, converter);

        converter = new Converter<String, Float>() {

            @Override
            public Float convert(final String value) {
                return Float.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(float.class, converter);
        ConversionUtils.CONVERTERS.put(Float.class, converter);

        converter = new Converter<String, Double>() {

            @Override
            public Double convert(final String value) {
                return Double.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(double.class, converter);
        ConversionUtils.CONVERTERS.put(Double.class, converter);

        converter = new Converter<String, Boolean>() {

            @Override
            public Boolean convert(final String value) {
                return Boolean.valueOf(value);
            }

        };
        ConversionUtils.CONVERTERS.put(boolean.class, converter);
        ConversionUtils.CONVERTERS.put(Boolean.class, converter);

        converter = new Converter<String, String>() {

            @Override
            public String convert(final String value) {
                return value;
            }

        };
        ConversionUtils.CONVERTERS.put(String.class, converter);

        converter = new Converter<String, Class<?>>() {

            @Override
            public Class<?> convert(final String value) {
                try {
                    return Class.forName(value);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            }

        };
        ConversionUtils.CONVERTERS.put(Class.class, converter);
    }

    private ConversionUtils() {
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static<T> Converter<String, T> getStringConverter(final Class<T> type) {
        if (type.isEnum()) {
            final Class<? extends Enum> enumType = (Class<? extends Enum>) type;
            return new Converter<String, T>() {

                @Override
                public T convert(final String value) {
                    return (T) Enum.valueOf(enumType, value);
                }

            };
        }


        final Converter<String, ?> converter = ConversionUtils.CONVERTERS.get(type);
        if (converter == null) {
            throw new IllegalArgumentException("The type \"" + type.getName()
                    + "\" is not convertable from string by default!");
        } else {
            return (Converter<String, T>) converter;
        }
    }

    public static Object getConsant(final String name, final Class<?> type) {
        try {
            final Field field = type.getField(name);
            final int modifiers = field.getModifiers();
            if (Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers)) {
                return field.get(type);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("The field \"" + name + "\" not found in the \"" + type.getName() + "\" type!");
        } catch (IllegalAccessException | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("The field \"" + name + "\" in the \"" + type.getName()
                    + "\" type is not a constant!");
        }
    }

}
