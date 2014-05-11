package by.vsu.mf.simplegles20.base.sprite;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.util.Point2D;
import by.vsu.mf.simplegles20.core.util.Rect;

public class Sprite2D {
	protected Texture texture;
	protected Shader shader;
	
	protected Rect spriteBounds;
	protected Rect textBounds;		
	
	protected float vertexPosition[] = new float[8];
	protected float texturePosition[] = new float[8];
	
	protected float spriteTransformMatrix[] = new float[16];
	
	protected FloatBuffer positionBuffer;
	protected FloatBuffer textureCoordinatesBuffer;	
	
	protected int aPositionHandle;
	protected int aTextureHandle;
	protected int uAnimHandle;
	
	public Sprite2D (Texture texture, Rect textBounds,  Shader shader, Rect spriteBounds) {
		this.texture = texture;
		this.shader = shader;
		this.textBounds = textBounds;
		this.spriteBounds = spriteBounds;
		
		updateVertexCoordinats();				
		updateTextureCoordinats(textBounds);	
		
		bindShaderParams();
	}
	
	protected void bindShaderParams() {
		shader.linkProgram();
		aPositionHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_vertex");
        aTextureHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_texcoord");        
        uAnimHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_anim");
	}
	
	public Sprite2D (Texture texture, Shader shader, Rect spriteBounds) {
		this(texture,
				new Rect(new Point2D(0f, 0f), 1f, 1f), 
				shader,
				spriteBounds);
	}		
	
	private void updateVertexCoordinats() {
		fillVertexSquareArray(vertexPosition, spriteBounds);			
		positionBuffer = makeBuffer(vertexPosition);
	}
	
	protected void updateTextureCoordinats(Rect textureBounds) {
		fillTextureSquareArray(texturePosition, textureBounds);
		textureCoordinatesBuffer = makeBuffer(texturePosition);
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
	
	public static void fillVertexSquareArray(float[] array, Rect bounds) {
		array[0] = bounds.leftTop.x;
		array[1] = bounds.leftTop.y;
		
		array[2] = bounds.leftTop.x + bounds.w;
		array[3] = bounds.leftTop.y;
		
		array[4] = bounds.leftTop.x;
		array[5] = bounds.leftTop.y - bounds.h;
		
		array[6] = bounds.leftTop.x + bounds.w;
		array[7] = bounds.leftTop.y - bounds.h;
	}
	
	public static void fillTextureSquareArray(float[] array, Rect bounds) {
		array[0] = bounds.leftTop.x;
		array[1] = bounds.leftTop.y;
		
		array[2] = bounds.leftTop.x + bounds.w;
		array[3] = bounds.leftTop.y;
		
		array[4] = bounds.leftTop.x;
		array[5] = bounds.leftTop.y + bounds.h;
		
		array[6] = bounds.leftTop.x + bounds.w;
		array[7] = bounds.leftTop.y + bounds.h;	
		
	}
	
	public static FloatBuffer makeBuffer(float[] array) {
		FloatBuffer buffer = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		return (FloatBuffer) buffer.put(array).position(0);
	}

	public Rect getSpriteBounds() {
		return spriteBounds;
	}

	public void setSpriteBounds(Rect spriteBounds) {
		this.spriteBounds = spriteBounds;
		updateVertexCoordinats();
	}

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}		
	
	
}
