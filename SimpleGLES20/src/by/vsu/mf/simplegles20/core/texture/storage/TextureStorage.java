package by.vsu.mf.simplegles20.core.texture.storage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.vsu.mf.simplegles20.core.texture.Texture;
import by.vsu.mf.simplegles20.core.texture.TextureLoader;
import by.vsu.mf.simplegles20.core.texture.TextureProperties;
import by.vsu.mf.simplegles20.core.util.Point2D;
import by.vsu.mf.simplegles20.core.util.Rect;
import by.vsu.mf.simplegles20.core.util.ScreenHelper;
import by.vsu.mf.simplegles20.core.util.packer.AtlasInfo;
import by.vsu.mf.simplegles20.core.util.packer.AtlasTextureInfo;
import by.vsu.mf.simplegles20.core.util.packer.OpenGLTexturePacker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

public class TextureStorage {
	private Map<String, TextureInfo> texturesInfo;
	private Map<AtlasInfo, Texture> atlases;
	private OpenGLTexturePacker packer;
	
	/**
	 * Инициализация хранилища. Очень тяжелая операция.
	 * @param textures
	 * @param properties
	 */
	public TextureStorage(Map<String, Bitmap> textures, TextureProperties properties) {
		List<AtlasTextureInfo> textureSizes = new LinkedList<AtlasTextureInfo>();
		for(String bmpName : textures.keySet()) {
			Bitmap bmp = textures.get(bmpName);
			textureSizes.add(new AtlasTextureInfo(bmpName, bmp.getWidth(), bmp.getHeight()));
		}
		packer = new OpenGLTexturePacker();
		packer.packTextures(textureSizes);
		
		atlases = new HashMap<AtlasInfo, Texture>();
		texturesInfo = new HashMap<String, TextureInfo>();
		
		for(AtlasInfo atlas : packer.getAtlases()) {	
			ScreenHelper helper = new ScreenHelper(atlas.getWidth(), atlas.getHeight());
			Bitmap bmpAtlas = Bitmap.createBitmap(atlas.getWidth(), atlas.getHeight(), Bitmap.Config.ARGB_8888);			
			Canvas c = new Canvas(bmpAtlas);
			c.drawARGB(0, 0, 0, 0);
			Set<TextureInfo> newTextures = new HashSet<TextureInfo>();
			for(AtlasTextureInfo atlasTextureInfo : atlas.getTextures()) {
				Bitmap bmp = textures.get(atlasTextureInfo.getName());
				c.drawBitmap(bmp, atlasTextureInfo.getXPos(), atlasTextureInfo.getYPos(), null);
				Rect dimension = new Rect(
						new Point2D(helper.toGLESWidth(atlasTextureInfo.getXPos()), helper.toGLESHeight(atlasTextureInfo.getYPos())),
						helper.toGLESWidth(atlasTextureInfo.getWidth()),
						helper.toGLESHeight(atlasTextureInfo.getHeight()));
				TextureInfo info = new TextureInfo(null, dimension);
				texturesInfo.put(atlasTextureInfo.getName(), info);		
				newTextures.add(info);
			}
			Texture atlasTexture = TextureLoader.getInstance().load(bmpAtlas, properties);
			atlases.put(atlas, atlasTexture);
			for(TextureInfo info : newTextures) {
				info.setTexture(atlasTexture);
			}
			bmpAtlas.recycle();
			bmpAtlas = null;
		}				
	}
	
	public TextureInfo getTextureInfo(String textureName) {
		return texturesInfo.get(textureName);
	}
	
}
