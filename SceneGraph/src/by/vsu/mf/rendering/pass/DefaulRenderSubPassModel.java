package by.vsu.mf.rendering.pass;

public class DefaulRenderSubPassModel implements RenderSubPassModel {

    @Override
    public void perform(final RendererContext context) {
        context.draw();
    }

}
