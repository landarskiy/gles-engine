package by.vsu.mf.simplegles20.core.renderer;

import java.util.LinkedList;
import java.util.Stack;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;


public class EGLConfigChooser implements GLSurfaceView.EGLConfigChooser {    
    
	private LinkedList<int[]> configs = new LinkedList<int[]>();
	private Stack<Integer> currentConfig = new Stack<Integer>();	
       
    private int[] configSpec;
    
    public void addConfigParam(int key, int value) {
    	if(key != EGL10.EGL_NONE) {
    		currentConfig.push(key);
        	currentConfig.push(value);
        	return;    		
    	}
    	int[] config = new int[currentConfig.size()+1];
		config[currentConfig.size()] = EGL10.EGL_NONE;
		for(int i = currentConfig.size()-1; i>=0; i--) {
			config[i] = currentConfig.pop();
		}
		configs.addFirst(config);
    }    
    
    
    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
    	int[] resultValue = new int[1];          	
    	//перебираем конфиги пока не найдем подходящий        
        int configsCount = 0;
    	for(int[] config : configs) {
        	configSpec = config;
        	if (!egl.eglChooseConfig(display, configSpec, null, 0, resultValue)) {
        		throw new IllegalArgumentException("eglChooseConfig failed");
        	}
        	configsCount = resultValue[0];
        	if(configsCount > 0) {
        		break;
        	}        		
        }
        if (configsCount <= 0) {	
    		throw new IllegalArgumentException("Your device doesn't support any of standard configurations");
    	}        
        EGLConfig[] configs = new EGLConfig[configsCount];
        //получаем массив конфигураций
        egl.eglChooseConfig(display, configSpec, configs, configsCount, resultValue); 
        //возвращаем первый конфиг подходящий под условия
        return configs[0];         
    }
}
