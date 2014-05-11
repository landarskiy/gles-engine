package by.vsu.mf.simplegles20.core.texture;

import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.simplegles20.core.util.ObjectPool;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class TextureLoader {
	private static TextureLoader instance;
	private Map<Integer, Texture> textures;
	private ObjectPool<Texture> texturePool;

	@SuppressLint("UseSparseArrays")
	private TextureLoader() {
		this.textures = new HashMap<Integer, Texture>();
		this.texturePool = new ObjectPool<Texture>();
		for(int i = 0 ; i<texturePool.getPoolSize() ; i++) {
			texturePool.addLast(new Texture());
		}
	}

	public static TextureLoader getInstance() {
		if (instance == null) {
			synchronized (TextureLoader.class) {
				if (instance == null) {
					instance = new TextureLoader();
				}
			}
		}
		return instance;
	}
	
	public Texture load(final Bitmap bitmap, TextureProperties properties) {
		int hash = bitmap.hashCode();
		
		Texture texture = textures.get(hash);
		if (texture != null) {
			return texture;
		}
		
		if(texturePool.isEmpty()) {
			texture = new Texture();
		} else {
			texture = texturePool.removeFirst();
		}
				
		texture.setHash(hash);
		texture.setWidth(bitmap.getWidth());
		texture.setHeight(bitmap.getHeight());
		init(texture, bitmap, properties);

		textures.put(hash, texture);
		return texture;
	}		

	private void init(Texture texture, Bitmap bitmap, TextureProperties p) {
		int[] textureId = new int[1];
		GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
		GLES20.glGenTextures(1, textureId, 0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);
		texture.setTextureId(textureId[0]);

		if (p.isMipmaps()) {
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_MIN_FILTER,
					GLES20.GL_LINEAR_MIPMAP_LINEAR);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		}
		if (!p.isMipmaps() && p.isFilter()) {
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		}
		if (!p.isMipmaps() && !p.isFilter()) {
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		}

		int format = GLUtils.getInternalFormat(bitmap);
		int type = GLES20.GL_UNSIGNED_BYTE;
		try {
			type = GLUtils.getType(bitmap);
		} catch (IllegalArgumentException e) {
			Log.e("OpenGL", "bitmap illegal type");
		}

		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, format, bitmap, type, 0);

		if (p.isDupli()) {
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
		}
		if (!p.isDupli()) {
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
					GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		}
		if (p.isMipmaps()) {
			GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
		}
	}
	
	public synchronized void clear() {
		for(Integer hash : textures.keySet()) {
			textures.remove(hash).delete();
		}
	}
	
	public void remove(Texture texture) {
		remove(texture.getHash());
	}
	
	public void remove(int hash) {
		Texture texture = textures.remove(hash);
		if (texture == null) {
			return;
		}
		texture.delete();
		texturePool.addLast(texture);
	}
		
}
