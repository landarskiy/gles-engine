package by.vsu.mf.rendering.pass;

import by.vsu.mf.rendering.opengl.renderingAspect.RenderingAspect;
import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public interface RendererContext {

    void draw();

    void applyAspect(final RenderingAspect aspect);

    ShaderInput getShaderInput();

}
