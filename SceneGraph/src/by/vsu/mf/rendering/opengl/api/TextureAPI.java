package by.vsu.mf.rendering.opengl.api;

public interface TextureAPI {

	void createTexture(final String name, final byte[] data);
	
    void createTexture(final String name, final int width, final int height);

    void activateTexture(final int slot, final String name);

    void deactivateTexture(final int slot);

}
