package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import by.vsu.mf.rendering.jogl.gl2.api.GL2TextureHandleStore;
import by.vsu.mf.rendering.opengl.api.TextureAPI;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class GLES2TextureAPI implements TextureAPI {

    private final int[] generatedHandles = new int[1];
    private GL2TextureHandleStore handleStore;

    public void setHandleStore(final GL2TextureHandleStore handleStore) {
        this.handleStore = handleStore;
    }

    protected void prepare(String name) {
    	GLES20.glGenTextures(1, this.generatedHandles, 0);
        int textureHandle = this.generatedHandles[0];

        this.handleStore.putTextureHandle(name, textureHandle);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
    }
    
    @Override
    public void createTexture(final String name, final int width, final int height) {
        prepare(name);
        
        IntBuffer textureBuffer =
                ByteBuffer.allocateDirect(width * height * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, width, height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE,
                textureBuffer);

        // TODO: find out if this is correct action here when, for example, we are creating frame buffer.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    @Override
	public void createTexture(final String name, final byte[] data) {
    	prepare(name);
    	
    	Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		int format = GLUtils.getInternalFormat(bitmap);
		int type = GLES20.GL_UNSIGNED_BYTE;
		try {
			type = GLUtils.getType(bitmap);
		} catch (IllegalArgumentException e) {
			Log.e("OpenGL", "bitmap illegal type");
		}

		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, format, bitmap, type, 0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}
    
    @Override
    public void activateTexture(final int slot, final String name) {
        GLES20.glActiveTexture(GLES2TextureAPI.translateTextureSlot(slot));
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, this.handleStore.getTextureHandle(name));
    }

    @Override
    public void deactivateTexture(final int slot) {
        GLES20.glActiveTexture(GLES2TextureAPI.translateTextureSlot(slot));
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    private static int translateTextureSlot(final int slot) {
        switch (slot) {
            case 0:
                return GLES20.GL_TEXTURE0;
            case 1:
                return GLES20.GL_TEXTURE1;
            case 2:
                return GLES20.GL_TEXTURE2;
            default:
                throw new RuntimeException("Invalid texture slot number: " + slot
                        + "! Slots in [0; 2] are supported.");
        }
    }	

}
