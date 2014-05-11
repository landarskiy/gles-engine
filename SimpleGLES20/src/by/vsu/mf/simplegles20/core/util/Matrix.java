package by.vsu.mf.simplegles20.core.util;

@SuppressWarnings("deprecation")
public class Matrix extends android.opengl.Matrix{
	
	public static void crossV(float[] resv, float[] lv, float[] rv) {
		resv[0] = lv[1]*rv[2] - lv[2]*rv[1];
		resv[1] = lv[2]*rv[0] - lv[0]*rv[2];
		resv[2] = lv[0]*rv[1] - lv[1]*rv[0];
    }

    public static void normalize3v(float[] vn) {
    	double length = Math.sqrt(vn[0]*vn[0] + vn[1]*vn[1] + vn[2]*vn[2]);
    	vn[0] /= length;
    	vn[1] /= length;
    	vn[2] /= length;    	
    }
    
    public static void setRotateM(float[] rm, float[] forward, float[] up) {
    	setIdentityM(rm, 0);
    	
    	normalize3v(forward);
    	normalize3v(up);
    	
    	float[] side = new float[3];
    	float[] nUp = new float[3];
    	
    	crossV(side, up, forward);
    	normalize3v(side);
    	crossV(nUp, forward, side);
    	normalize3v(nUp);
    	    	
    	rm[0] = forward[0];
    	rm[1] = forward[1];
    	rm[2] = forward[2];
    	
    	rm[4] = side[0];
    	rm[5] = side[1];
    	rm[6] = side[2];
    	
    	rm[8] = nUp[0];
    	rm[9] = nUp[1];
    	rm[10] = nUp[2];
    }
}
