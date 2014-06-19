package by.vsu.mf.rendering.opengl;

import by.vsu.mf.rendering.Renderer;
import by.vsu.mf.rendering.object.Geometry;
import by.vsu.mf.rendering.object.GeometryPrimitiveType;
import by.vsu.mf.rendering.object.Object3D;
import by.vsu.mf.rendering.opengl.api.DrawAPI;
import by.vsu.mf.rendering.opengl.api.TransformationAPI;
import by.vsu.mf.rendering.opengl.api.VertexAttributeAPI;
import by.vsu.mf.rendering.opengl.renderingAspect.RenderingAspect;
import by.vsu.mf.rendering.opengl.shader.ShaderInput;
import by.vsu.mf.rendering.opengl.shader.ShaderManager;
import by.vsu.mf.rendering.pass.DefaulRenderSubPassModel;
import by.vsu.mf.rendering.pass.RenderSubPassModel;
import by.vsu.mf.rendering.pass.RendererContext;

public abstract class OpenGLRenderer implements Renderer<Object3D> {

    protected ShaderManager shaderManager;

    protected DrawAPI drawAPI;
    protected VertexAttributeAPI vertexAttributeAPI;
    protected TransformationAPI transformationAPI;

    protected RenderingAspectApplicator<RenderingAspect> renderingAspectApplicator;

    private RenderSubPassModel passModel = new DefaulRenderSubPassModel();
    private OpenGLRendererContext context = new OpenGLRendererContext();

    public void setShaderManager(final ShaderManager manager) {
        this.shaderManager = manager;
    }

    public void setDrawAPI(final DrawAPI api) {
        this.drawAPI = api;
    }

    public void setVertexAttributeAPI(final VertexAttributeAPI api) {
        this.vertexAttributeAPI = api;
    }

    public void setTransformationAPI(final TransformationAPI api) {
        this.transformationAPI = api;
    }

    public void setRenderingAspectApplicator(final RenderingAspectApplicator<RenderingAspect> applicator) {
        this.renderingAspectApplicator = applicator;
    }

    public void setRenderSubPassModel(final RenderSubPassModel model) {
        this.passModel = model;
    }

    @Override
    public void render(final Object3D entity) {
        final ShaderInput input = this.shaderManager.prepareShader(entity.getShaderState());

        this.transformationAPI.apply(entity.getTransformationMatrix());

        for (final RenderingAspect aspect : entity.getRenderingAspects()) {
            this.renderingAspectApplicator.apply(aspect);
        }

        final Geometry geometry = entity.getVertexData();
        this.applyGeometry(geometry, input);

        if (geometry.usesIndexes()) {
            throw new UnsupportedOperationException("Indexes are not supported!");
        } else {
            this.context.setCount(geometry.getVertexCount());
            this.context.setPrimitiveType(geometry.getPrimitiveType());
            this.context.setShaderInput(input);

            this.passModel.perform(this.context);

            this.context.reset();
        }
    }

    protected abstract void applyGeometry(final Geometry geometry, final ShaderInput input);

    private class OpenGLRendererContext implements RendererContext {

        private int offset;
        private int count;
        private GeometryPrimitiveType primitiveType;
        private ShaderInput shaderInput;

        @Override
        public void draw() {
            OpenGLRenderer.this.drawAPI.draw(this.primitiveType, this.offset, this.count);
        }

        @Override
        public void applyAspect(final RenderingAspect aspect) {
            OpenGLRenderer.this.renderingAspectApplicator.apply(aspect);
        }

        @Override
        public ShaderInput getShaderInput() {
            return this.shaderInput;
        }

        public void setCount(final int count) {
            this.count = count;
        }

        public void setPrimitiveType(final GeometryPrimitiveType primitiveType) {
            this.primitiveType = primitiveType;
        }

        public void setShaderInput(final ShaderInput input) {
            this.shaderInput = input;
        }

        public void reset() {
            this.offset = 0;
            this.count = 0;
            this.primitiveType = null;
            this.shaderInput = null;
        }

    }

}
