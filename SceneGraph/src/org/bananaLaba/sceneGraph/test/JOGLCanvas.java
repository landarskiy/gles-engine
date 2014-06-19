package org.bananaLaba.sceneGraph.test;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

import org.bananaLaba.ioc.BeanContainer;

import com.jogamp.opengl.util.FPSAnimator;

public class JOGLCanvas extends GLCanvas {

    private static final long serialVersionUID = 435297155331244240L;

    public JOGLCanvas(final GLCapabilities capabilities, final BeanContainer container) {
        super(capabilities);

        final GLEventListener listener = container.getBean("canvasListener", GLEventListener.class);
        this.addGLEventListener(listener);

        final FPSAnimator animator = new FPSAnimator(this, 60);
        animator.start();
    }

}
