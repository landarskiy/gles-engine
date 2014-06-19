package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import java.nio.Buffer;

import android.opengl.GLES20;
import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.api.BufferAPI;

public class GLES2BufferAPI implements BufferAPI {

    private int[] generatedHandles = new int[1];

    @Override
    public int createBuffer(final Buffer data, final VertexDataType type) {
        GLES20.glGenBuffers(1, this.generatedHandles, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.generatedHandles[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, data.capacity() * type.getSizeInBytes(), data, GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        return this.generatedHandles[0];
    }

    @Override
    public void deleteBuffer(final int handle) {
        throw new UnsupportedOperationException("Buffer deletion is not implemented yet!");
    }

}
