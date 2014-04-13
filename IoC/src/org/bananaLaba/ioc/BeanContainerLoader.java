package org.bananaLaba.ioc;

import java.io.IOException;
import java.io.InputStream;

public interface BeanContainerLoader {

    BeanContainer load(final InputStream stream) throws IOException;

}
