package by.vsu.mf.simplegles20.core.object;

import android.opengl.Matrix;
import by.vsu.mf.simplegles20.core.util.Point3D;

public class DirectedAbstractObject {
	protected Point3D position;
	protected Point3D direction;
	protected Point3D directionUp;

	protected float[] modelMatrix = new float[16];
	protected float[] transformationMatrix = new float[16];
	
	public Point3D getPosition() {
		return position;
	}

	public void setPosition(Point3D position) {
		this.position = position;
	}

	public Point3D getDirection() {
		return direction;
	}

	public void setDirection(Point3D direction) {
		this.direction = direction;
	}

	public Point3D getDirectionUp() {
		return directionUp;
	}

	public void setDirectionUp(Point3D directionUp) {
		this.directionUp = directionUp;
	}

	public void updateModelMatrix() {
		Matrix.setLookAtM(modelMatrix, 0,
        		position.x, position.y, position.z,
        		position.x + direction.x, position.y + direction.y, position.z + direction.z,
        		directionUp.x, directionUp.y, directionUp.z);
	}

	public void setModelMatrix(float[] modelMatrix) {
		this.modelMatrix = modelMatrix;
	}
	
	public float[] getModelMatrix() {
		return modelMatrix;
	}

	public void rotate(float a, float x, float y, float z) {
		Matrix.setIdentityM(transformationMatrix, 0);
		Matrix.rotateM(transformationMatrix, 0, a, x, y, z);
		float[] result = new float[4];
		Matrix.multiplyMV(result, 0, transformationMatrix, 0, position.toGomogenFloatArray(), 0);
		position.fromFloatArray(result);
		Matrix.multiplyMV(result, 0, transformationMatrix, 0, direction.toGomogenFloatArray(), 0);
		direction.fromFloatArray(result);
		Matrix.multiplyMV(result, 0, transformationMatrix, 0, directionUp.toGomogenFloatArray(), 0);
		directionUp.fromFloatArray(result);
	}
}
