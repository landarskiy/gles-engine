package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import android.opengl.GLES20;
import by.vsu.mf.rendering.object.GeometryPrimitiveType;
import by.vsu.mf.rendering.opengl.api.DrawAPI;

public class GLES2DrawAPI implements DrawAPI {

    @Override
    public void draw(final GeometryPrimitiveType type, final int first, final int count) {
        GLES20.glDrawArrays(GLES2DrawAPI.getMode(type), first, count);
    }

    private static int getMode(final GeometryPrimitiveType type) {
        if (type == GeometryPrimitiveType.POINT) {
            return GLES20.GL_POINTS;
        } else if (type == GeometryPrimitiveType.TRIANGLE) {
            return GLES20.GL_TRIANGLES;
        } else if (type == GeometryPrimitiveType.TRIANGLE_STRIP) {
            return GLES20.GL_TRIANGLE_STRIP;
        } else {
            throw new IllegalArgumentException("Invalid primitive type!");
        }
    }

}
