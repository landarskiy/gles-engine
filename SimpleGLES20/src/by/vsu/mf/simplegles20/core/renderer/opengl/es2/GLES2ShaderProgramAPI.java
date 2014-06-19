package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import java.util.HashMap;
import java.util.Map;

import android.opengl.GLES20;
import by.vsu.mf.rendering.opengl.api.ShaderProgramAPI;

public class GLES2ShaderProgramAPI implements ShaderProgramAPI {

    private Map<String, Integer> uniformLocations = new HashMap<String, Integer>();
    private Map<String, Integer> vertexAttributeLocations = new HashMap<String, Integer>();

    private int handle;

    public GLES2ShaderProgramAPI(final int handle) {
        this.handle = handle;
    }

    public void setProgramHandle(final int programHandle) {
        this.handle = programHandle;
    }

    @Override
    public int getVertexAttributeLocation(final String name) {
        Integer location = this.vertexAttributeLocations.get(name);
        if (location == null) {
            location = GLES20.glGetAttribLocation(this.handle, name);
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
            location = GLES20.glGetUniformLocation(this.handle, name);
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
        GLES20.glUseProgram(this.handle);
    }

    @Override
    public int getHandle() {
        return this.handle;
    }

    @Override
    public void setUniform(final String name, final int value) {
        final int location = this.getUniformLocation(name);
        GLES20.glUniform1i(location, value);
    }

    @Override
    public void setUniform(final String name, final float value) {
        final int location = this.getUniformLocation(name);
        GLES20.glUniform1f(location, value);
    }

    @Override
    public void setUniform(final String name, final CompoundUniformType type, final int[] value) {
        final int location = this.getUniformLocation(name);
        switch (type) {
            case VECTOR3:
                GLES20.glUniform3iv(location, 1, value, 0);
                break;
            case VECTOR4:
                GLES20.glUniform4iv(location, 1, value, 0);
                break;
            case MATRIX3:
                GLES20.glUniform3iv(location, 1, value, 0);
                break;
            case MATRIX4:
                GLES20.glUniform4iv(location, 1, value, 0);
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
                GLES20.glUniform3fv(location, 1, value, 0);
                break;
            case VECTOR4:
                GLES20.glUniform4fv(location, 1, value, 0);
                break;
            case MATRIX3:
                GLES20.glUniformMatrix3fv(location, 1, false, value, 0);
                break;
            case MATRIX4:
                GLES20.glUniformMatrix4fv(location, 1, false, value, 0);
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
            return (GLES20.glGetUniformLocation(this.handle, name) != -1);
        }
    }

    @Override
    // FIXME: cache attribute location if it's available and also cache boolean flag indicating that the attribute doesn't exists if it's so.
    public boolean hasVertexAttribute(final String name) {
        if (this.vertexAttributeLocations.containsKey(name)) {
            return true;
        } else {
            return (GLES20.glGetAttribLocation(this.handle, name) != -1);
        }
    }

}
