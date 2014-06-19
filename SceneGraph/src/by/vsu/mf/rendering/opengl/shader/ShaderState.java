package by.vsu.mf.rendering.opengl.shader;

import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.rendering.opengl.shader.uniform.UniformHolder;

// TODO: improve the shader manager to take uniform holder set during shader code lookup.
public class ShaderState {

    private String vertexShaderName;
    private String fragmentShaderName;

    private Map<String, UniformHolder> uniformHolders = new HashMap<>();

    public void setVertexShader(final String name) {
        this.vertexShaderName = name;
    }

    public void setFragmentShader(final String name) {
        this.fragmentShaderName = name;
    }

    public String getVertexShaderName() {
        return this.vertexShaderName;
    }

    public String getFragmentShaderName() {
        return this.fragmentShaderName;
    }

    public void setUniform(final UniformHolder holder) {
        this.uniformHolders.put(holder.getName(), holder);
    }

    public void applyUniforms(final ShaderInput input) {
        for (final UniformHolder holder : this.uniformHolders.values()) {
            holder.apply(input);
        }
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = (result * 31) + (this.vertexShaderName == null ? 0 : this.vertexShaderName.hashCode());
        result = (result * 31) + (this.fragmentShaderName == null ? 0 : this.fragmentShaderName.hashCode());

        return result;
    }

}
