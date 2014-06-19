package by.vsu.mf.rendering.opengl.texture;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.shaderModel.SimpleParameterMap;

import by.vsu.mf.rendering.opengl.api.TextureAPI;

public class TextureManager {

	private TextureFactory textureFactory;
	private TextureAPI textureAPI;
	private Map<Integer, TextureState> textureControls = new HashMap<>();

	public TextureFactory getTextureFactory() {
		return textureFactory;
	}

	public void setTextureFactory(TextureFactory textureFactory) {
		this.textureFactory = textureFactory;
	}

	public TextureAPI getTextureAPI() {
		return textureAPI;
	}

	public void setTextureAPI(TextureAPI textureAPI) {
		this.textureAPI = textureAPI;
	}

	public void process(TextureState state) {
		final int hash = state.hashCode();
		if (!textureControls.containsKey(hash)) {
			final TextureFactorySession textureSession = textureFactory.getSession(new SimpleParameterMap());
			textureAPI.createTexture(state.getTextureName(), textureSession.getData(state.getTextureName()));
			textureControls.put(hash, state);
		}
		
		textureAPI.activateTexture(state.getTextureSlot(),
				state.getTextureName());
	}
}
