package by.vsu.mf.simplegles20.core.util;

public class Point3D extends Point2D{
	public float z;
	
	public Point3D() {
		super();
		this.z = 0;
	}
	
	public Point3D(float x, float y, float z) {
		super(x, y);
		this.z = z;
		this.floatArray = new float[3];
	}

	@Override
	public float[] toFloatArray() {
		floatArray[0] = x;
		floatArray[1] = y;
		floatArray[2] = z;
		return floatArray;
	}
	
	public float[] toGomogenFloatArray() {
		float[] gomogen = new float[4];
		gomogen[0] = x;
		gomogen[1] = y;
		gomogen[2] = z;
		gomogen[3] = 1;
		return gomogen;
	}
	
	@Override
	public void fromFloatArray(float[] point3D) {
		x = point3D[0];
		y = point3D[1];
		z = point3D[2];
	}
}
