package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.ViewSpace;

public abstract class ViewSpaceUniformHolder extends BaseUniformHolder {

    protected ViewSpace view;

    public void setView(final ViewSpace view) {
        this.view = view;
    }

}
