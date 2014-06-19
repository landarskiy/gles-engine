package org.bananaLaba.shaderModel.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.bananaLaba.shaderModel.ShaderFactory;
import org.bananaLaba.shaderModel.ShaderFactoryLoader;
import org.bananaLaba.shaderModel.SimpleParameterMap;
import org.bananaLaba.shaderModel.template.simple.meta.SimpleShaderFactoryLoader;

public class ShaderFactoryLoaderTest {

    public static void main(final String[] arguments) throws IOException {
        final ShaderFactoryLoader loader = new SimpleShaderFactoryLoader();
        loader.setUp(new FileInputStream("files/test/shaderFactoryTest.xml"));

        final ShaderFactory factory = loader.create();

        final SimpleParameterMap parameters = new SimpleParameterMap();
        parameters.setParameter("lighting", true);
        parameters.setParameter("test", true);
        parameters.setParameter("numLights", 3);

        System.out.println("[Vertex shader]");
        System.out.println(factory.getSession(parameters).getShaderCode("vertexShader"));

        System.out.println("[Fragment shader]");
        System.out.println(factory.getSession(parameters).getShaderCode("fragmentShader"));
    }

}
