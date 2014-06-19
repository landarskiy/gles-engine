package by.vsu.mf.rendering.jogl.gl2.api;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2ES2;

import by.vsu.mf.rendering.opengl.api.ShaderCompilerAPI;
import by.vsu.mf.rendering.opengl.api.ShaderProgramAPI;

public class GL2ShaderCompilerAPI extends GL2API implements ShaderCompilerAPI {

    private List<Integer> stageHandles = new ArrayList<>();

    @Override
    public void reset() {
        this.stageHandles.clear();
    }

    @Override
    public void compile(final ShaderType type, final String code) {
        final int glShaderType = GL2ShaderCompilerAPI.getGLShaderType(type);
        int shaderHandle = this.gl.glCreateShader(glShaderType);
        this.gl.glShaderSource(shaderHandle, 1, new String[] {code}, null, 0);
        this.gl.glCompileShader(shaderHandle);

        final int[] compiled = new int[1];
        this.gl.glGetShaderiv(shaderHandle, GL2ES2.GL_COMPILE_STATUS, compiled, 0);

        final String typeName = (glShaderType == GL2ES2.GL_VERTEX_SHADER ? "Vertex" : "Fragment");
        if (compiled[0] != 0) {
            System.out.println(typeName + " shader compiled.");
        } else {
            final int[] logLength = new int[1];
            this.gl.glGetShaderiv(shaderHandle, GL2ES2.GL_INFO_LOG_LENGTH, logLength, 0);

            byte[] log = new byte[logLength[0]];
            this.gl.glGetShaderInfoLog(shaderHandle, logLength[0], (int[]) null, 0, log, 0);

            throw new RuntimeException("Failed to compile " + typeName.toLowerCase() + " shader: " + new String(log));
        }

        this.stageHandles.add(shaderHandle);
    }

    @Override
    public ShaderProgramAPI link() {
        final int shaderProgramHandle = this.gl.glCreateProgram();
        for (final int stageHandle : this.stageHandles) {
            this.gl.glAttachShader(shaderProgramHandle, stageHandle);
        }

        this.gl.glLinkProgram(shaderProgramHandle);

        final GL2ShaderProgramAPI programAPI = new GL2ShaderProgramAPI(shaderProgramHandle);
        programAPI.setGL(this.gl);
        return programAPI;
    }

    private static int getGLShaderType(final ShaderType type) {
        if (type == ShaderType.FRAGMENT) {
            return GL2ES2.GL_FRAGMENT_SHADER;
        } else if (type == ShaderType.VERTEX) {
            return GL2ES2.GL_VERTEX_SHADER;
        } else {
            throw new IllegalArgumentException("Invalid shader type: " + type + "!");
        }
    }

}
