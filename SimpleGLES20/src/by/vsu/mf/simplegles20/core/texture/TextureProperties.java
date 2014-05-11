package by.vsu.mf.simplegles20.core.texture;

public class TextureProperties {
	private boolean filter;
	private boolean dupli;
	private boolean mipmaps;

	public TextureProperties() {
		this(false, false, false);
	}

	public TextureProperties(boolean filter, boolean dupli, boolean mipmaps) {
		this.filter = filter;
		this.dupli = dupli;
		this.mipmaps = mipmaps;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public boolean isDupli() {
		return dupli;
	}

	public void setDupli(boolean dupli) {
		this.dupli = dupli;
	}

	public boolean isMipmaps() {
		return mipmaps;
	}

	public void setMipmaps(boolean mipmaps) {
		this.mipmaps = mipmaps;
	}

}
