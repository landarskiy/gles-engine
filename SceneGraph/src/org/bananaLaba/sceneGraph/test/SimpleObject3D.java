package org.bananaLaba.sceneGraph.test;

import java.util.ArrayList;
import java.util.Collection;

import by.vsu.mf.rendering.object.Geometry;
import by.vsu.mf.rendering.object.Object3D;
import by.vsu.mf.rendering.opengl.renderingAspect.RenderingAspect;
import by.vsu.mf.rendering.opengl.shader.ShaderState;

public class SimpleObject3D implements Object3D {

    private Geometry vertexData;
    private ShaderState shaderState;
    private float[] modelMatrix = new float[16];
    private Collection<? extends RenderingAspect> renderingAspects = new ArrayList<>();

    public SimpleObject3D(final Geometry vertexData) {
        this.vertexData = vertexData;
        this.shaderState = new ShaderState();
        this.shaderState.setFragmentShader("simpleFragmentShader");
        this.shaderState.setVertexShader("simpleVertexShader");
    }

    @Override
    public Geometry getVertexData() {
        return this.vertexData;
    }

    @Override
    public ShaderState getShaderState() {
        return this.shaderState;
    }

    @Override
    public float[] getTransformationMatrix() {
        return this.modelMatrix;
    }

    @Override
    public Collection<? extends RenderingAspect> getRenderingAspects() {
        return this.renderingAspects;
    }

    @Override
    public void setShaderState(final ShaderState state) {
        this.shaderState = state;
    }

}
