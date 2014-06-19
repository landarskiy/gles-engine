package by.vsu.mf.rendering.jogl.gl2.api;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import by.vsu.mf.rendering.opengl.api.TextureAPI;

public class GL2TextureAPI extends GL2API implements TextureAPI {

    private final int[] generatedHandles = new int[1];
    private GL2TextureHandleStore handleStore;

    public void setHandleStore(final GL2TextureHandleStore handleStore) {
        this.handleStore = handleStore;
    }

    protected void prepare(final String name) {
    	this.gl.glGenTextures(1, this.generatedHandles, 0);
        int textureHandle = this.generatedHandles[0];

        this.handleStore.putTextureHandle(name, textureHandle);

        this.gl.glBindTexture(GL.GL_TEXTURE_2D, textureHandle);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP_TO_EDGE);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
    }
    
    @Override
    public void createTexture(final String name, final int width, final int height) {
        prepare(name);

        IntBuffer textureBuffer =
                ByteBuffer.allocateDirect(width * height * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        this.gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE,
                textureBuffer);

        // TODO: find out if this is correct action here when, for example, we are creating frame buffer.
        this.gl.glBindTexture(GL.GL_TEXTURE_2D, 0);        
    }

    @Override
	public void createTexture(final String name, final byte[] data) {
    	prepare(name);
    	try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
			DataBufferByte imageData = (DataBufferByte) img.getRaster().getDataBuffer();			
			IntBuffer textureBuffer =
					ByteBuffer.wrap(imageData.getData()).asIntBuffer();
	        this.gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, img.getWidth(), img.getHeight(), 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE,
	                textureBuffer);

	        // TODO: find out if this is correct action here when, for example, we are creating frame buffer.
	        this.gl.glBindTexture(GL.GL_TEXTURE_2D, 0); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Override
    public void activateTexture(final int slot, final String name) {
        this.gl.glActiveTexture(GL2TextureAPI.translateTextureSlot(slot));
        this.gl.glBindTexture(GL.GL_TEXTURE_2D, this.handleStore.getTextureHandle(name));
    }

    @Override
    public void deactivateTexture(final int slot) {
        this.gl.glActiveTexture(GL2TextureAPI.translateTextureSlot(slot));
        this.gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
    }

    private static int translateTextureSlot(final int slot) {
        switch (slot) {
            case 0:
                return GL.GL_TEXTURE0;
            case 1:
                return GL.GL_TEXTURE1;
            case 2:
                return GL.GL_TEXTURE2;
            default:
                throw new RuntimeException("Invalid texture slot number: " + slot
                        + "! Slots in [0; 2] are supported.");
        }
    }

}
