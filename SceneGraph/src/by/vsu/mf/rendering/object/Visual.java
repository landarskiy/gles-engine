package by.vsu.mf.rendering.object;

import java.util.Collection;

import by.vsu.mf.rendering.opengl.renderingAspect.RenderingAspect;

public interface Visual {

    Collection<? extends RenderingAspect> getRenderingAspects();

}
