package org.bananaLaba.bootstrap.test;

import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.bootstrap.common.Converter;

public class ConversionUtilsTest {

    public static void main(final String[] arguments) {
        final String value = "ONE";
        final Converter<String, TestEnum> converter = ConversionUtils.getStringConverter(TestEnum.class);
        System.out.println(converter.convert(value));

        System.out.println(ConversionUtils.getConsant("INTEGER", Constants.class));
    }

    public static enum TestEnum {
        ONE, TWO
    }

    public static class Constants {
        public static final int INTEGER = 23;
    }

}
