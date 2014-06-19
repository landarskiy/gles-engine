package org.bananaLaba.shaderModel.template.simple.meta;

import java.io.IOException;
import java.io.InputStream;

import org.bananaLaba.fdp.FDPLoader;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.simple.bootstrap.SimpleFDPLoader;
import org.bananaLaba.ioc.map.BeanMap;
import org.bananaLaba.shaderModel.ShaderFactory;
import org.bananaLaba.shaderModel.ShaderFactoryLoader;
import org.bananaLaba.shaderModel.template.ShaderFactoryPrototype;

// FIXME: supply the loader with a resource locator to be able to open scenario configuration in any environment.
public class SimpleShaderFactoryLoader implements ShaderFactoryLoader {

    private static final String BEAN_NAME_PROTOTYPE = "prototype";

    private ShaderFactoryPrototype metaFactory;

    @Override
    public ShaderFactory create() {
        if (this.metaFactory == null) {
            throw new IllegalStateException("A shader factory loader should be set up from a configuration input "
                    + "stream before usage!");
        }

        return this.metaFactory.create();
    }

    @Override
    public void setUp(final InputStream input) throws IOException {
        final BeanMap beanMap = new BeanMap();
        this.metaFactory = new SimpleShaderFactoryPrototype();
        beanMap.putBean(SimpleShaderFactoryLoader.BEAN_NAME_PROTOTYPE, this.metaFactory);

        final FDPLoader fdpLoader = new SimpleFDPLoader(beanMap);
        final XMLProcessor configurationProcessor =
                fdpLoader.load(SimpleShaderFactoryLoader.class.getResourceAsStream("shaderTemplateScenario.xml"));
        configurationProcessor.process(input);
    }

    @Override
    public void cleanUp() {
        this.metaFactory = null;
    }

}
