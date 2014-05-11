package by.vsu.mf.simplegles20.core.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;

public class Simple3DObject extends DirectedAbstractObject implements DrawableObject{
	protected Shader shader;
	
	protected int vertexCount;
	protected float[] vertexPosition;
	protected float[] textPos;
	
	protected FloatBuffer positionBuffer;
	protected ShortBuffer positionBufferFace;
	
	protected FloatBuffer textureBuffer;
	protected ShortBuffer textureBufferFace;
	
	protected int aPositionHandle;
	protected int uMVPMtxHandle;
	protected int uLightHandle;
	protected int aTextureHandle;
	
	protected Texture tx;
	
    private final float[] MVPMatrix = new float[16];
    
    private float[] light;
	
	public Simple3DObject (Shader shader, float[] vertexPosition, int vertexCount, float[] textPos, Texture tx) {
		this.shader = shader;
		this.vertexPosition = vertexPosition;
		this.vertexCount = vertexCount;
		this.textPos = textPos;
		this.tx = tx;
		
		updateVertexCoordinats();					
		textureBuffer = makeBuffer(textPos);
		bindShaderParams();
	}
	
	protected void bindShaderParams() {
		shader.linkProgram();
		aPositionHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_vertex");
		uMVPMtxHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_MVPMatrix");
		uLightHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_light");
		aTextureHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_texcoord");
		
		Matrix.setIdentityM(modelMatrix, 0);
	}			
	
	private void updateVertexCoordinats() {		
		positionBuffer = makeBuffer(vertexPosition);
	}
	
	public void draw(Camera camera){
        GLES20.glUseProgram(shader.getProgId());
        tx.use(0);
                        
        GLES20.glEnableVertexAttribArray(aPositionHandle);
        GLES20.glEnableVertexAttribArray(aTextureHandle);
        
        positionBuffer.position(0);
        GLES20.glVertexAttribPointer(aPositionHandle, 3, GLES20.GL_FLOAT, false, 4 * 3, positionBuffer);
        
        textureBuffer.position(0);
        GLES20.glVertexAttribPointer(aTextureHandle, 2, GLES20.GL_FLOAT, false, 4 * 2, textureBuffer);

        Matrix.multiplyMM(MVPMatrix, 0, camera.getProjectViewMatrix(), 0, modelMatrix, 0);
        GLES20.glUniformMatrix4fv(uMVPMtxHandle, 1, false, MVPMatrix, 0);
        GLES20.glUniform3fv(uLightHandle, 1, light, 0);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        
        GLES20.glDisableVertexAttribArray(aPositionHandle);
        GLES20.glDisableVertexAttribArray(aTextureHandle);
    }				
	
	public static FloatBuffer makeBuffer(float[] array) {
		FloatBuffer buffer = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		return (FloatBuffer) buffer.put(array).position(0);
	}

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}		
	
    public void setLight(float[] light) {
    	this.light = light;
    }
    

}
