package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import java.util.ArrayList;
import java.util.List;

import android.opengl.GLES20;
import by.vsu.mf.rendering.opengl.api.ShaderCompilerAPI;
import by.vsu.mf.rendering.opengl.api.ShaderProgramAPI;

public class GLES2ShaderCompilerAPI implements ShaderCompilerAPI {

    private List<Integer> stageHandles = new ArrayList<Integer>();

    @Override
    public void reset() {
        this.stageHandles.clear();
    }

    @Override
    public void compile(final ShaderType type, final String code) {
        final int glShaderType = GLES2ShaderCompilerAPI.getGLShaderType(type);
        int shaderHandle = GLES20.glCreateShader(glShaderType);
        GLES20.glShaderSource(shaderHandle, code);
        GLES20.glCompileShader(shaderHandle);

        final int[] compiled = new int[1];
        GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compiled, 0);

        final String typeName = (glShaderType == GLES20.GL_VERTEX_SHADER ? "Vertex" : "Fragment");
        if (compiled[0] != 0) {
            System.out.println(typeName + " shader compiled.");
        } else {
            final int[] logLength = new int[1];
            GLES20.glGetShaderiv(shaderHandle, GLES20.GL_INFO_LOG_LENGTH, logLength, 0);

            byte[] log = new byte[logLength[0]];
            GLES20.glGetShaderInfoLog(shaderHandle);

            throw new RuntimeException("Failed to compile " + typeName.toLowerCase() + " shader: " + new String(log));
        }

        this.stageHandles.add(shaderHandle);
    }

    @Override
    public ShaderProgramAPI link() {
        final int shaderProgramHandle = GLES20.glCreateProgram();
        for (final int stageHandle : this.stageHandles) {
            GLES20.glAttachShader(shaderProgramHandle, stageHandle);
        }

        GLES20.glLinkProgram(shaderProgramHandle);

        final GLES2ShaderProgramAPI programAPI = new GLES2ShaderProgramAPI(shaderProgramHandle);
        return programAPI;
    }

    private static int getGLShaderType(final ShaderType type) {
        if (type == ShaderType.FRAGMENT) {
            return GLES20.GL_FRAGMENT_SHADER;
        } else if (type == ShaderType.VERTEX) {
            return GLES20.GL_VERTEX_SHADER;
        } else {
            throw new IllegalArgumentException("Invalid shader type: " + type + "!");
        }
    }

}
