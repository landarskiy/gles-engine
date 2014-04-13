package org.bananaLaba.fdp;

import java.io.IOException;
import java.io.InputStream;

public interface FDPLoader {

    XMLProcessor load(final InputStream xmlStream) throws IOException;

}
