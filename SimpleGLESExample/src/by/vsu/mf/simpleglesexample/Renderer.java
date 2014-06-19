package by.vsu.mf.simpleglesexample;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import by.vsu.mf.meshloader.MeshLoader;
import by.vsu.mf.meshloader.ObjMeshLoader;
import by.vsu.mf.simplegles20.base.sprite.Object3D;
import by.vsu.mf.simplegles20.core.object.Camera;
import by.vsu.mf.simplegles20.core.object.ObjectTree;
import by.vsu.mf.simplegles20.core.object.Simple3DObject;
import by.vsu.mf.simplegles20.core.object.SimpleNormals3DObject;
import by.vsu.mf.simplegles20.core.shader.Shader;
import by.vsu.mf.simplegles20.core.shader.ShaderLoader;
import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.texture.TextureLoader;
import by.vsu.mf.simplegles20.core.texture.TextureProperties;
import by.vsu.mf.simplegles20.core.util.Matrix;
import by.vsu.mf.simplegles20.core.util.Point2D;
import by.vsu.mf.simplegles20.core.util.Point3D;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class Renderer implements GLSurfaceView.Renderer{
	private final static int FLOAT_SIZE_BYTES = 4;
	
	private Context context;
	
	public ObjectTree scene;
	
	private Camera camera;		
	private Simple3DObject spacebox;
	private Simple3DObject sun;
	private SimpleNormals3DObject spaceship;
	
	private final float[] Light = new float[3];
	
	Point2D movePoint = new Point2D(0, 0);
	
	float transformation[] = new float[16];
	float frameRotation[] = new float[16];
	float frameSunRotation[] = new float[16];
	float resultPoint[] = new float[4];
	
	Point3D srcCameraPosition;
	Point3D srcCameraDirection;
	
	public Renderer(Context context) {
		this.context = context;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		
		GLES20.glClearColor(0.1f,0.1f,0.3f,1.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        
		spacebox.setModelMatrix(scene.getGlobalModelMatrix(spacebox));
		spaceship.setModelMatrix(scene.getGlobalModelMatrix(spaceship));
		sun.setModelMatrix(scene.getGlobalModelMatrix(sun));
		
        spacebox.draw(camera);
        spaceship.draw(camera); 
        sun.draw(camera);
        
        Matrix.multiplyMV(resultPoint, 0, frameRotation, 0, spaceship.getPosition().toGomogenFloatArray(), 0);
        spaceship.getPosition().fromFloatArray(resultPoint);
        Matrix.multiplyMV(resultPoint, 0, frameRotation, 0, spaceship.getDirection().toGomogenFloatArray(), 0);
        spaceship.getDirection().fromFloatArray(resultPoint);
        Matrix.multiplyMV(resultPoint, 0, frameSunRotation, 0, sun.getDirection().toGomogenFloatArray(), 0);
        sun.getDirection().fromFloatArray(resultPoint);
        
        scene.updateGlobalModelMatrix(spacebox);
        
	}
	
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		scene = new ObjectTree();
		
		spacebox = create(R.raw.spacebox, R.raw.spaceskybox_hd);
		spaceship = createNormals(R.raw.sample_ship_finish, R.raw.sh3_flip);
		sun = create(R.raw.untitled, R.raw.untitled_texture);
		
		float ratio = (float) width / height/5;
		
		camera = new Camera();
		camera.setFrustumProjection(-ratio, ratio, -0.2f, 0.2f, 0.5f, 1000);
		
		Point3D position = new Point3D(0, 0, 4);
		Point3D direct = new Point3D(0, 0, 0);
		Point3D directUp = new Point3D(0, 1,0);
		
		camera.setLookAt(position, direct, directUp);
		camera.calculateProjectViewMatrix();
		
		position = new Point3D();
		direct = new Point3D(1, 0, 0);
		directUp = new Point3D(0, 0, 1);
		spacebox.setPosition(position);
		spacebox.setDirection(direct);
		spacebox.setDirectionUp(directUp);
		
		position = new Point3D(3, 0, 0);
		direct = new Point3D(0, 0, -1);
		directUp = new Point3D(0, -1, 0);
		spaceship.setPosition(position);
		spaceship.setDirection(direct);
		spaceship.setDirectionUp(directUp);
		
		position = new Point3D(0, 0, 0);
		direct = new Point3D(0, 0, -1);
		directUp = new Point3D(0, -1, 0);
		sun.setPosition(position);
		sun.setDirection(direct);
		sun.setDirectionUp(directUp);
		
		Matrix.setIdentityM(frameRotation, 0);
		Matrix.rotateM(frameRotation, 0, 1, 0, 1, 0);
		Matrix.setIdentityM(frameSunRotation, 0);
		Matrix.rotateM(frameSunRotation, 0, -0.2f, 0, 1, 0);
		
		Light[0]=0;
	    Light[1]=0;
	    Light[2]=0;
	    spacebox.setLight(Light);
	    spaceship.setLight(Light);
	    sun.setLight(Light);
	      
		GLES20.glViewport(0, 0, width, height);		
			
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glEnable( GLES20.GL_DEPTH_TEST );
		
		
		scene.add(null, spacebox);
		scene.add(spacebox, camera);
		scene.add(spacebox, spaceship);
		scene.add(spacebox, sun);
		
		scene.updateGlobalModelMatrix(spacebox);
		/*
		spacebox = create(R.raw.spacebox, R.raw.spaceskybox);
		spaceship = create(R.raw.sample_ship_finish, R.raw.sh3_flip);		
		
		float ratio = (float) width / height/2;
		
		camera = new Camera();
		camera.setFrustumProjection(-ratio, ratio, -0.5f, 0.5f, 0.5f, 1000);
		Point3D cameraPosition = new Point3D(0, 0, 4);
		Point3D targetCameraPoint = new Point3D(0, 0, 0);
		Point3D upVector = new Point3D(0, 1.0f,0);
		camera.setLookAt(cameraPosition, targetCameraPoint, upVector);
		camera.calculateProjectViewMatrix();
        
        Light[0]=1;
        Light[1]=-2;
        Light[2]=3;
        spacebox.setLight(Light);
        spaceship.setLight(Light);
        
		GLES20.glViewport(0, 0, width, height);		
		
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glEnable( GLES20.GL_DEPTH_TEST );*/
	}

	
	private Simple3DObject create(int objResId, int textureResId) {
		MeshLoader loader = new ObjMeshLoader();
		by.vsu.mf.meshloader.Mesh mesh;
		try {
			mesh = loader.load(context.getResources().openRawResource(objResId));
			int bufferSize = mesh.getFaceCount()*3*3;
			float[] vertex = new float[bufferSize];
			int index = 0;
			for(int i=0;i<mesh.getFaceCount();i++) {
				int[] face = mesh.getVertexFace(i);
				int[] faceT = mesh.getTextureFace(i);
				for(int j=0;j<face.length;j++) {
					float[] vertexPoint = mesh.getVertexCoordinate(face[j]);
					for(int k = 0 ; k<vertexPoint.length; k++) {
						vertex[index++] = vertexPoint[k];
					}
					
					/*float[]textPoint = mesh.getTextureCoordinatef(faceT[j]);
					for(int k = 0 ; k<textPoint.length; k++) {
						vertex[index++] = textPoint[k];
					}*/
				}												
			}
			
			bufferSize = mesh.getFaceCount()*3*2;
			float[] textures = new float[bufferSize];
			index = 0;
			for(int i=0;i<mesh.getFaceCount();i++) {
				int[] textFace = mesh.getTextureFace(i);
				for(int j=0;j<textFace.length;j++) {
					float[] textPoint = mesh.getTextureCoordinate(textFace[j]);
					for(int k = 0 ; k<textPoint.length; k++) {
						textures[index++] = textPoint[k];
					}
				}
			}
			
			Scanner vertexSc = new Scanner(new InputStreamReader(context.getResources().openRawResource(R.raw.simple_3d_v)));
			Scanner fragmentSc = new Scanner(new InputStreamReader(context.getResources().openRawResource(R.raw.simple_3d_f)));
			Shader shader = ShaderLoader.getInstance().load(toString(vertexSc), toString(fragmentSc));

			//InputStream isSpaceship = context.getResources().openRawResource(R.raw.sh3_flip);
			InputStream isSpaceship = context.getResources().openRawResource(textureResId);
			Bitmap bmpSpaceship = BitmapFactory.decodeStream(isSpaceship);
			Texture txSpaceship = TextureLoader.getInstance().load(bmpSpaceship, new TextureProperties(true, true, true));
			
			Simple3DObject object = new Simple3DObject(shader, vertex, mesh.getFaceCount()*3, textures, txSpaceship);		
			
			isSpaceship.close();
			bmpSpaceship.recycle();
			bmpSpaceship = null;
			return object;
		} catch (Exception e1) {
		}
		return null;
	}
	
	private SimpleNormals3DObject createNormals(int objResId, int textureResId) {
		MeshLoader loader = new ObjMeshLoader();
		by.vsu.mf.meshloader.Mesh mesh;
		try {
			mesh = loader.load(context.getResources().openRawResource(objResId));
			int bufferSize = mesh.getFaceCount()*3*3;
			float[] vertex = new float[bufferSize];
			int index = 0;
			for(int i=0;i<mesh.getFaceCount();i++) {
				int[] face = mesh.getVertexFace(i);
				int[] faceT = mesh.getTextureFace(i);
				for(int j=0;j<face.length;j++) {
					float[] vertexPoint = mesh.getVertexCoordinate(face[j]);
					for(int k = 0 ; k<vertexPoint.length; k++) {
						vertex[index++] = vertexPoint[k];
					}
					
					/*float[]textPoint = mesh.getTextureCoordinatef(faceT[j]);
					for(int k = 0 ; k<textPoint.length; k++) {
						vertex[index++] = textPoint[k];
					}*/
				}												
			}
			
			bufferSize = mesh.getFaceCount()*3*2;
			float[] textures = new float[bufferSize];
			index = 0;
			for(int i=0;i<mesh.getFaceCount();i++) {
				int[] textFace = mesh.getTextureFace(i);
				for(int j=0;j<textFace.length;j++) {
					float[] textPoint = mesh.getTextureCoordinate(textFace[j]);
					for(int k = 0 ; k<textPoint.length; k++) {
						textures[index++] = textPoint[k];
					}
				}
			}
			
			bufferSize = mesh.getFaceCount()*3*3;
			float[] normals = new float[bufferSize];
			index = 0;
			for(int i=0;i<mesh.getFaceCount();i++) {
				int[] normFace = mesh.getNarmalFace(i);
				for(int j=0;j<normFace.length;j++) {
					float[] normPoint = mesh.getNormalCoordinate(normFace[j]);
					for(int k = 0 ; k<normPoint.length; k++) {
						normals[index++] = normPoint[k];
					}
				}
			}
			Scanner vertexSc = new Scanner(new InputStreamReader(context.getResources().openRawResource(R.raw.light_3d_v)));
			Scanner fragmentSc = new Scanner(new InputStreamReader(context.getResources().openRawResource(R.raw.light_3d_f)));
			Shader shader = ShaderLoader.getInstance().load(toString(vertexSc), toString(fragmentSc));

			//InputStream isSpaceship = context.getResources().openRawResource(R.raw.sh3_flip);
			InputStream isSpaceship = context.getResources().openRawResource(textureResId);
			Bitmap bmpSpaceship = BitmapFactory.decodeStream(isSpaceship);
			Texture txSpaceship = TextureLoader.getInstance().load(bmpSpaceship, new TextureProperties(true, true, true));
			
			SimpleNormals3DObject object = new SimpleNormals3DObject(shader, vertex, mesh.getFaceCount()*3, textures, txSpaceship, normals);		
			
			isSpaceship.close();
			bmpSpaceship.recycle();
			bmpSpaceship = null;
			return object;
		} catch (Exception e1) {
		}
		return null;
	}
	
	
	 
	private String toString(Scanner sc) {
		StringBuilder text = new StringBuilder();
		while(sc.hasNext()) {
			text.append(sc.nextLine()).append("\n");
		}
		return text.toString();
	}

	public void onTouchEvent(final MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			movePoint.x = event.getRawX();
			movePoint.y = event.getRawY();
			srcCameraPosition = camera.getPosition();
			srcCameraDirection = camera.getDirection();
			break;
		case MotionEvent.ACTION_MOVE:
			if(srcCameraPosition == null) {
				srcCameraPosition = camera.getPosition();
			}
			if(srcCameraDirection == null) {
				srcCameraDirection = camera.getDirection();
			}
			float dx = event.getRawX() - movePoint.x;
			float dy = event.getRawY() - movePoint.y;
			Log.i("ROTATE", dx + " ; " + dy);
			float angleX = dx/16f;
			float angleY = dy/8f;
			
			//camera.rotate(angleX, 0, 1f, 0);
			//camera.rotate(angleY, 1f, 0, 0);
			
	        Matrix.setIdentityM(transformation, 0);
	        float xK = 1f;
	        float zK = 1f;
	        if(Math.abs(srcCameraPosition.x) > Math.abs(srcCameraPosition.z)) {
	        	zK = 0;
	        } else {
	        	xK = 0;
	        }
	        Matrix.rotateM(transformation, 0, angleX, 0, 1f, 0);
	        Matrix.rotateM(transformation, 0, angleY, 0.5f, 0, 0);
	        Matrix.rotateM(transformation, 0, angleY, 0, 0, 0.5f);
	        Point3D pCamera = new Point3D(srcCameraPosition.x, srcCameraPosition.y, srcCameraPosition.z);
	        Point3D pCameraDirection = new Point3D(srcCameraDirection.x, srcCameraDirection.y, srcCameraDirection.z);
	        float multiplePoint[] = pCamera.toGomogenFloatArray();
	        Matrix.multiplyMV(multiplePoint, 0, transformation, 0, multiplePoint, 0);
	        pCamera.fromFloatArray(multiplePoint);	       
	        camera.setPosition(pCamera);
	        
	        multiplePoint = pCameraDirection.toGomogenFloatArray();
	        Matrix.multiplyMV(multiplePoint, 0, transformation, 0, multiplePoint, 0);
	        pCameraDirection.fromFloatArray(multiplePoint);	       
	        camera.setDirection(pCameraDirection);
	        
			camera.updateModelMatrix();
	        camera.calculateProjectViewMatrix();
	        
			break;		
		}
    }
	
	public void zoom(float zoom, int event) {
		switch (event) {
		case 0:
			srcCameraPosition = camera.getPosition();
			break;
		case 1:
			Matrix.setIdentityM(transformation, 0);
			Matrix.scaleM(transformation, 0, zoom, zoom, zoom);
			Point3D pCamera = new Point3D(srcCameraPosition.x, srcCameraPosition.y, srcCameraPosition.z);
			float multiplePoint[] = pCamera.toGomogenFloatArray();
		    Matrix.multiplyMV(multiplePoint, 0, transformation, 0, multiplePoint, 0);
		    pCamera.fromFloatArray(multiplePoint);
		    camera.setPosition(pCamera);
		    camera.calculateProjectViewMatrix();
			break;
		}
		Log.i("SCALE", zoom + "");
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {		
		
	}
}
