package by.vsu.mf.simplegles20.base.sprite;

import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.util.Rect;

public class MultistateAnimatedSprite2D extends AnimatedSprite2D{

	private class Bound {
		int leftBound;
		int rightBound;
		public Bound(int leftBound, int rightBound) {
			this.leftBound = leftBound;
			this.rightBound = rightBound;
		}		
	}
	
	private String state;
	private Bound bound;
	private Map<String, Bound> states;
	
	public MultistateAnimatedSprite2D(Texture texture, Rect textBounds,
			Shader shader, Rect spriteBounds, int rowCount, int colCount,
			int frameCount) {
		super(texture, textBounds, shader, spriteBounds, rowCount, colCount, frameCount);
		this.bound = new Bound(0, frameCount);
		this.states = new HashMap<String, MultistateAnimatedSprite2D.Bound>();
	}
	
	public MultistateAnimatedSprite2D(Texture texture, Shader shader,
			Rect spriteBounds, int rowCount, int colCount, int frameCount) {
		super(texture, shader, spriteBounds, rowCount, colCount, frameCount);
		this.bound = new Bound(0, frameCount);
		this.states = new HashMap<String, MultistateAnimatedSprite2D.Bound>();
	}

	public void addState(String name, int startFrame, int endFrame) {
		states.put(name, new Bound(startFrame, endFrame));
	}
	
	public void setState(String name) {
		this.state = name;
		this.bound = states.get(name);
		this.currentFrame = bound.leftBound;
	}
	
	public String getState() {		
		return this.state;
	}
	
	public void nextFrame() {
		currentFrame++;
		if(currentFrame == bound.rightBound) {
			currentFrame = bound.leftBound;
		}
		updateTextureCoordinats(frameBounds[currentFrame]);
	}
}
