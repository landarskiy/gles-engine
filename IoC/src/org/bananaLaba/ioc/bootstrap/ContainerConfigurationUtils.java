package org.bananaLaba.ioc.bootstrap;

public final class ContainerConfigurationUtils {

    private ContainerConfigurationUtils() {
    }

    public static String getAnonymousBeanName(final String parent, final int id) {
        final StringBuilder nameBuilder = new StringBuilder("$");
        nameBuilder.append(id).append("$").append(parent);

        return nameBuilder.toString();
    }

}
