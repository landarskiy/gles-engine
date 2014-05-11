package by.vsu.mf.simplegles20.base.sprite;

import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.util.Point2D;
import by.vsu.mf.simplegles20.core.util.Rect;

public class AnimatedSprite2D extends Sprite2D{

	protected Rect[] frameBounds;
	protected int currentFrame;
	
	public AnimatedSprite2D(Texture texture, Rect textBounds, Shader shader,
			Rect spriteBounds, int rowCount, int colCount, int frameCount) {
		super(texture, textBounds, shader, spriteBounds);
		initFrameBounds(rowCount, colCount, frameCount);
		this.currentFrame = 1;
		updateTextureCoordinats(frameBounds[currentFrame]);
	}

	public AnimatedSprite2D(Texture texture, Shader shader, Rect spriteBounds,
			int rowCount, int colCount, int frameCount) {
		this(texture,
				new Rect(new Point2D(0f, 0f), 1f, 1f), 
				shader, spriteBounds, rowCount,
				colCount, frameCount);
	}
	
	private void initFrameBounds(int rowCount, int colCount, int frameCount) {
		float stepX = textBounds.w / (float)colCount;
		float stepY = textBounds.h / (float)rowCount;
		frameBounds = new Rect[frameCount];
		int frame = 0;
		for(int i = 0 ; i<rowCount ; i++) {
			for(int j = 0 ; j<colCount; j++) {
				Rect rect = new Rect(
						new Point2D(textBounds.leftTop.x + j*stepX,
								textBounds.leftTop.y + i*stepY), 
						stepX, stepY);
				frameBounds[frame++] = rect;
				if(frame>=frameCount) {
					break;
				}
			}
		}
	}
	
	public void nextFrame() {
		currentFrame++;
		if(currentFrame == frameBounds.length) {
			currentFrame = 1;
		}
		updateTextureCoordinats(frameBounds[currentFrame]);
	}
	
	public void setFrame(int frame) {
		this.currentFrame = frame;
		updateTextureCoordinats(frameBounds[currentFrame]);
	}
	
	public int getFrame() {
		return currentFrame;
	}
}
