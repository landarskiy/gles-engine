package by.vsu.mf.rendering.opengl;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import by.vsu.mf.rendering.jogl.gl2.api.GL2API;
import by.vsu.mf.rendering.object.Object3D;
import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.shader.ShaderInput;

// FIXME: remove this class
public class VBOOpenGLRenderer2 extends VBOOpenGLRenderer {

    private static final int BYTES_PER_FLOAT = 4;
    private static final int POSITION_DATA_SIZE = 3;
    private static final int COLOR_DATA_SIZE = 4;
    private static final int STRIDE = 7 * VBOOpenGLRenderer2.BYTES_PER_FLOAT;

    private static final int POSITION_OFFSET = 0;
    private static final int COLOR_OFFSET = 3;

    private GL2 gl;

    private int bufferHandle;

    private FloatBuffer vertexData;
    {
        final float[] triangleVerticesData = {
                // X, Y, Z,
                // R, G, B, A
                -0.5f, -0.25f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f,

                0.5f, -0.25f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                0.0f, 0.559016994f, 0.0f,
                0.0f, 1.0f, 0.0f, 1.0f
        };

        this.vertexData = ByteBuffer.allocateDirect(triangleVerticesData.length * VBOOpenGLRenderer2.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.vertexData.put(triangleVerticesData).position(0);
    }

    @Override
    public void render(final Object3D object) {
        if (this.gl == null) {
            try {
                Field field = GL2API.class.getDeclaredField("gl");
                field.setAccessible(true);
                this.gl = (GL2) field.get(this.bufferAPI);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            this.setUpBuffer();
        }
        final ShaderInput input = this.shaderManager.prepareShader(object.getShaderState());

        this.transformationAPI.apply(object.getTransformationMatrix());

        int handle = input.getVertexAttributeLocation("a_Position");
        this.vertexAttributeAPI.setData(this.bufferHandle);
        this.gl.glVertexAttribPointer(handle, VBOOpenGLRenderer2.POSITION_DATA_SIZE, GL.GL_FLOAT, false,
                VBOOpenGLRenderer2.STRIDE, 0);
//        this.vertexData.position(VBOOpenGLRenderer2.POSITION_OFFSET);
//        this.gl.glVertexAttribPointer(handle, VBOOpenGLRenderer2.POSITION_DATA_SIZE, GL.GL_FLOAT, false,
//                VBOOpenGLRenderer2.STRIDE, this.vertexData);
//
        this.gl.glEnableVertexAttribArray(handle);

        // Pass in the color information
        handle = input.getVertexAttributeLocation("a_Color");
        this.gl.glVertexAttribPointer(handle, VBOOpenGLRenderer2.COLOR_DATA_SIZE, GL.GL_FLOAT, false,
                VBOOpenGLRenderer2.STRIDE, 12);
//        this.vertexData.position(VBOOpenGLRenderer2.COLOR_OFFSET);
//        this.gl.glVertexAttribPointer(handle, VBOOpenGLRenderer2.COLOR_DATA_SIZE, GL.GL_FLOAT, false,
//                VBOOpenGLRenderer2.STRIDE, this.vertexData);
//
        this.gl.glEnableVertexAttribArray(handle);

        this.gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3);

        this.vertexAttributeAPI.reset();
    }

    private void setUpBuffer() {
        this.bufferHandle = this.bufferAPI.createBuffer(this.vertexData, VertexDataType.FLOAT);
    }

}
