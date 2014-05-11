package by.vsu.mf.simplegles20.core.util;

public class Point2D {
	protected float[] floatArray;
	
	public float x;
	public float y;

	public Point2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point2D(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		this.floatArray = new float[2];
	}

	public float[] toFloatArray() {
		floatArray[0] = x;
		floatArray[1] = y;
		return floatArray;
	}
	
	public void fromFloatArray(float[] point2D) {
		x = point2D[0];
		y = point2D[1];
	}
}
