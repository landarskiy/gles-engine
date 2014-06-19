package org.bananaLaba.sceneGraph.test;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import org.bananaLaba.sceneGraph.test.helper.MatrixUtils;
import org.bananaLaba.sceneGraph.test.helper.RenderTargetManager;

import by.vsu.mf.rendering.SimpleViewSpace;
import by.vsu.mf.rendering.jogl.gl2.api.GL2ContextDistributor;
import by.vsu.mf.rendering.object.GeometryLoader;
import by.vsu.mf.rendering.object.GeometryUtils;
import by.vsu.mf.rendering.object.Object3D;
import by.vsu.mf.rendering.opengl.OpenGLRenderer;
import by.vsu.mf.rendering.opengl.shader.ShaderState;
import by.vsu.mf.rendering.opengl.shader.uniform.UniformUtils;
import by.vsu.mf.rendering.sfx.GaussBlurModel;

import com.jogamp.opengl.math.FloatUtil;

public class GraphicsEngine implements GLEventListener {

    private static final String FILTER_TARGET_1 = "filter1";
    private static final String FILTER_TARGET_2 = "filter2";

    private static final int BLOOM_MAP_WIDTH = 256;
    private static final int BLOOM_MAP_HEIGHT = 256;

    private ShaderState gaussState = new ShaderState();
    {
        this.gaussState.setFragmentShader("gaussFragmentShader");
        this.gaussState.setVertexShader("gaussVertexShader");
    }

    private GaussBlurModel blurPassModel;

    private float[] modelViewMatrix = new float[16];

    private Object3D mainObject = new SimpleObject3D(new GeometryLoader().load("files/test/sample_ship_finish.obj"));
    {
        final ShaderState shaderState = this.mainObject.getShaderState();
        shaderState.setVertexShader("testVertexShader");
        shaderState.setFragmentShader("testFragmentShader");
        shaderState.setUniform(UniformUtils.getVector3Holder("u_LightPos",
               new float[] {0.0f, 0.0f, -0.5f}));
    }

    private Object3D quad = new SimpleObject3D(GeometryUtils.createQuad());
    private ShaderState screenQuadState;
    {
        this.screenQuadState = this.quad.getShaderState();
        this.screenQuadState.setFragmentShader("quadFragmentShader");
        this.screenQuadState.setVertexShader("quadVertexShader");
        this.screenQuadState.setUniform(UniformUtils.getHolder("u_Texture0", 0));
    }

    private GL2 gl;
    private GL2ContextDistributor contextDistributor;

    private OpenGLRenderer renderer;
    private OpenGLRenderer blurRenderer;

    private RenderTargetManager renderTargetManager;

    private SimpleViewSpace viewSpace = new SimpleViewSpace();

    public void setContextDistributor(final GL2ContextDistributor contextDistributor) {
        this.contextDistributor = contextDistributor;
    }

    public void setRenderer(final OpenGLRenderer renderer) {
        this.renderer = renderer;
    }

    public void setBlurRenderer(final OpenGLRenderer renderer) {
        this.blurRenderer = renderer;
    }

    @Override
    public void init(final GLAutoDrawable drawable) {
        this.gl = drawable.getGL().getGL2();
        this.contextDistributor.distribute(this.gl);

        this.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        this.gl.glEnable(GL.GL_CULL_FACE);

        this.gl.glEnable(GL.GL_DEPTH_TEST);

        final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = -0.5f;

        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -5.0f;

        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;

        MatrixUtils.setLookAt(this.viewSpace.getViewMatrix(), 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
    }

    private void drawScene() {
        this.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        float[] modelMatrix = this.mainObject.getTransformationMatrix();
        FloatUtil.makeIdentityf(modelMatrix, 0);
        MatrixUtils.translate(modelMatrix, 0, 4.0f, 0.0f, -7.0f);

        MatrixUtils.multiplyMatrices(this.viewSpace.getViewMatrix(), 0, modelMatrix, 0, this.modelViewMatrix, 0);
        this.mainObject.getShaderState().setUniform(UniformUtils.getMatrix4Holder("u_MVMatrix", this.modelViewMatrix));

        MatrixUtils.multiplyMatrices(this.viewSpace.getProjectionMatrix(), 0, this.modelViewMatrix, 0, modelMatrix,
                0);

        this.renderer.render(this.mainObject);
    }

    @Override
    public void dispose(final GLAutoDrawable drawable) {
    }

    @Override
    public void display(final GLAutoDrawable drawable) {
        this.renderTargetManager.setTarget(GraphicsEngine.FILTER_TARGET_1, null);
        this.drawScene();

        this.gl.glEnable(GL.GL_BLEND);
        this.gl.glBlendFunc(GL.GL_ONE, GL.GL_ONE);

        this.renderTargetManager.setTarget(GraphicsEngine.FILTER_TARGET_2, GraphicsEngine.FILTER_TARGET_1);
        this.drawGauss(false);

        this.renderTargetManager.setTarget(GraphicsEngine.FILTER_TARGET_1, GraphicsEngine.FILTER_TARGET_2);
        this.drawGauss(true);

        this.gl.glDisable(GL.GL_BLEND);

        this.renderTargetManager.setTarget(RenderTargetManager.DEFAULT_TARGET_NAME, null);
        this.drawScene();

        this.gl.glEnable(GL.GL_BLEND);
        this.gl.glBlendFunc(GL.GL_ONE, GL.GL_ONE);

        this.renderTargetManager.setTarget(RenderTargetManager.DEFAULT_TARGET_NAME, GraphicsEngine.FILTER_TARGET_1);
        this.drawQuad();

        this.gl.glDisable(GL.GL_BLEND);
    }

    @Override
    public void reshape(final GLAutoDrawable drawable, final int x, final int y, final int width, final int height) {
        if (this.renderTargetManager == null) {
            this.renderTargetManager = new RenderTargetManager(this.gl, 0, 0, width, height);

            this.renderTargetManager.createTarget(GraphicsEngine.FILTER_TARGET_1, GraphicsEngine.BLOOM_MAP_WIDTH,
                    GraphicsEngine.BLOOM_MAP_HEIGHT);
            this.renderTargetManager.createTarget(GraphicsEngine.FILTER_TARGET_2, GraphicsEngine.BLOOM_MAP_WIDTH,
                    GraphicsEngine.BLOOM_MAP_HEIGHT);
        } else {
            this.renderTargetManager.setDefaultViewPort(0, 0, width, height);
        }

        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 2.0f;
        final float far = 10.0f;

        MatrixUtils.setFrustum(this.viewSpace.getProjectionMatrix(), 0, left, right, bottom, top, near, far);

        this.blurPassModel = new GaussBlurModel(GraphicsEngine.BLOOM_MAP_WIDTH, GraphicsEngine.BLOOM_MAP_HEIGHT, ratio);
        this.blurRenderer.setRenderSubPassModel(this.blurPassModel);
    }

    private void drawQuad() {
        this.gl.glDisable(GL.GL_DEPTH_TEST);

        this.quad.setShaderState(this.screenQuadState);
        this.renderer.render(this.quad);

        this.gl.glEnable(GL.GL_DEPTH_TEST);
    }

    private void drawGauss(final boolean inverted) {
        this.gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        this.gl.glDisable(GL.GL_DEPTH_TEST);

        this.quad.setShaderState(this.gaussState);

        this.blurPassModel.setInverted(inverted);
        this.blurRenderer.render(this.quad);

        this.gl.glEnable(GL.GL_DEPTH_TEST);
    }

}
