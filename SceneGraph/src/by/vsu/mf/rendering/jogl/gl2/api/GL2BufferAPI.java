package by.vsu.mf.rendering.jogl.gl2.api;

import java.nio.Buffer;

import javax.media.opengl.GL;

import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.api.BufferAPI;

public class GL2BufferAPI extends GL2API implements BufferAPI {

    private int[] generatedHandles = new int[1];

    @Override
    public int createBuffer(final Buffer data, final VertexDataType type) {
        this.gl.glGenBuffers(1, this.generatedHandles, 0);
        this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.generatedHandles[0]);
        this.gl.glBufferData(GL.GL_ARRAY_BUFFER, data.capacity() * type.getSizeInBytes(), data, GL.GL_STATIC_DRAW);
        this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

        return this.generatedHandles[0];
    }

    @Override
    public void deleteBuffer(final int handle) {
        throw new UnsupportedOperationException("Buffer deletion is not implemented yet!");
    }

}
