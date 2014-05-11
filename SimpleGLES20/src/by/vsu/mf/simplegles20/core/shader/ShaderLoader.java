package by.vsu.mf.simplegles20.core.shader;

import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.simplegles20.core.util.ObjectPool;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import android.util.Log;

public class ShaderLoader {
	
	private static ShaderLoader instance;
	private Map<Integer, Shader> shaders;
	private ObjectPool<Shader> shaderPool;
	
	@SuppressLint("UseSparseArrays")
	private ShaderLoader () {
		this.shaders = new HashMap<Integer, Shader>();
		this.shaderPool = new ObjectPool<Shader>();
		for(int i = 0 ; i<shaderPool.getPoolSize() ; i++) {
			shaderPool.addLast(new Shader());
		}
	}
	
	public static ShaderLoader getInstance() {
		if(instance == null) {
			synchronized (ShaderLoader.class) {
				if(instance == null) {
					instance = new ShaderLoader();
				}
			}
		}
		return instance;
	}
	
	public Shader load(String vertexShader, String fragmentShader) {
		int hash = generateHash(vertexShader.hashCode(), 
				fragmentShader.hashCode());
    	
		Shader shader = shaders.get(hash);
    	if(shader != null) {
    		return shader;
    	}

    	if(shaderPool.isEmpty()) {
    		shader = new Shader();
    	} else {
    		shader = shaderPool.removeFirst();
    	}
    	                
        int vertexShaderId = loadShader(GLES20.GL_VERTEX_SHADER, vertexShader);
        int fragmentShaderId = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);
        int progId = GLES20.glCreateProgram();
        GLES20.glAttachShader(progId, vertexShaderId);
        GLES20.glAttachShader(progId, fragmentShaderId);                                
        GLES20.glReleaseShaderCompiler();
        
        shader.setHash(hash);
        shader.setProgId(progId);
        shaders.put(hash, shader);
    	return shader;
	}	
	
	private int loadShader(int shaderType, String shaderSrc) {
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
            GLES20.glShaderSource(shader, shaderSrc);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                Log.e("Error", "Could not compile shader name:" + shaderSrc + " =>" + shaderType + ":");
                Log.e("Error", GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

	public synchronized void clear() {
		for(Integer hash : shaders.keySet()) {
			shaders.remove(hash).unlinkProgram();
		}
	}
	
	public void remove(Shader shader) {
		remove(shader.getHash());
	}
	
	public void remove(int hash) {
		Shader shader = shaders.remove(hash);
		if(shader == null) {
			return;
		}
		shaderPool.addLast(shader);
	}
		
	private int generateHash(int a, int b) {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}
}
