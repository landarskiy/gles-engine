package by.vsu.mf.rendering.jogl.gl2.api;

import javax.media.opengl.GL;

import by.vsu.mf.rendering.object.GeometryPrimitiveType;
import by.vsu.mf.rendering.opengl.api.DrawAPI;

public class GL2DrawAPI extends GL2API implements DrawAPI {

    @Override
    public void draw(final GeometryPrimitiveType type, final int first, final int count) {
        this.gl.glDrawArrays(GL2DrawAPI.getMode(type), first, count);
    }

    private static int getMode(final GeometryPrimitiveType type) {
        if (type == GeometryPrimitiveType.POINT) {
            return GL.GL_POINTS;
        } else if (type == GeometryPrimitiveType.TRIANGLE) {
            return GL.GL_TRIANGLES;
        } else if (type == GeometryPrimitiveType.TRIANGLE_STRIP) {
            return GL.GL_TRIANGLE_STRIP;
        } else {
            throw new IllegalArgumentException("Invalid primitive type!");
        }
    }

}
