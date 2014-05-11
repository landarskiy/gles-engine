package by.vsu.mf.simplegles20.core.texture.storage;

import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.util.Rect;

public class TextureInfo {
	private Texture texture;
	private Rect dimension;

	public TextureInfo(Texture texture, Rect dimension) {
		super();
		this.texture = texture;
		this.dimension = dimension;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Rect getDimension() {
		return dimension;
	}

	public void setDimension(Rect dimension) {
		this.dimension = dimension;
	}

}
