package by.vsu.mf.simplegles20.core.util;

public class Rect{
	public Point2D leftTop;
	public float w;
	public float h;
	
	public Rect(Rect rect) {
		this(rect.leftTop, rect.w, rect.h);
	}
	
	public Rect(Point2D leftTop, float w, float h) {
		this.leftTop = leftTop;
		this.w = w;
		this.h = h;
	}
		
}
