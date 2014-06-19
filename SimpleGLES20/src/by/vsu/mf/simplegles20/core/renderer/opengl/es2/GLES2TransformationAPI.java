package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import by.vsu.mf.rendering.opengl.api.TransformationAPI;
import by.vsu.mf.rendering.opengl.shader.ShaderInput;
import by.vsu.mf.rendering.opengl.shader.ShaderInput.CompoundUniformType;
import by.vsu.mf.rendering.opengl.shader.ShaderManager;

public class GLES2TransformationAPI implements TransformationAPI {

    private static final String UNIFORM_NAME_MVP_MATRIX = "u_MVPMatrix";

    private ShaderManager shaderManager;

    public void setShaderManager(final ShaderManager manager) {
        this.shaderManager = manager;
    }

    @Override
    public void apply(final float[] matrix) {
        final ShaderInput input = this.shaderManager.getCurrentShaderInput();
        if (input.hasUniform(GLES2TransformationAPI.UNIFORM_NAME_MVP_MATRIX)) {
            input.setUniform(GLES2TransformationAPI.UNIFORM_NAME_MVP_MATRIX, CompoundUniformType.MATRIX4, matrix);
        }
    }

}
