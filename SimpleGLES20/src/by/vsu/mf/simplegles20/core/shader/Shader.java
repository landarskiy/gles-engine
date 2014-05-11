package by.vsu.mf.simplegles20.core.shader;

import by.vsu.mf.simplegles20.core.util.StoredObject;
import android.opengl.GLES20;

public class Shader extends StoredObject{
	private int progId;

	public Shader() {
		this.hash = 0;
		this.progId = 0;
	}

	public void linkProgram() {
		GLES20.glLinkProgram(progId);
	}		
	
	public void unlinkProgram() {
		GLES20.glDeleteProgram(progId);
	}	

	public int getProgId() {
		return progId;
	}

	protected void setProgId(int progId) {
		this.progId = progId;
	}

}
