package by.vsu.mf.rendering.opengl.texture;

public class TextureState {
	private int textureSlot;
	private String textureName;

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

	public int getTextureSlot() {
		return textureSlot;
	}

	public void setTextureSlot(int textureSlot) {
		this.textureSlot = textureSlot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((textureName == null) ? 0 : textureName.hashCode());
		result = prime * result + textureSlot;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextureState other = (TextureState) obj;
		if (textureName == null) {
			if (other.textureName != null)
				return false;
		} else if (!textureName.equals(other.textureName))
			return false;
		if (textureSlot != other.textureSlot)
			return false;
		return true;
	}	

}
