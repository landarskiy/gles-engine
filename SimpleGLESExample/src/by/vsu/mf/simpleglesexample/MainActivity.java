package by.vsu.mf.simpleglesexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import org.bananaLaba.bootstrap.test.ConversionUtilsTest;
import org.bananaLaba.bootstrap.test.FlowNodeCopyTest;
import org.bananaLaba.fdp.FDPLoader;
import org.bananaLaba.fdp.XMLProcessor;
import org.bananaLaba.fdp.simple.bootstrap.SimpleFDPLoader;
import org.bananaLaba.ioc.BeanContainer;
import org.bananaLaba.ioc.BeanContainerLoader;
import org.bananaLaba.ioc.bootstrap.SimpleBeanContainerLoader;
import org.bananaLaba.ioc.simple.SimpleContainer;

import by.vsu.mf.meshloader.ObjMeshLoader;
import by.vsu.mf.simplegles20.core.object.DirectedAbstractObject;
import by.vsu.mf.simplegles20.core.object.ObjectTree;
import by.vsu.mf.simplegles20.core.renderer.EGLConfigChooser;
import by.vsu.mf.simplegles20.core.shader.ShaderLoader;
import by.vsu.mf.simplegles20.core.texture.TextureLoader;
import by.vsu.mf.simplegles20.core.util.Point3D;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.PixelFormat;

public class MainActivity extends Activity {

	private String update = "";
	private long lastUpdateTime = 0;
	private long lastUpdate = 0;
	private GLSurfaceView glSurfaceView;
    private Renderer render;
    private EGLConfigChooser ConfigChooser;
    private Handler mHandler = new Handler();
    private Boolean RPause = false;
    private boolean running;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        glSurfaceView = (GLSurfaceView) findViewById(R.id.surfaceView);
        
        
        final SeekBar bar = (SeekBar)findViewById(R.id.seekBar1);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				bar.setProgress(50);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				render.zoom(1f, 0);
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(!fromUser) {
					return;
				}
				render.zoom(100f/(progress + 50), 1);				
			}
		});
        EGLConfigChooser chooser = null;
        try {
        	final BeanContainerLoader containerLoader = new SimpleBeanContainerLoader();
			final BeanContainer container = containerLoader.load(getResources().openRawResource(by.vsu.mf.simplegles20.R.raw.beans));
			final FDPLoader loader = new SimpleFDPLoader(container);
	        final XMLProcessor processor = loader.load(getResources().openRawResource(by.vsu.mf.simplegles20.R.raw.scenarios));
	        processor.process(getResources().openRawResource(by.vsu.mf.simplegles20.R.raw.egl_config_default_3d));
			chooser = container.getBean("eglConfigChooser", EGLConfigChooser.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                

        getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		
        glSurfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setEGLConfigChooser(chooser);
        //glSurfaceView.setEGLConfigChooser(ConfigChooser = new EGLConfigChooser(EGLConfigChooser.C_3D_888_MSAA));

        render = new Renderer(this);

        glSurfaceView.setRenderer(render);

        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        blp.gravity = Gravity.TOP;
        LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT );                
        running = true;
        
        new Thread(new Runnable() {			
			@Override
			public void run() {
				while(running) {
					long startTime = System.currentTimeMillis();
					glSurfaceView.requestRender();
					try {
						long sleepTime = Math.min(1000/30, System.currentTimeMillis()-startTime);
						Thread.sleep(Math.abs(1000/20 - sleepTime));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
					
				}				
			}
		}).start();    
    }
    
    void reqRend(){
    	if(System.currentTimeMillis()-lastUpdateTime > 500) {
    		update = " " + (1000/(System.currentTimeMillis()-lastUpdate));
    		lastUpdateTime = System.currentTimeMillis();
    	}
    	lastUpdate = System.currentTimeMillis();

        mHandler.removeCallbacks(mDrawRa);
        if(!RPause){
        	mHandler.postDelayed(mDrawRa, 0);
        	glSurfaceView.requestRender();
        }
    }

    private final Runnable mDrawRa = new Runnable() {
        public void run() {
            reqRend();
            try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
			}
        }
    };

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        glSurfaceView.queueEvent(new Runnable() {
            public void run() {
                render.onTouchEvent(event);
            }});
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
        RPause = true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
        RPause = false;
        reqRend();
    }
    @Override
    protected void onStop(){
        super.onStop();
        RPause = true;
        this.finish();
    }

	@Override
	public void onBackPressed() {
		RPause = true;
		running = false;
		ShaderLoader.getInstance().clear();
		//TextureLoader.getInstance().clear();
		super.onBackPressed();		
	}
    
    

}
