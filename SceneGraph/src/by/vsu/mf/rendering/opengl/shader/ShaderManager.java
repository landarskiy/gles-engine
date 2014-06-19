package by.vsu.mf.rendering.opengl.shader;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.shaderModel.ShaderFactory;
import org.bananaLaba.shaderModel.ShaderFactorySession;
import org.bananaLaba.shaderModel.SimpleParameterMap;

import by.vsu.mf.rendering.opengl.api.ShaderCompilerAPI;
import by.vsu.mf.rendering.opengl.api.ShaderCompilerAPI.ShaderType;
import by.vsu.mf.rendering.opengl.api.ShaderProgramAPI;

// FIXME: allowed tag attribute control absence is damned annoying!.
// FIXME: don't forget to delete unnecessary programs using glDeleteProgram.
// FIXME: imply shader state creation through the manage somehow.
// FIXME: don't forget to mark all the shader states associated with this manager as dirty when OpenGL context is
// changed.
public class ShaderManager {

    private ShaderCompilerAPI compilerAPI;
    private ShaderFactory factory;
    private int currentProgramHandle = -1;
    private ShaderProgramAPI currentInput;

    private Map<Integer, ShaderProgramAPI> shaderControls = new HashMap<>();

    public void setFactory(final ShaderFactory factory) {
        this.factory = factory;
    }

    public void setCompilerAPI(final ShaderCompilerAPI api) {
        this.compilerAPI = api;
    }

    private ShaderProgramAPI compile(final String vertexShader, final String fragmentShader) {
        this.compilerAPI.compile(ShaderType.FRAGMENT, fragmentShader);
        this.compilerAPI.compile(ShaderType.VERTEX, vertexShader);

        return this.compilerAPI.link();
    }

    /**
     * Applies the given shader state. Should be called after all updates of the state, but before any draw calls.
     * @param state the shader state
     */
    /*
     * If a program associated with the state is already used (i.e. this program is the last activated using
     * glUseProgram) the glUseProgramCall will not be performed/
     */
    public ShaderInput prepareShader(final ShaderState state) {
        final int shaderHashCode = state.hashCode();
        ShaderProgramAPI input = this.shaderControls.get(shaderHashCode);
        int programHandle;
        if (input == null) {
            final ShaderFactorySession factorySession = this.factory.getSession(new SimpleParameterMap());
            final String vertexShader = factorySession.getShaderCode(state.getVertexShaderName());
            final String fragmentShader = factorySession.getShaderCode(state.getFragmentShaderName());
            input = this.compile(vertexShader, fragmentShader);
            this.compilerAPI.reset();

            programHandle = input.getHandle();

            this.shaderControls.put(shaderHashCode, input);
        } else {
            programHandle = input.getHandle();
        }

        if (this.currentProgramHandle != programHandle) {
            this.currentProgramHandle = programHandle;
            this.currentInput = input;
            this.currentInput.activate();
        }

        state.applyUniforms(input);

        return input;
    }

    public ShaderInput getCurrentShaderInput() {
        return this.currentInput;
    }

}
