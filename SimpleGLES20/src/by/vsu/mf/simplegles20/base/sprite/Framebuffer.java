package by.vsu.mf.simplegles20.base.sprite;

import android.opengl.GLES20;
import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.util.Rect;

public class Framebuffer extends Sprite2D{

	public Framebuffer(Texture texture, Shader shader, Rect spriteBounds) {
		super(texture, shader, spriteBounds);
	}

	public void draw(){
		//shader.linkProgram();
        GLES20.glUseProgram(shader.getProgId());
        texture.use(0);
        
        positionBuffer.position(0);
        GLES20.glVertexAttribPointer(aPositionHandle, 2, GLES20.GL_FLOAT, false, 8, positionBuffer);
        GLES20.glEnableVertexAttribArray(aPositionHandle);
        
        textureCoordinatesBuffer.position(0);
        GLES20.glVertexAttribPointer(aTextureHandle, 2, GLES20.GL_FLOAT, false, 8, textureCoordinatesBuffer);
        GLES20.glEnableVertexAttribArray(aTextureHandle);
                
        GLES20.glUniform2f(uAnimHandle, 0f, 0f);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }	
}
