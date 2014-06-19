package by.vsu.mf.rendering.object;

import by.vsu.mf.rendering.opengl.shader.ShaderState;

public interface Object3D extends Visual {

    Geometry getVertexData();

    ShaderState getShaderState();

    void setShaderState(final ShaderState state);

    // TODO: try to eliminate direct access to the float array.
    float[] getTransformationMatrix();

}
