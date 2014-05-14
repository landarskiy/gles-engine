package by.vsu.mf.simplegles20.core.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.texture.Texture;

public class SimpleNormals3DObject extends DirectedAbstractObject implements DrawableObject{
	protected Shader shader;
	
	protected int vertexCount;
	protected float[] vertexPosition;
	protected float[] textPos;
	protected float[] normalPos;
	
	protected FloatBuffer positionBuffer;
	protected ShortBuffer positionBufferFace;
	
	protected FloatBuffer normalBuffer;
	protected ShortBuffer normalBufferFace;
	
	protected FloatBuffer textureBuffer;
	protected ShortBuffer textureBufferFace;
	
	protected int aPositionHandle;
	protected int uMVPMtxHandle;
	protected int uLightHandle;
	protected int aTextureHandle;
	protected int aNormalHandle;
	protected int uMViewMtxHandle;
	protected int uEyePosHandle;
	protected int uTexture1Handle;
	
	protected Texture tx;
	
    private final float[] MVPMatrix = new float[16];
    
    private float[] light;
	
	public SimpleNormals3DObject (Shader shader, float[] vertexPosition, int vertexCount, 
			float[] textPos, Texture tx, float[] normalPos) {
		this.shader = shader;
		this.vertexPosition = vertexPosition;
		this.vertexCount = vertexCount;
		this.textPos = textPos;
		this.tx = tx;
		this.normalPos = normalPos;
		
		updateBuffers();							
		bindShaderParams();
	}
	
	protected void bindShaderParams() {
		shader.linkProgram();
		aPositionHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_vertex");
		aNormalHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_normal");
		aTextureHandle = GLES20.glGetAttribLocation(shader.getProgId(), "a_texcoord");
		uMVPMtxHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_MVPMatrix");
		uMViewMtxHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_MVMatrix");
		uLightHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_light");
		uEyePosHandle = GLES20.glGetUniformLocation(shader.getProgId(), "u_EyePos");
		uTexture1Handle = GLES20.glGetUniformLocation(shader.getProgId(), "s_texture1");
		
		Matrix.setIdentityM(modelMatrix, 0);
	}			
	
	private void updateBuffers() {		
		positionBuffer = makeBuffer(vertexPosition);
		textureBuffer = makeBuffer(textPos);
		normalBuffer = makeBuffer(normalPos);
	}
	
	public void draw(Camera camera){
        GLES20.glUseProgram(shader.getProgId());
        tx.use(0);
                        
        GLES20.glEnableVertexAttribArray(aPositionHandle);
        GLES20.glEnableVertexAttribArray(aNormalHandle);
        GLES20.glEnableVertexAttribArray(aTextureHandle);
        
        positionBuffer.position(0);
        GLES20.glVertexAttribPointer(aPositionHandle, 3, GLES20.GL_FLOAT, false, 4 * 3, positionBuffer);
        
        textureBuffer.position(0);
        GLES20.glVertexAttribPointer(aTextureHandle, 2, GLES20.GL_FLOAT, false, 4 * 2, textureBuffer);
        
        normalBuffer.position(0);
        GLES20.glVertexAttribPointer(aNormalHandle, 3, GLES20.GL_FLOAT, false, 4 * 3, normalBuffer);
        
        /*
         * Matrix.multiplyMM(MVPMatrix, 0, Camera.getProjViewMatrix(), 0, ModelMatrix, 0);

        GLES20.glUniform1i(uTexture1Handle,0);
        GLES20.glUniformMatrix4fv(uMViewMtxHandle, 1, false,ModelMatrix, 0);
        GLES20.glUniformMatrix4fv(uMVPMtxHandle, 1, false,MVPMatrix, 0);
        GLES20.glUniform3fv(uLightHandle, 1,Light, 0);
        GLES20.glUniform3fv(uEyePosHandle, 1,Camera.getEyePos(), 0);
         */
        
        Matrix.multiplyMM(MVPMatrix, 0, camera.getProjectViewMatrix(), 0, modelMatrix, 0);
        GLES20.glUniform1i(uTexture1Handle,0);
        GLES20.glUniformMatrix4fv(uMVPMtxHandle, 1, false, MVPMatrix, 0);
        GLES20.glUniformMatrix4fv(uMViewMtxHandle, 1, false, modelMatrix, 0);
        GLES20.glUniform3fv(uLightHandle, 1, light, 0);
        GLES20.glUniform3fv(uEyePosHandle, 1, camera.getPosition().toFloatArray(), 0);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        
        GLES20.glDisableVertexAttribArray(aPositionHandle);
        GLES20.glDisableVertexAttribArray(aTextureHandle);
        GLES20.glDisableVertexAttribArray(aNormalHandle);
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
