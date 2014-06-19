package by.vsu.mf.rendering.jogl.gl2.api;

import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.rendering.opengl.api.ShaderProgramAPI;

public class GL2ShaderProgramAPI extends GL2API implements ShaderProgramAPI {

    private Map<String, Integer> uniformLocations = new HashMap<>();
    private Map<String, Integer> vertexAttributeLocations = new HashMap<>();

    private int handle;

    public GL2ShaderProgramAPI(final int handle) {
        this.handle = handle;
    }

    public void setProgramHandle(final int programHandle) {
        this.handle = programHandle;
    }

    @Override
    public int getVertexAttributeLocation(final String name) {
        Integer location = this.vertexAttributeLocations.get(name);
        if (location == null) {
            location = this.gl.glGetAttribLocation(this.handle, name);
            this.vertexAttributeLocations.put(name, location);

            if (location == -1) {
                throw new IllegalArgumentException("Attribute \"" + name
                        + "\" is not available in the current shader!");
            }
        }

        return location;
    }

    // FIXME: change access to private
    @Override
    public int getUniformLocation(final String name) {
        Integer location = this.uniformLocations.get(name);
        if (location == null) {
            location = this.gl.glGetUniformLocation(this.handle, name);
            this.uniformLocations.put(name, location);

            if (location == -1) {
                throw new IllegalArgumentException("Uniform \"" + name
                        + "\" is not available in the current shader!");
            }
        }

        return location;
    }

    @Override
    public void activate() {
        this.gl.glUseProgram(this.handle);
    }

    @Override
    public int getHandle() {
        return this.handle;
    }

    @Override
    public void setUniform(final String name, final int value) {
        final int location = this.getUniformLocation(name);
        this.gl.glUniform1i(location, value);
    }

    @Override
    public void setUniform(final String name, final float value) {
        final int location = this.getUniformLocation(name);
        this.gl.glUniform1f(location, value);
    }

    @Override
    public void setUniform(final String name, final CompoundUniformType type, final int[] value) {
        final int location = this.getUniformLocation(name);
        switch (type) {
            case VECTOR3:
                this.gl.glUniform3iv(location, 1, value, 0);
                break;
            case VECTOR4:
                this.gl.glUniform4iv(location, 1, value, 0);
                break;
            case MATRIX3:
                this.gl.glUniform3iv(location, 1, value, 0);
                break;
            case MATRIX4:
                this.gl.glUniform4iv(location, 1, value, 0);
                break;
            default:
                throw new IllegalArgumentException("Invalid uniform type: " + type);
        }
    }

    @Override
    public void setUniform(final String name, final CompoundUniformType type, final float[] value) {
        final int location = this.getUniformLocation(name);
        switch (type) {
            case VECTOR3:
                this.gl.glUniform3fv(location, 1, value, 0);
                break;
            case VECTOR4:
                this.gl.glUniform4fv(location, 1, value, 0);
                break;
            case MATRIX3:
                this.gl.glUniformMatrix3fv(location, 1, false, value, 0);
                break;
            case MATRIX4:
                this.gl.glUniformMatrix4fv(location, 1, false, value, 0);
                break;
            default:
                throw new IllegalArgumentException("Invalid uniform type: " + type);
        }
    }

    @Override
    // FIXME: cache uniform location if it's available and also cache boolean flag indicating that the uniform doesn't exists if it's so.
    public boolean hasUniform(final String name) {
        if (this.uniformLocations.containsKey(name)) {
            return true;
        } else {
            return (this.gl.glGetUniformLocation(this.handle, name) != -1);
        }
    }

    @Override
    // FIXME: cache attribute location if it's available and also cache boolean flag indicating that the attribute doesn't exists if it's so.
    public boolean hasVertexAttribute(final String name) {
        if (this.vertexAttributeLocations.containsKey(name)) {
            return true;
        } else {
            return (this.gl.glGetAttribLocation(this.handle, name) != -1);
        }
    }

}
