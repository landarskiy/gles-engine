package by.vsu.mf.simplegles20.base.sprite;

import android.opengl.GLES20;
import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.util.Point2D;
import by.vsu.mf.simplegles20.core.util.Rect;

public class TailedSprite2D extends Sprite2D{

	private float stepX;
	private float stepY;
	private float currentX;
	private float currentY;
	private int animHandle;
	
	public TailedSprite2D(Texture texture, Rect textBounds, Shader shader,
			Rect spriteBounds, float stepX, float stepY) {
		super(texture, textBounds, shader, spriteBounds);
		this.stepX = stepX;
		this.stepY = stepY;
		this.currentX = 0;
		this.currentY = 0;
		
		animHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_anim");
	}

	public TailedSprite2D(Texture texture, Shader shader, Rect spriteBounds, 
			float stepX, float stepY) {
		this(texture,
				new Rect(new Point2D(0f, 0f), 1f, 1f), 
				shader, spriteBounds, stepX, stepY);
	}
	
	public void nextStep() {
		currentX += stepX;
		currentY += stepY;
		if(currentX > 1f) {
			currentX -= 1f;
		}
		if(currentY > 1f) {
			currentY -= 1f;
		}
	}

	@Override
	public void draw() {
		//shader.linkProgram();
        GLES20.glUseProgram(shader.getProgId());
        texture.use(0);
        
        positionBuffer.position(0);
        GLES20.glVertexAttribPointer(aPositionHandle, 2, GLES20.GL_FLOAT, false, 8, positionBuffer);
        GLES20.glEnableVertexAttribArray(aPositionHandle);
        
        textureCoordinatesBuffer.position(0);
        GLES20.glVertexAttribPointer(aTextureHandle, 2, GLES20.GL_FLOAT, false, 8, textureCoordinatesBuffer);
        GLES20.glEnableVertexAttribArray(aTextureHandle);
        
        GLES20.glUniform2f(uAnimHandle, currentX, currentY);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}
	
	
}
