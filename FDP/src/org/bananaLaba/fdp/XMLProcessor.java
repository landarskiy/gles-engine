package org.bananaLaba.fdp;

import java.io.IOException;
import java.io.InputStream;

import org.bananaLaba.ioc.BeanContainer;

public interface XMLProcessor {

    void process(final InputStream xmlStream) throws IOException;

    void setBeanContainer(final BeanContainer container);

}
