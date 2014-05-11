package by.vsu.mf.simplegles20.core.texture;

import by.vsu.mf.simplegles20.core.util.StoredObject;
import android.opengl.GLES20;

public class Texture extends StoredObject{
    protected int textureId;
    protected int width;
    protected int height;
    
    public Texture() {
    	this.hash = 0;
    	this.textureId = 0;
    	this.width = 0;
    	this.height = 0;
    }    

    public void use(int textureSlot){
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureSlot);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
    }
    
    public void delete(){    	
        GLES20.glDeleteTextures(1, new int[]{textureId}, 0);
    }	    

	public int getTextureId() {
		return textureId;
	}

	public void setTextureId(int textureId) {
		this.textureId = textureId;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
    
}

