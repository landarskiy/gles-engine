package org.bananaLaba.sceneGraph.test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;

import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerLoader;
import org.bananaLaba.ioc.bootstrap.SimpleBeanContainerLoader;

public class DemoTest {

    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    public static void main(final String[] arguments) throws Exception {
        final JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        final double x = 0.5 * (resolution.getWidth() - DemoTest.WINDOW_WIDTH);
        final double y = 0.5 * (resolution.getHeight() - DemoTest.WINDOW_HEIGHT);
        frame.setBounds((int) x, (int) y, DemoTest.WINDOW_WIDTH, DemoTest.WINDOW_HEIGHT);

        final BeanContainerLoader loader = new SimpleBeanContainerLoader();
        final InputStream containerIn = new FileInputStream(new File("files/test/container2.xml"));
        final BeanContainer container = loader.load(containerIn);
        containerIn.close();

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        final GLCapabilities capabilities = new GLCapabilities(profile);

        frame.add(new JOGLCanvas(capabilities, container));
        frame.setVisible(true);
    }

}
