package org.bananaLaba.shaderModel;

import java.io.IOException;
import java.io.InputStream;

public interface ShaderFactoryLoader extends ShaderMetaFactory {

    void setUp(final InputStream input) throws IOException;

    void cleanUp();

}
