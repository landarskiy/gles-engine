package by.vsu.mf.simplegles20.core.object;

import android.graphics.RectF;
import android.opengl.Matrix;
import by.vsu.mf.simplegles20.core.util.Point3D;

public class Camera extends DirectedAbstractObject{
	
	private float[] projectMatrix = new float[16];
	private float[] projectViewMatrix = new float[16];
	
    public void setFrustumProjection(float screenWidth, float screenHeight, float near, float far){
    	float ratio = screenWidth / screenHeight;
    	setFrustumProjection(-ratio, ratio, -1f, 1f, near, far);
    }
    
    public void setFrustumProjection(RectF window, float near, float far){
        Matrix.frustumM(projectMatrix, 0, window.left, window.right, window.bottom, window.top, near, far);
    }
    
	public void setFrustumProjection(float left, float right, float bottom, float top, float near, float far){
        Matrix.frustumM(projectMatrix, 0, left, right, bottom, top, near, far);
    }
	
	public void setLookAt(Point3D cameraPosition, Point3D targetCameraPoint, Point3D upVector){
		this.position = cameraPosition;
		this.direction = new Point3D(targetCameraPoint.x - cameraPosition.x, 
				targetCameraPoint.y - cameraPosition.y, 
				targetCameraPoint.z - cameraPosition.z);
		this.directionUp = upVector;
		updateModelMatrix();
    }		
	
	public float[] calculateProjectViewMatrix() {
		Matrix.multiplyMM(projectViewMatrix, 0, projectMatrix, 0, modelMatrix, 0);
		return projectViewMatrix;
	}


	public void setPosition(Point3D position) {
		super.setPosition(position);
		updateModelMatrix();
	}

	public void setDirection(Point3D direction) {
		super.setDirection(direction);
		updateModelMatrix();
	}

	public void setDirectionUp(Point3D directionUp) {
		setDirectionUp(directionUp);
		updateModelMatrix();
	}
		
	public float[] getProjectMatrix() {
		return projectMatrix;
	}

	public float[] getViewMatrix() {
		return modelMatrix;
	}

	public float[] getProjectViewMatrix() {
		return projectViewMatrix;
	}
	
	
}
