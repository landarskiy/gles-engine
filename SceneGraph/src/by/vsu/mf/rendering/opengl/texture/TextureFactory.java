package by.vsu.mf.rendering.opengl.texture;

import org.bananaLaba.shaderModel.ParameterMap;

public interface TextureFactory {
	TextureFactorySession getSession(final ParameterMap parameters); 
}
