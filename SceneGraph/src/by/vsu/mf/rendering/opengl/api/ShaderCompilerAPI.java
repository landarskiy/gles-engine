package by.vsu.mf.rendering.opengl.api;

public interface ShaderCompilerAPI {

    void reset();

    void compile(final ShaderType type, final String code);

    ShaderProgramAPI link();

    public enum ShaderType {
        VERTEX, FRAGMENT
    }

}
