package org.bananaLaba.sceneGraph.test.helper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

// FIXME: if OpenGL context has not changed and if a render target being created has name that overlaps with an
// existing target, don't forget to free all handles associated with the existing target.
public class RenderTargetManager {

    public static final String DEFAULT_TARGET_NAME = "default";

    private Map<String, TargetControl> targetControls = new HashMap<>();
    private boolean defaultTargetActive;

    private GL2 gl;

    private int[] generatedHandles = new int[1];

    public RenderTargetManager(final GL2 gl, final int screenX, final int screenY, final int screenWidth, final int screenHeight) {
        this.setGL(gl);

        final TargetControl control = new TargetControl();
        control.setBufferHandle(0);
        control.setTextureHandle(0);
        control.setWidth(screenWidth);
        control.setHeight(screenHeight);
        control.setX(screenX);
        control.setY(screenY);

        this.targetControls.put(RenderTargetManager.DEFAULT_TARGET_NAME, control);

        this.setTarget(RenderTargetManager.DEFAULT_TARGET_NAME, null);
    }

    public void setDefaultViewPort(final int x, final int y, final int width, final int height) {
        final TargetControl control = this.targetControls.get(RenderTargetManager.DEFAULT_TARGET_NAME);
        control.setWidth(width);
        control.setHeight(height);
        control.setX(x);
        control.setY(y);

        if (this.defaultTargetActive) {
            this.gl.glViewport(x, y, width, height);
        }
    }

    public void setGL(final GL2 gl) {
        this.gl = gl;
    }

    public void createTarget(final String name, final int width, final int height) {
        if (name == null) {
            throw new RuntimeException("Expected a not-null render target name!");
        }

        if (RenderTargetManager.DEFAULT_TARGET_NAME.equals(name)) {
            throw new RuntimeException("Cannot re-define the default render target!");
        }

        this.gl.glGenTextures(1, this.generatedHandles, 0);
        int textureHandle = this.generatedHandles[0];

        this.gl.glBindTexture(GL.GL_TEXTURE_2D, textureHandle);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP_TO_EDGE);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        this.gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

        IntBuffer textureBuffer =
                ByteBuffer.allocateDirect(width * height * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        this.gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE,
                textureBuffer);

        this.gl.glGenRenderbuffers(1, this.generatedHandles, 0);
        int depthBuffer = this.generatedHandles[0];
        this.gl.glBindRenderbuffer(GL.GL_RENDERBUFFER, depthBuffer);
        this.gl.glRenderbufferStorage(GL.GL_RENDERBUFFER, GL.GL_DEPTH_COMPONENT16, width, height);

        this.gl.glGenFramebuffers(1, this.generatedHandles, 0);
        int frameBuffer = this.generatedHandles[0];
        this.gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, frameBuffer);
        this.gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER, GL.GL_COLOR_ATTACHMENT0, GL.GL_TEXTURE_2D, textureHandle, 0);
        this.gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, GL.GL_DEPTH_ATTACHMENT, GL.GL_RENDERBUFFER, depthBuffer);

        int result = this.gl.glCheckFramebufferStatus(GL.GL_FRAMEBUFFER);
        if (result != GL.GL_FRAMEBUFFER_COMPLETE) {
            throw new RuntimeException("Couldn't create the \"" + name + "\" framebuffer!");
        }

        this.gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, 0);
        this.gl.glBindTexture(GL.GL_TEXTURE_2D, 0);

        final TargetControl control = new TargetControl();
        control.setBufferHandle(frameBuffer);
        control.setTextureHandle(textureHandle);
        control.setWidth(width);
        control.setHeight(height);
        this.targetControls.put(name, control);
    }

    public void setTarget(final String bufferTargetName, final String textureTargetName) {
        final TargetControl bufferControl = this.targetControls.get(bufferTargetName);
        if (bufferControl == null) {
            throw new RuntimeException("The render target \"" + bufferTargetName + "\" doesn't exist!");
        }

        if (RenderTargetManager.DEFAULT_TARGET_NAME.equals(bufferTargetName)) {
            this.defaultTargetActive = true;
        } else {
            this.defaultTargetActive = false;
        }

        this.gl.glViewport(bufferControl.getX(), bufferControl.getY(), bufferControl.getWidth(),
                bufferControl.getHeight());

        this.gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, bufferControl.getBufferHandle());
        this.gl.glActiveTexture(GL.GL_TEXTURE0);
        if (textureTargetName != null) {
            final TargetControl textureControl = this.targetControls.get(textureTargetName);
            if (textureControl == null) {
                throw new RuntimeException("The render target \"" + textureTargetName + "\" doesn't exist!");
            }

            this.gl.glBindTexture(GL.GL_TEXTURE_2D, textureControl.getTextureHandle());
        } else {
            this.gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
        }
    }

    private static class TargetControl {

        private int bufferHandle;
        private int textureHandle;

        private int x;
        private int y;

        private int width;
        private int height;

        public int getBufferHandle() {
            return this.bufferHandle;
        }

        public void setBufferHandle(final int bufferHandle) {
            this.bufferHandle = bufferHandle;
        }

        public int getTextureHandle() {
            return this.textureHandle;
        }

        public void setTextureHandle(final int textureHandle) {
            this.textureHandle = textureHandle;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(final int width) {
            this.width = width;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(final int height) {
            this.height = height;
        }

        public int getX() {
            return this.x;
        }

        public void setX(final int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(final int y) {
            this.y = y;
        }

    }

}
